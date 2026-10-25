package me.jellysquid.mods.sodium.client.render.chunk.format;

public enum ChunkMeshAttribute {
    POSITION,
    COLOR,
    TEXTURE,
    LIGHT,
    // Only present in the extended formats used while a shader pack is active
    ENTITY_DATA,
    MID_TEX_COORD,
    TANGENT
}
