package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EventPlus implements CommandExecutor {
    private final BreakGrassSimulator plugin;
    public EventPlus(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            Player player = (Player) sender;
                for (Player players: Bukkit.getOnlinePlayers()) {
                    players.sendTitle("Событие началось!", "Бонсный опыт!");
                }
                new BukkitRunnable() {
                    int ctr = 60;
                    @Override
                    public void run() {
                        ctr -= 1;
                        if (ctr != 0) {
                            plugin.getLevelManager().addExp(player, 1);
                        }
                        plugin.getScoreBoardManager().setScoreBoard(player);
                        if (ctr == 0) {
                            for (Player players: Bukkit.getOnlinePlayers()) {
                                players.sendTitle("Событие закончилось!", "Бонсный опыт!");
                            }
                            cancel();
                        }
                    }
                }.runTaskTimer(BreakGrassSimulator.getInstance(), 0L, 20L);
            }
        return false;
    }
}
