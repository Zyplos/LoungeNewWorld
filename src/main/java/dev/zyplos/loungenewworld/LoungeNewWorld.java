package dev.zyplos.loungenewworld;

import dev.zyplos.loungenewworld.Events.HandleEyeOfEnder;
import dev.zyplos.loungenewworld.Events.PlayerDeath;
import dev.zyplos.loungenewworld.commands.NewPlaythrough;
import dev.zyplos.loungenewworld.commands.ReloadConfig;
import dev.zyplos.loungenewworld.internals.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoungeNewWorld extends JavaPlugin implements Listener {
    public Utils utils;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        utils = new Utils(this);

        getLogger().info("Enabled. " + Bukkit.getServer().getVersion() + " | " + Bukkit.getServer().getBukkitVersion());

        this.getCommand("newplaythrough").setExecutor(new NewPlaythrough(this));
        this.getCommand("playthroughconfigreload").setExecutor(new ReloadConfig(this));

        getServer().getPluginManager().registerEvents(new PlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new HandleEyeOfEnder(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");
        this.saveConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Hello, " + event.getPlayer().getName() + "!");
    }
}