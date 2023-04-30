package dev.jake.breakgrasssimulator.baseSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickInGame implements Listener {
    private final BreakGrassSimulator plugin;
    public InventoryClickInGame(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
