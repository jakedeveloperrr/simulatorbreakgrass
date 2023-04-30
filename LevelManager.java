package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.rebirthSetup.RebirthList;
import dev.jake.breakgrasssimulator.rebirthSetup.RebithManager;
import dev.jake.breakgrasssimulator.rebirthSetup.RebithTag;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LevelManager {

    public final BreakGrassSimulator plugin;
    private YamlConfiguration levelConfig;
    private File levelFile;
    private YamlConfiguration rebirthConfig;
    private File rebirthFile;
    private List<String> top;
    public LevelManager(BreakGrassSimulator plugin) {
        this.plugin = plugin;
        top = new ArrayList<>();
    }

    private int maxRebirth = 10;

    public void loadLevelFile() {
        plugin.getDataFolder().mkdir();
        levelFile = new File(plugin.getDataFolder(), "Level-data.yml");
        plugin.getDataFolder().mkdir();
        rebirthFile = new File(plugin.getDataFolder(), "Rebirth-data.yml");
        if (!levelFile.exists()) {
            try {
                levelFile.createNewFile();
                rebirthFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        levelConfig = YamlConfiguration.loadConfiguration(levelFile);
        rebirthConfig = YamlConfiguration.loadConfiguration(rebirthFile);

    }

    public void setupPlayer(Player player) {
        if (!levelConfig.contains(player.getUniqueId().toString())) {
            levelConfig.set(player.getUniqueId() + ".level", 0);
            levelConfig.set(player.getUniqueId() + ".exp", 0);
            levelConfig.set(player.getUniqueId() + ".required-exp", 100);
        }
        levelConfig.set(player.getUniqueId() + ".name", player.getName());
        if (!rebirthConfig.contains(player.getUniqueId().toString())) {
            rebirthConfig.set(player.getUniqueId() + ".rebirth", 0);
            rebirthConfig.set(player.getUniqueId() + ".required-balance", 100000.0);
        }
        if (!player.hasPlayedBefore()) {
            if (getRebirthPlayer(player) == 0) {
                setRebirth(RebirthList.Zero, player);
            }
        }
        rebirthConfig.set(player.getUniqueId() + ".name", player.getName());
        try {
            levelConfig.save(levelFile);
            rebirthConfig.save(rebirthFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addExp(Player player, int amount) {
        levelConfig.set(player.getUniqueId() + ".exp", getExp(player) + amount);
        try {
            levelConfig.save(levelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void giveExp(Player player, int given) {
        levelConfig.set(player.getUniqueId() + ".exp", getExp(player) + given);
        try {
            levelConfig.save(levelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        plugin.getScoreBoardManager().setScoreBoard(player);
    }

    public void levelUp(Player player) {
            levelConfig.set(player.getUniqueId() + ".exp", getExp(player) - getReqExp(player));
            levelConfig.set(player.getUniqueId() + ".level", getLevel(player) + 1);
            levelConfig.set(player.getUniqueId() + ".required-exp", getReqExp(player) + 1250);
            try {
                levelConfig.save(levelFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendTitle(ChatColor.translateAlternateColorCodes('&',
                    "&3&l >>> Новый уровень! <<< "), ChatColor.translateAlternateColorCodes('&',
                    "&e&l >>> Твой уровень - " + getLevel(player) + "! <<<"), 20, 60, 20);
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 5, 1);
            player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("Particles.level_up")), player.getLocation(), 1, 0, 5, 0);
    }

    public void rebirthUp(Player player) {
        Economy economy = plugin.economy;
        if (getRebirthPlayer(player) != maxRebirth) {
            if (economy.getBalance(player) >= getReqBalance(player)) {
                levelConfig.set(player.getUniqueId() + ".exp", 0);
                levelConfig.set(player.getUniqueId() + ".level", 0);
                levelConfig.set(player.getUniqueId() + ".required-exp", 100);
                if (getRebirthPlayer(player) == 0) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 1);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 12000.0);
                    setRebirth(RebirthList.One, player);
                } else if (getRebirthPlayer(player) == 1) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 2);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 17000.0);
                    setRebirth(RebirthList.Two, player);
                } else if (getRebirthPlayer(player) == 2) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 3);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 23000.0);
                    setRebirth(RebirthList.Three, player);
                } else if (getRebirthPlayer(player) == 3) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 4);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 28500.0);
                    setRebirth(RebirthList.Four, player);
                } else if (getRebirthPlayer(player) == 4) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 5);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 32000.0);
                    setRebirth(RebirthList.Five, player);
                } else if (getRebirthPlayer(player) == 5) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 6);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 39500.0);
                    setRebirth(RebirthList.Six, player);
                } else if (getRebirthPlayer(player) == 6) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 7);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 42000.0);
                    setRebirth(RebirthList.Seven, player);
                } else if (getRebirthPlayer(player) == 7) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 8);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 50000.0);
                    setRebirth(RebirthList.Eight, player);
                } else if (getRebirthPlayer(player) == 8) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 9);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 55500.0);
                    setRebirth(RebirthList.Nine, player);
                } else if (getRebirthPlayer(player) == 9) {
                    rebirthConfig.set(player.getUniqueId() + ".rebirth", 10);
                    rebirthConfig.set(player.getUniqueId() + "required.balance", getReqBalance(player) + 61000.0);
                    setRebirth(RebirthList.Ten, player);
                }
                economy.withdrawPlayer(player, getReqBalance(player));

                player.sendTitle(ChatColor.translateAlternateColorCodes('&',
                        "&3&l >>> Перерождение... <<< "), ChatColor.translateAlternateColorCodes('&',
                        "&e&l >>> Количество перерождений - " + getRebirthPlayer(player) + "! <<<"), 20, 60, 20);
                player.spawnParticle(Particle.valueOf(plugin.getConfig().getString("Particles.rebirth_up")), player.getLocation(), 1, 0, 5, 0);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 3, 1);
            } else {
                player.sendMessage(ChatColor.RED + "Недостаточно средств для перерождения!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 5, 1);
            }
        } else {
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cМаксимальное количество ребитхов - &l10!"));
        }
        try {
            levelConfig.save(levelFile);
            rebirthConfig.save(rebirthFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        plugin.getScoreBoardManager().setScoreBoard(player);
    }

    public static void setRebirth(RebirthList rebirth, Player player) {
        BreakGrassSimulator rebirthsystem = BreakGrassSimulator.getInstance();
        FileConfiguration config = rebirthsystem.getConfig();
        String uuid = player.getUniqueId().toString();
        config.set(uuid, rebirth.name());
        rebirthsystem.saveConfig();

        RebithTag.setTag(player);
        RebithTag.newRebirth(player);
    }

    public List<String> loadTop() {
        Map<String, Integer> data = new HashMap<>();
        for (String key: levelConfig.getKeys(false)) {
            data.put(key, levelConfig.getInt(key + ".level"));
        }

        top.clear();
        top = data.entrySet().stream().sorted(Collections.reverseOrder(
                Map.Entry.comparingByValue()
        )).limit(100).map(Map.Entry :: getKey).collect(Collectors.toList());
        return top;
    }

    public int getLevel(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".level");
    }
    public int getLevel(OfflinePlayer player) {
        return levelConfig.getInt(player.getUniqueId() + ".level");
    }
    public int getExp(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".exp");
    }
    public int getReqExp(Player player) {
        return levelConfig.getInt(player.getUniqueId() + ".required-exp");
    }
    public String getName(OfflinePlayer player) {
        return levelConfig.getString(player.getUniqueId() + ".name");
    }

    public int getRebirthPlayer(Player player) {
        return rebirthConfig.getInt(player.getUniqueId() + ".rebirth");
    }
    public int getReqBalance(Player player) {
        return rebirthConfig.getInt(player.getUniqueId() + ".required-balance");
    }


}
