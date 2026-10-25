package me.jellysquid.mods.sodium.client.render.chunk.shader;

import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexAttributeBinding;
import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexFormat;
import me.jellysquid.mods.sodium.client.gl.shader.ShaderBindingPoint;
import me.jellysquid.mods.sodium.client.render.chunk.format.ChunkMeshAttribute;

import java.util.ArrayList;
import java.util.List;

public class ChunkShaderBindingPoints {
    public static final ShaderBindingPoint POSITION = new ShaderBindingPoint(0);
    public static final ShaderBindingPoint COLOR = new ShaderBindingPoint(1);
    public static final ShaderBindingPoint TEX_COORD = new ShaderBindingPoint(2);
    public static final ShaderBindingPoint LIGHT_COORD = new ShaderBindingPoint(3);

    public static final ShaderBindingPoint MODEL_OFFSET = new ShaderBindingPoint(4);

    // Read by shader packs through Oculus, at the indices Vintagium and Oculus agree on
    public static final ShaderBindingPoint ENTITY_DATA = new ShaderBindingPoint(11);
    public static final ShaderBindingPoint MID_TEX_COORD = new ShaderBindingPoint(12);
    public static final ShaderBindingPoint TANGENT = new ShaderBindingPoint(13);

    /**
     * The bindings for a chunk vertex format, including the shader pack attributes when the format carries them.
     */
    public static GlVertexAttributeBinding[] createChunkBindings(GlVertexFormat<ChunkMeshAttribute> format) {
        List<GlVertexAttributeBinding> bindings = new ArrayList<>(7);
        bindings.add(new GlVertexAttributeBinding(POSITION, format.getAttribute(ChunkMeshAttribute.POSITION)));
        bindings.add(new GlVertexAttributeBinding(COLOR, format.getAttribute(ChunkMeshAttribute.COLOR)));
        bindings.add(new GlVertexAttributeBinding(TEX_COORD, format.getAttribute(ChunkMeshAttribute.TEXTURE)));
        bindings.add(new GlVertexAttributeBinding(LIGHT_COORD, format.getAttribute(ChunkMeshAttribute.LIGHT)));

        if (format.hasAttribute(ChunkMeshAttribute.ENTITY_DATA)) {
            bindings.add(new GlVertexAttributeBinding(ENTITY_DATA, format.getAttribute(ChunkMeshAttribute.ENTITY_DATA)));
            bindings.add(new GlVertexAttributeBinding(MID_TEX_COORD, format.getAttribute(ChunkMeshAttribute.MID_TEX_COORD)));
            bindings.add(new GlVertexAttributeBinding(TANGENT, format.getAttribute(ChunkMeshAttribute.TANGENT)));
        }

        return bindings.toArray(new GlVertexAttributeBinding[0]);
    }
}
