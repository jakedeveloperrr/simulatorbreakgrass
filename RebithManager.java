package dev.jake.breakgrasssimulator.rebirthSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.levelsystem.LevelManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RebithManager {

    public final BreakGrassSimulator plugin;
    public RebithManager(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }

    public static RebirthList getRebirth(Player player) {
        BreakGrassSimulator rebirthsystem = BreakGrassSimulator.getInstance();
        FileConfiguration config = rebirthsystem.getConfig();

        return RebirthList.valueOf(config.getString(player.getUniqueId().toString()));
    }

}
