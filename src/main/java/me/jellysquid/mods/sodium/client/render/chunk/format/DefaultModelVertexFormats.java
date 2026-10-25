package me.jellysquid.mods.sodium.client.render.chunk.format;

import me.jellysquid.mods.sodium.client.render.chunk.format.hfp.HFPModelVertexType;
import me.jellysquid.mods.sodium.client.render.chunk.format.sfp.SFPModelVertexType;

public class DefaultModelVertexFormats {
    public static final HFPModelVertexType MODEL_VERTEX_HFP = new HFPModelVertexType(false);
    public static final SFPModelVertexType MODEL_VERTEX_SFP = new SFPModelVertexType(false);

    // Used instead of the two above while a shader pack is active, see ShaderVertexData
    public static final HFPModelVertexType MODEL_VERTEX_HFP_EXTENDED = new HFPModelVertexType(true);
    public static final SFPModelVertexType MODEL_VERTEX_SFP_EXTENDED = new SFPModelVertexType(true);
}
