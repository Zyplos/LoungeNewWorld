package dev.zyplos.loungenewworld.commands;

import dev.zyplos.loungenewworld.LoungeNewWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {
    private final LoungeNewWorld plugin;

    public ReloadConfig(LoungeNewWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reloadConfig();
        sender.sendMessage(plugin.utils.getServerPrefix() + "Config reloaded.");
        return true;
    }
}
