# LoungeNewWorld

A Spigot plugin for Minecraft survival multiplayer servers that changes players' spawnpoints to a far away location in the world. Originally made for situations where there is a small update to the game, but the server owner wants to keep the world instead of generating a new one. For players who want to start from scratch and enjoy the new update, this plugin will allow them to do so, while allowing other players to keep their progress and builds.

This plugin will not work with servers that use a custom world generator. This plugin assumes your overworld is called "world" as it is by default in `server.properties`.

Some minor edits could make this plugin work with custom world generators, but this works as is for the server it was made for.

## Usage

Players can use the `/newplaythrough` command to opt into the new spawnpoint that has been configured. Doing this command will clear their inventory, clear their ender chest, remove all their xp, clear all their achievements, and teleports them to the new spawnpoint.

This plugin makes sure that dying after breaking their bed at the new spawnpoint will not cause the player to respawn at the old spawnpoint (which will happen if you just `/spawnpoint` a player).

## Configuration

The new spawnpoint can be configured in the `config.yml` file. This can be changed by editing the `x`, `y`, and `z` values in the `newSpawn` section of the config file.

In the Java version of Minecraft, strongholds will stop spawning 20,000 blocks away from spawn. For players in the new playthrough, you can manually generate a stronghold with `/place` and edit the `strongholdLocation` section of the config file to let Eyes of Ender point to the new stronghold. `spawnRadius` can be edited to change how far players should be from the actual worldspawn before the plugin starts to change the direction that Eyes of Ender go.

Players with operator permissions can do `/playthroughconfigreload` to reflect any changes made in the config without restarting the server.

If a player would like to opt out of the new spawnpoint, you must manually remove their UUID from the `players` section of the config file. Do not just set their key to false. This will allow them to respawn at the original spawnpoint.
