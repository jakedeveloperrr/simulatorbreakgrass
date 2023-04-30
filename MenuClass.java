package dev.jake.breakgrasssimulator.systemmenu;

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

import java.util.ArrayList;
import java.util.Arrays;

public class MenuClass implements CommandExecutor {

    private final BreakGrassSimulator plugin;
    public MenuClass (BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    public Inventory menu = Bukkit.createInventory(null, 18, ">>> Меню игрока");
    public Inventory console = Bukkit.createInventory(null, 54, ">>> Консоль");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory menu = Bukkit.createInventory(null, 18, ">>> Меню игрока");

            ItemStack balance = new ItemStack(Material.EMERALD_BLOCK);
            ItemStack levels = new ItemStack(Material.TRIDENT);
            ItemStack help = new ItemStack(Material.FISHING_ROD);
            ItemStack warp = new ItemStack(Material.LAPIS_ORE);


            ItemMeta balmeta = balance.getItemMeta();
            ItemMeta lvlsmeta = levels.getItemMeta();


            balmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5&l >>> Баланс"));
            lvlsmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&l&5 >>> Список уровней"));

            ArrayList<String> balanceLore = new ArrayList<>();
            ArrayList<String> lvlseLore = new ArrayList<>();
            ArrayList<String> topmetaLore = new ArrayList<>();

            balanceLore.add(ChatColor.translateAlternateColorCodes('&', "&7 Посмотреть свой баланс"));
            lvlseLore.add(ChatColor.translateAlternateColorCodes('&', "&7 Информация об уровнях"));
            topmetaLore.add(ChatColor.translateAlternateColorCodes('&', "&7 Посмотреть топ-лист игроков по балансу."));

            balmeta.setLore(balanceLore);
            balance.setItemMeta(balmeta);

            lvlsmeta.setLore(lvlseLore);
            levels.setItemMeta(lvlsmeta);

            ItemMeta metaHelp = help.getItemMeta();
            metaHelp.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5&l>>> Информация"));
            ArrayList<String> loreHelp = new ArrayList<>();
            loreHelp.add(ChatColor.translateAlternateColorCodes('&', "&7Нажми на &6УДОЧКУ&3, чтобы показать информацию полностью"));
            metaHelp.setLore(loreHelp);
            help.setItemMeta(metaHelp);

            ItemMeta metaWarp = warp.getItemMeta();
            metaWarp.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5&l>>> Варп"));
            ArrayList<String> loreWarp = new ArrayList<>();
            loreWarp.add(ChatColor.translateAlternateColorCodes('&', "&7Нажми чтобы - переместиться на &6арену-фарма!"));
            metaWarp.setLore(loreWarp);
            warp.setItemMeta(metaWarp);

            ItemStack[] statmenu = {balance, levels, help, warp};
            menu.setContents(statmenu);
            player.openInventory(menu);
        }
        return false;
    }
}
