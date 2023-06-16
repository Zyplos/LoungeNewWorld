package dev.zyplos.loungenewworld.Events;

import dev.zyplos.loungenewworld.LoungeNewWorld;
import org.bukkit.Location;
import org.bukkit.entity.EnderSignal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class HandleEyeOfEnder implements Listener {
    private final LoungeNewWorld plugin;

    public HandleEyeOfEnder(LoungeNewWorld plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (!(e.getEntity() instanceof EnderSignal enderSignal)) {
            return;
        }

        Location eyeLocation = enderSignal.getLocation();
        Location spawnLocation = new Location(
                eyeLocation.getWorld(),
                eyeLocation.getWorld().getSpawnLocation().getX(),
                eyeLocation.getWorld().getSpawnLocation().getY(),
                eyeLocation.getWorld().getSpawnLocation().getZ()
        );

        // check that the eye of ender is within 70000 blocks of spawn
        if (eyeLocation.distance(spawnLocation) < plugin.getConfig().getInt("spawnRadius")) {
            return;
        }

        enderSignal.setTargetLocation(plugin.utils.getStrongholdLocation());
    }
}