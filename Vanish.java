package dev.jake.breakgrasssimulator.baseSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import jdk.tools.jlink.internal.Platform;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vanish implements CommandExecutor {


    BreakGrassSimulator plugin;

    public Vanish(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (sender.isOp()) {
                Player player = (Player) sender;
                if (plugin.invsList.contains(player)) {
                    for (Player players: Bukkit.getOnlinePlayers()) {
                        players.showPlayer(plugin, player);
                    }
                    plugin.invsList.remove(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&c&lНа данный момент тебя видят другие игроки!"));
                }
                else if (!plugin.invsList.contains(player)) {
                    for (Player players: Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(plugin, player);
                    }


                    plugin.invsList.add(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&c&lТы спрятался! Игроки не видят тебя"));
                }
            }
        }
        return true;
    }
}
