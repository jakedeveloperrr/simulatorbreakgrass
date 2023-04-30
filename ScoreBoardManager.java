package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardManager {

    private final BreakGrassSimulator plugin;
    public ScoreBoardManager(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    public void setScoreBoard(Player player) {
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective levelBoard = scoreboard.registerNewObjective("title", "dummy");

        levelBoard.setDisplaySlot(DisplaySlot.SIDEBAR);
        levelBoard.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l&a >> Уровни <<"));

        Score line = levelBoard.getScore(ChatColor.RED + "-----------------------------");

        line.setScore(5);
        Score name = levelBoard.getScore("Ник: " + ChatColor.GREEN + player.getDisplayName());
        name.setScore(4);
        Score level = levelBoard.getScore("Твой уровень: " + ChatColor.GREEN + plugin.getLevelManager().getLevel(player));
        level.setScore(3);
        Score exp = levelBoard.getScore("Количетсво опыта: " + ChatColor.GREEN + plugin.getLevelManager().getExp(player) + " из " + plugin.getLevelManager().getReqExp(player));
        exp.setScore(2);
        Score rebirth = levelBoard.getScore("Перерождений: " + ChatColor.GREEN + plugin.getLevelManager().getRebirthPlayer(player));
        rebirth.setScore(1);
        Score balance = levelBoard.getScore("Твой баланс: " + ChatColor.GREEN + plugin.economy.getBalance(player) + " монет");
        balance.setScore(0);
        player.setScoreboard(scoreboard);
    }
}
