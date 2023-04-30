package dev.jake.breakgrasssimulator.baseSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.rebirthSetup.RebirthList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JoinLeaveEvent implements Listener {

    private final BreakGrassSimulator plugin;

    public JoinLeaveEvent(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&b&lПривет, " + player.getDisplayName() + ".\n" +
                            "Ты попал на &aсимулятор &bломания травы, &dполучай удовольствие, качай уровень, покупай ребитхи, в общем наслаждайся! \n" +
                            "&bРЕЖИМ СОЗДАН ДЛЯ ОТДЫХА В ТЕСТОВЫХ ЦЕЛЯХ &c( ! )"));

            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
                    "&b&lОго! На нашем режмие новый игрок - &d" + player.getDisplayName() + "&b! Не забудьте познакомиться с ним!"));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&l&bРад тебя видеть, снова " + player.getDisplayName() + "! Не забудь собрать ежедневную награду!"));

            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
                    "&l&d" + player.getDisplayName() + "&b снова с нами!"));
            for (int i = 0; i < plugin.invsList.size(); i++) {
                player.hidePlayer(plugin, plugin.invsList.get(i));
            }
        }

        plugin.getLevelManager().setupPlayer(player);
        plugin.getScoreBoardManager().setScoreBoard(player);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&b&lИгрок - &d" + player.getDisplayName() + "&b вышел с сервера! Но мы всегда будем его ждать."));
    }
}
