# Spear Optimizer
Spear Optimizer is a mod designed mostly for high ping such as 150-120ms to be more optimized and have a faster delivery speed of packets
What Spear Optimizer does is a blend of various optimizations such as:

+ **Netty flush** - Immediately pushes spear use, release, swing, and attack packets down the network wire with zero client buffering.
+ **Instant attribute Swapping** - Flushes hotbar slot changes in sync with your hits, ensuring the server applies the correct weapon damage and enchantments during fast weapon swaps.
+ **Miss lockout** - Removes vanilla's 4-tick miss penalty (`missTime = 0`), so rapid jabs and strikes never drop inputs.
+ **Lunge charge sync** - Synchronizes spear charge timing so releases hit the exact valid tick threshold, preventing failed lunges on unstable ping.
+ **Smooth velocity blending** - Softens the incoming server velocity packet.

NOTICE | Due to anticheats such as **GrimAC, Vulcan, Polar, etc**: Client side prediction is impossible (possible but; as most servers with greater anticheat will catch that behaviour and mark it as cheating as you're given sudden velocity that wasn't authorized by anti-cheat on server-side.)

More work will be done on the Spear mod (such as smart-switch for Client prediction on servers that wont be tagged by anti-cheat. As of now it is too risky to be added)

## License
[License](LICENSE)

**Copyright (c)** 2026 Nel. All Rights Reserved.

### You are NOT permitted to:
- **Decompile**, **reverse engineer**, **modify**, **copy**, or **redistribute** this mod's source code.
- **Re-upload or redistribute** this mod as a standalone project on Modrinth, CurseForge, or any other website.
- **Sell or monetize** this mod in any commercial form.

### You ARE permitted to:
- Include this mod (.jar file in its original, unmodified form) in any public or private modpack distributed on Modrinth, CurseForge, or custom launchers.
- Play with this mod on any singleplayer world or multiplayer server.

### Description
Optimizes spear execution to be faster and more responsive while remaining compliant with server anti-cheats.