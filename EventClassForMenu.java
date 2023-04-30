package dev.jake.breakgrasssimulator.systemmenu;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.levelsystem.GuiLevel;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class EventClassForMenu implements Listener {

    private final BreakGrassSimulator plugin;
    public EventClassForMenu (BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    Economy economy = BreakGrassSimulator.getInstance().economy;
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();

        if (event.getView().getTitle().equals(">>> Меню игрока")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getType() == null) {
                return;
            }
            switch (event.getCurrentItem().getType()) {
                case EMERALD_BLOCK:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&l&a >>> На счету в данный момент: " + economy.getBalance(player) + " монет"));
                    break;
                case TRIDENT:
                    player.closeInventory();
                    new GuiLevel(player, plugin);
                    break;
                case FISHING_ROD:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&b&l>>> Режим &5'Симулятор Ломания Травы'&b - написан игроком jaketop1gg. \n" +
                                    ">>> Идея достаточно простая и голову особо ломать не нужно! &cЛомаешь траву - получаешь опыт и деньги.&b \n" +
                                    ">>> Удачи в игре! &7(режим создан исключительно в целях проверки знаний, для cristalix.gg)"));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5, 1);
                    break;
                case LAPIS_ORE:
                    player.closeInventory();
                    player.teleport(player.getWorld().getBlockAt(100, 90, 100).getLocation());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&b&l>>> Успешно переместил тебя в точку назначения!"));
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 5, 1);
                    break;
            }
        }
    }
}
