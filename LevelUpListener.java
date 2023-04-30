package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LevelUpListener implements Listener {

    private final BreakGrassSimulator plugin;
    public LevelUpListener(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void upLevel(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Economy economy = plugin.economy;
        if (event.getCurrentItem().getType() == null) {
            return;
        }
        switch (event.getCurrentItem().getType()) {
            case EXPERIENCE_BOTTLE:
                player.closeInventory();
                if (plugin.getLevelManager().getExp(player) >= plugin.getLevelManager().getReqExp(player) && economy.getBalance(player) >= 2500.0 ) {
                    plugin.getLevelManager().levelUp(player);
                    economy.withdrawPlayer(player, 2000.0);
                } else {
                    player.sendMessage(ChatColor.RED + "Недостаточно средств для прокачки уровня!");
                }
                plugin.getScoreBoardManager().setScoreBoard(player);
        }
    }
}
