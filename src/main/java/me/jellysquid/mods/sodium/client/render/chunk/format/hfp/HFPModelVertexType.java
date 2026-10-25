package me.jellysquid.mods.sodium.client.render.chunk.format.hfp;

import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexAttributeFormat;
import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexFormat;
import me.jellysquid.mods.sodium.client.model.vertex.buffer.VertexBufferView;
import me.jellysquid.mods.sodium.client.model.vertex.type.BlittableVertexType;
import me.jellysquid.mods.sodium.client.model.vertex.type.ChunkVertexType;
import me.jellysquid.mods.sodium.client.render.chunk.format.ChunkMeshAttribute;
import me.jellysquid.mods.sodium.client.render.chunk.format.ModelVertexSink;
import net.minecraft.client.renderer.BufferBuilder;

/**
 * Uses half-precision floating point numbers to represent position coordinates and normalized unsigned shorts for
 * texture coordinates. All texel positions in the block diffuse texture atlas can be exactly mapped (including
 * their centering offset), as the
 */
public class HFPModelVertexType implements ChunkVertexType {
    public static final GlVertexFormat<ChunkMeshAttribute> VERTEX_FORMAT =
			GlVertexFormat.builder(ChunkMeshAttribute.class, 20)
	        .addElement(ChunkMeshAttribute.POSITION, 0, GlVertexAttributeFormat.UNSIGNED_SHORT, 3, false)
	        .addElement(ChunkMeshAttribute.COLOR, 8, GlVertexAttributeFormat.UNSIGNED_BYTE, 4, true)
	        .addElement(ChunkMeshAttribute.TEXTURE, 12, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, false)
	        .addElement(ChunkMeshAttribute.LIGHT, 16, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, true)
	        .optional(ChunkMeshAttribute.ENTITY_DATA, ChunkMeshAttribute.MID_TEX_COORD, ChunkMeshAttribute.TANGENT)
	        .build();

    /**
     * The same vertex plus the data shader packs read, in the layout Vintagium uses for Oculus.
     */
    public static final GlVertexFormat<ChunkMeshAttribute> VERTEX_FORMAT_EXTENDED =
			GlVertexFormat.builder(ChunkMeshAttribute.class, 36)
	        .addElement(ChunkMeshAttribute.POSITION, 0, GlVertexAttributeFormat.UNSIGNED_SHORT, 3, false)
	        .addElement(ChunkMeshAttribute.COLOR, 8, GlVertexAttributeFormat.UNSIGNED_BYTE, 4, true)
	        .addElement(ChunkMeshAttribute.TEXTURE, 12, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, false)
	        .addElement(ChunkMeshAttribute.LIGHT, 16, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, true)
	        .addElement(ChunkMeshAttribute.ENTITY_DATA, 20, GlVertexAttributeFormat.SHORT, 2, false)
	        .addElement(ChunkMeshAttribute.MID_TEX_COORD, 24, GlVertexAttributeFormat.FLOAT, 2, false)
	        .addElement(ChunkMeshAttribute.TANGENT, 32, GlVertexAttributeFormat.BYTE, 4, true)
	        .build();

    public static final float MODEL_SCALE = (32.0f / 65536.0f);
    public static final float TEXTURE_SCALE = (1.0f / 32768.0f);

    private final boolean extended;

    public HFPModelVertexType(boolean extended) {
        this.extended = extended;
    }

    public boolean isExtended() {
        return this.extended;
    }

    @Override
    public ModelVertexSink createFallbackWriter(BufferBuilder consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelVertexSink createBufferWriter(VertexBufferView buffer, boolean direct) {
        return direct ? new HFPModelVertexBufferWriterUnsafe(buffer, this) : new HFPModelVertexBufferWriterNio(buffer, this);
    }

    @Override
    public BlittableVertexType<ModelVertexSink> asBlittable() {
        return this;
    }

    @Override
    public GlVertexFormat<ChunkMeshAttribute> getCustomVertexFormat() {
        return this.extended ? VERTEX_FORMAT_EXTENDED : VERTEX_FORMAT;
    }

    @Override
    public float getModelScale() {
        return MODEL_SCALE;
    }

    @Override
    public float getTextureScale() {
        return TEXTURE_SCALE;
    }
}
