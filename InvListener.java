package dev.jake.breakgrasssimulator.levelsystem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&',
                "&l&6 >>> Уровни"))) {
            event.setCancelled(true);
        }
        else if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&',
                "&l&6 >>> Перерождения"))) {
            event.setCancelled(true);
        }
        else if (event.getView().getTitle().equals("Прокачка уровня")) {
            event.setCancelled(true);
        }
    }
}
