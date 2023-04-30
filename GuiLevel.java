package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class GuiLevel{

    public GuiLevel(Player player, BreakGrassSimulator plugin){
        LevelManager levelManage = plugin.getLevelManager();
        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&',
        "&l&6 >>> Уровни"));
        Economy economy = plugin.economy;
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b" + player.getDisplayName()));
        skullMeta.setLore(Arrays.asList(
                ChatColor.WHITE + "Уровень: " + ChatColor.GREEN + levelManage.getLevel(player),
                ChatColor.WHITE + "Количетсво опыта: " + ChatColor.GREEN + plugin.getLevelManager().getExp(player) + " из " + plugin.getLevelManager().getReqExp(player),
                ChatColor.WHITE + "Количество денег: " + ChatColor.GREEN + economy.getBalance(player) + " из 2500 монет"
        ));
        skull.setItemMeta(skullMeta);

        ItemStack top = new ItemStack(Material.BOOK);
        ItemMeta topMeta = top.getItemMeta();
        topMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&l >> ТОП-100 по уровням"));
        ArrayList<String> topList = new ArrayList<>();
        int i = 1;
        for (String uuid: levelManage.loadTop()) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            topList.add(ChatColor.WHITE.toString() + i + ". " + levelManage.getName(p) + ": " + ChatColor.GREEN + levelManage.getLevel(p));
            i++;
        }


        topMeta.setLore(topList);
        top.setItemMeta(topMeta);

        inv.setItem(11, skull);
        inv.setItem(15, top);
        player.openInventory(inv);
    }
}
