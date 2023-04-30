package dev.jake.breakgrasssimulator.rebirthSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class RebirthListener implements Listener {
    private final BreakGrassSimulator plugin;
    public RebirthListener(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void upReb(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&',
                "&l&6 >>> Перерождения"))) {

            if (event.getCurrentItem().getType() == null) {
                return;
            }
            switch (event.getCurrentItem().getType()) {
                case PLAYER_HEAD:
                    player.closeInventory();
                    break;
                case TRIDENT:
                    player.closeInventory();
                    plugin.getLevelManager().rebirthUp(player);
            }
        }
    }
}
