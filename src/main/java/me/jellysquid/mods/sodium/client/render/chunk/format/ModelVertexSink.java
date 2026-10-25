package me.jellysquid.mods.sodium.client.render.chunk.format;

import me.jellysquid.mods.sodium.client.model.vertex.VertexSink;

public interface ModelVertexSink extends VertexSink {
    /**
     * Writes a quad vertex to this sink.
     * @param x The x-position of the vertex
     * @param y The y-position of the vertex
     * @param z The z-position of the vertex
     * @param color The ABGR-packed color of the vertex
     * @param u The u-texture of the vertex
     * @param v The y-texture of the vertex
     * @param light The packed light-map coordinates of the vertex
     */
    void writeQuad(float x, float y, float z, int color, float u, float v, int light);

    /**
     * Writes a quad vertex with the extra data shader packs read: the block id, the centre of the quad's texture and
     * its tangent. Sinks of a format without those attributes write the plain vertex.
     */
    default void writeQuadExtended(float x, float y, float z, int color, float u, float v, int light, short entityId,
                                   float midU, float midV, int tangent) {
        this.writeQuad(x, y, z, color, u, v, light);
    }
}
