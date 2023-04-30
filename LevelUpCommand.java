package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class LevelUpCommand implements CommandExecutor {
    private final BreakGrassSimulator plugin;
    public LevelUpCommand(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory up = Bukkit.createInventory(null, 9, "Прокачка уровня");
            ItemStack uping = new ItemStack(Material.EXPERIENCE_BOTTLE);
            ItemMeta meta = uping.getItemMeta();
            meta.setDisplayName("Прокачать уровень");
            meta.setLore(Arrays.asList(
                    ChatColor.DARK_GRAY + "Нажми чтобы прокачать свой игровой уровень"
            ));
            uping.setItemMeta(meta);

            up.setItem(4, uping);
            player.openInventory(up);
        }
        return false;
    }
}
