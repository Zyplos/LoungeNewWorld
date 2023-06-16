package dev.zyplos.loungenewworld.Events;

import dev.zyplos.loungenewworld.LoungeNewWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    private final LoungeNewWorld plugin;

    public PlayerDeath(LoungeNewWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = (Player) e.getEntity();

        // the player has no bed AND is opted into new spawn, their spawnpoint is set to the new spawn
        // to avoid having their spawnpoint at the old playthrough's spawn
        if (player.getBedSpawnLocation() == null && plugin.getConfig().getBoolean("players." + player.getUniqueId())) {
            player.setBedSpawnLocation(plugin.utils.getNewSpawnLocation(), true);
        }

    }
}
