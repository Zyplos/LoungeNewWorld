package dev.zyplos.loungenewworld.commands;

import dev.zyplos.loungenewworld.LoungeNewWorld;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class NewPlaythrough implements CommandExecutor {
    private final LoungeNewWorld plugin;

    public NewPlaythrough(LoungeNewWorld plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            plugin.getLogger().info("This command can only be run by a player.");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(plugin.utils.getServerPrefix() + "Start a new playthrough for Minecraft 1.20.\n" +
                    ChatColor.of("#ff7b00") + "This will do the following:\n" +
                    "- Clear your inventory\n" +
                    "- Clear your ender chest\n" +
                    "- Set your xp to zero\n" +
                    "- Remove all your achievements\n" +
                    "- Set your spawnpoint to the new spawn\n" +
                    "- Teleport to the new spawn\n" +
                    ChatColor.of("#ffffff") + "Type " + ChatColor.GREEN + "/newplaythrough confirm " +
                    ChatColor.of("#ffffff") + "if you're alright with this.");
            return true;
        }

        if (!args[0].equals("confirm")) {
            player.sendMessage(plugin.utils.getServerPrefix() +
                    ChatColor.of("#ff7b00") + "If you're okay with the warnings outlined in " + ChatColor.GREEN + "/newplaythrough" +
                    ChatColor.of("#ffffff") + ", then type " + ChatColor.GREEN + "/newplaythrough confirm " +
                    ChatColor.of("#ffffff") + "to confirm you want to start a new playthrough.");
            return true;
        }

        // add player's uuid to the players array in the config.yml
        plugin.getConfig().set("players." + player.getUniqueId(), true);
        plugin.saveConfig();

        // actually do the stuff
        // clear player's inventory
        player.getInventory().clear();
        // clear player's ender chest
        player.getEnderChest().clear();
        // set player's levels to zero
        player.setLevel(0);
        player.setExp(0);

        // remove the player's achievements
        // https://www.spigotmc.org/threads/clearing-every-advancement-a-player-has-on-the-server.473875/#post-4011198w
        Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
        while (iterator.hasNext()) {
            AdvancementProgress progress = player.getAdvancementProgress(iterator.next());
            for (String criteria : progress.getAwardedCriteria()) {
                progress.revokeCriteria(criteria);
            }
        }
        // give back the story/root advancement
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "advancement grant " + player.getName() + " only minecraft:story/root");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "advancement grant " + player.getName() + " only minecraft:adventure/root");

        // set player's spawnpoint to the new spawn
        player.setBedSpawnLocation(plugin.utils.getNewSpawnLocation(), true);
        // teleport player to the new spawn
        player.teleport(plugin.utils.getNewSpawnLocation());

        player.sendMessage(plugin.utils.getServerPrefix() + "Have fun!");

        return true;
    }
}
