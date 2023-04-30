package dev.jake.breakgrasssimulator.rebirthSetup;

import dev.jake.breakgrasssimulator.levelsystem.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class RebithTag {
    public static void setTag(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (RebirthList rebirth: RebirthList.values()) {
            Team team1 = player.getScoreboard().registerNewTeam(rebirth.name());

            team1.setPrefix(rebirth.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&f | "));

        }

        for (Player target: Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() != target.getUniqueId()) {
                player.getScoreboard().getTeam(RebithManager.getRebirth(target).name()).addEntry(target.getName());
            }
        }
    }

    public static void newRebirth(Player player) {
        RebirthList rebirthList = RebithManager.getRebirth(player);

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rebirthList.name()).addEntry(player.getName());
        }
    }

}
