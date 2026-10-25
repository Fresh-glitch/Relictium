package me.jellysquid.mods.sodium.client.render.chunk.format;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * The extra vertex data shader packs read through Oculus: a block id for every block state, the centre of each quad's
 * texture and its tangent. Oculus is optional and reached by reflection, as Vintagium does.
 *
 * The block ids are read once per renderer rebuild, in {@link #refresh()}. Oculus rebuilds the renderer whenever its
 * ids change, so they cannot go stale between rebuilds. While no shader pack is active there are none, and the chunk
 * meshes keep the plain vertex formats.
 */
public class ShaderVertexData {
    private static final Object BLOCK_RENDERING_SETTINGS;
    private static final MethodHandle GET_BLOCK_STATE_IDS;

    private static volatile Object2IntMap<IBlockState> blockStateIds;

    static {
        Object instance = null;
        MethodHandle getBlockStateIds = null;

        try {
            Class<?> clazz = Class.forName("net.coderbot.iris.block_rendering.BlockRenderingSettings");
            instance = clazz.getField("INSTANCE").get(null);
            getBlockStateIds = MethodHandles.lookup().findVirtual(clazz, "getBlockStateIds", MethodType.methodType(Object2IntMap.class));
        } catch (Throwable ignored) {
            // Oculus is not installed
        }

        BLOCK_RENDERING_SETTINGS = instance;
        GET_BLOCK_STATE_IDS = getBlockStateIds;
    }

    /**
     * Reads the current block ids, and answers whether a shader pack is active.
     */
    @SuppressWarnings("unchecked")
    public static boolean refresh() {
        Object2IntMap<IBlockState> ids = null;

        if (GET_BLOCK_STATE_IDS != null) {
            try {
                ids = (Object2IntMap<IBlockState>) GET_BLOCK_STATE_IDS.invoke(BLOCK_RENDERING_SETTINGS);
            } catch (Throwable ignored) {
                ids = null;
            }
        }

        blockStateIds = ids;

        return ids != null;
    }

    public static boolean isActive() {
        return blockStateIds != null;
    }

    public static short getEntityId(IBlockState state) {
        Object2IntMap<IBlockState> ids = blockStateIds;

        if (ids == null || !ids.containsKey(state)) {
            return 0;
        }

        return (short) ids.getInt(state);
    }

    public static int computeTangent(EnumFacing face) {
        int tx, ty, tz;

        if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
            tx = 0;
            ty = 0;
            tz = 127;
        } else {
            tx = 127;
            ty = 0;
            tz = 0;
        }

        // xyz as signed normalized bytes, w = 127
        return (tx & 0xFF) | ((ty & 0xFF) << 8) | ((tz & 0xFF) << 16) | (127 << 24);
    }
}
