# Freshium

Freshium is [Relictium](https://github.com/TheMade4/Relictium) specialized for
[RLCraft](https://www.curseforge.com/minecraft/modpacks/rlcraft) 2.10 on
[Cleanroom](https://github.com/CleanroomMC/Cleanroom). Relictium is a fork of
[Vintagium](https://github.com/Asek3/sodium-1.12), itself a fork of
[Sodium](https://github.com/CaffeineMC/sodium), ported to Minecraft 1.12.2 and Forge. It replaces the game's chunk
renderer for much better performance.

## What it adds to Relictium

- **Shader support through Oculus.** Relictium leaves out the per vertex data shader packs read: each block's id, the
  centre of its texture and its tangent. Without them packs like Complementary cannot tell water, leaves or grass from
  any other block, so water draws flat and nothing waves. Freshium carries Vintagium's layout for that data, and only
  while a shader pack is active: with shaders off it keeps Relictium's own smaller vertex formats and does no extra work.
- It shows as "Freshium Renderer" in F3.

Everything else is Relictium 1.2.0. Freshium keeps its mod id, `vintagium`, so mods that look for Vintagium, Oculus
among them, still find it, and it reads Relictium's existing `config/relictium-options.json`.

## Known incompatibilities

From Relictium:

- OptiFine
- FarPlaneTwo
- LittleTiles
- Fluidlogged API (and mods using it, such as Subaquatic)
- ArchitectureCraft

## Credits

Freshium is by [Fresh-glitch](https://github.com/Fresh-glitch). Relictium is by
[TheMade4](https://github.com/TheMade4), Vintagium by [Asek3](https://github.com/Asek3), with work by NanoLive and
[embeddedt](https://github.com/embeddedt), and Sodium by [CaffeineMC](https://github.com/CaffeineMC).

## License

Freshium is licensed under the LGPLv3, like Relictium. See [LICENSE](LICENSE).
