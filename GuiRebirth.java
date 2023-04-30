package dev.jake.breakgrasssimulator.rebirthSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.levelsystem.LevelManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class GuiRebirth {
    public GuiRebirth(Player player, BreakGrassSimulator plugin) {
        LevelManager rebirthManager = plugin.getLevelManager();
        Inventory rebInv = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&',
                "&l&6 >>> Перерождения"));

        Economy economy = plugin.economy;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b" + player.getDisplayName()));
        skullMeta.setLore(Arrays.asList(
                ChatColor.WHITE + "Количество ребитхов: " + ChatColor.GREEN + rebirthManager.getRebirthPlayer(player),
                ChatColor.WHITE + "Осталось до следующего ребитха: " + ChatColor.GREEN + economy.getBalance(player) + " из " + plugin.getLevelManager().getReqBalance(player)
        ));
        skull.setItemMeta(skullMeta);

        ItemStack up = new ItemStack(Material.TRIDENT);
        ItemMeta upMeta = up.getItemMeta();
        upMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                "&6&lПереродиться"));
        upMeta.setLore(Arrays.asList(
                ChatColor.GRAY + "Если хочешь переродиться нажми на трезубец",
                ChatColor.GRAY + "Перерождение стоит - " + ChatColor.RED + rebirthManager.getReqBalance(player)
        ));
        up.setItemMeta(upMeta);

        rebInv.setItem(11, skull);
        rebInv.setItem(15, up);
        player.openInventory(rebInv);
    }
}
