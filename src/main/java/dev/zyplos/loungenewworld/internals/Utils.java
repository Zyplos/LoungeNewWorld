package dev.zyplos.loungenewworld.internals;

import dev.zyplos.loungenewworld.LoungeNewWorld;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Utils {
    private final LoungeNewWorld plugin;

    public Utils(LoungeNewWorld plugin) {
        this.plugin = plugin;
    }

    public String getServerPrefix() {
        return ChatColor.of("#848484") + "[" + ChatColor.of("#ff3e3e") + "Server" + ChatColor.of("#848484") + "] " + ChatColor.of("#ffffff");
    }

    public Location getNewSpawnLocation() {
        // get new spawn x y and z from config.yml
        int x = plugin.getConfig().getInt("newSpawn.x");
        int y = plugin.getConfig().getInt("newSpawn.y");
        int z = plugin.getConfig().getInt("newSpawn.z");

        // get overworld instead of using the player's location incase they're in the nether or end
        World overworld = Bukkit.getWorld("world");

        return new Location(overworld, x, y, z);
    }

    public Location getStrongholdLocation() {
        // get new spawn x y and z from config.yml
        int x = plugin.getConfig().getInt("strongholdLocation.x");
        int y = plugin.getConfig().getInt("strongholdLocation.y");
        int z = plugin.getConfig().getInt("strongholdLocation.z");

        // get overworld instead of using the player's location incase they're in the nether or end
        World overworld = Bukkit.getWorld("world");

        return new Location(overworld, x, y, z);
    }
}
