package dev.jake.breakgrasssimulator.baseSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&bПеремещение на спавн! Осталось 5 секунд!"));
            new BukkitRunnable() {
                int ctr = 5;
                @Override
                public void run() {
                    ctr -= 1;

                    if (ctr == 4) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&bОсталось " + ctr + " секунд!"));
                    }
                    else if (ctr == 3) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&bОсталось " + ctr + " секунд!"));

                    }
                    else if (ctr == 2) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&bОсталось " + ctr + " секунд!"));
                    }
                    else if (ctr == 1) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&bОсталось " + ctr + " секунд!"));
                    }

                    else if (ctr == 0) {
                        player.teleport(player.getWorld().getSpawnLocation());

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&bПереместил тебя на спавн!"));
                        cancel();
                    }
                }
            }.runTaskTimer(BreakGrassSimulator.getInstance(), 0L, 20L);
        }
        return false;
    }
}
