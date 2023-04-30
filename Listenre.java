package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.rebirthSetup.RebirthList;
import dev.jake.breakgrasssimulator.rebirthSetup.RebithManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listenre implements Listener {

    private final BreakGrassSimulator plugin;
    public Listenre(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void breakGrass(BlockBreakEvent event) {
        Economy economy = plugin.economy;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (block.getType() == Material.GRASS) {
            event.setDropItems(false);
             plugin.getLevelManager().addExp(player, 10);
             economy.depositPlayer(player, 25.0);
            if (plugin.getLevelManager().getExp(player) >= plugin.getLevelManager().getReqExp(player) && economy.getBalance(player) >= 2500.0) {
                plugin.getLevelManager().levelUp(player);
                economy.withdrawPlayer(player, 2000.0);
            }
            plugin.getScoreBoardManager().setScoreBoard(player);
        }
        else if (block.getType() == Material.TALL_GRASS) {
            event.setDropItems(false);
            plugin.getLevelManager().addExp(player, 30);
            economy.depositPlayer(player, 50.0);
            if (plugin.getLevelManager().getExp(player) >= plugin.getLevelManager().getReqExp(player) && economy.getBalance(player) >= 2500.0) {
                plugin.getLevelManager().levelUp(player);
                economy.withdrawPlayer(player, 2000.0);
            }
            plugin.getScoreBoardManager().setScoreBoard(player);
        }
        else if (block.getType() != Material.GRASS) {
            if (player.isOp()) {
                event.setCancelled(false);
            }else {
                event.setCancelled(true);
                event.setDropItems(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lЛомать можно только траву!"));
            }
        }
        else if (block.getType() != Material.TALL_GRASS) {
            if (player.isOp()) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                event.setDropItems(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lЛомать можно только траву!"));
            }
        }

    }
}
