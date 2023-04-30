package dev.jake.breakgrasssimulator.rebirthSetup;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import dev.jake.breakgrasssimulator.levelsystem.GuiLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGuiReb implements CommandExecutor {

    private final BreakGrassSimulator plugin;
    public CmdGuiReb(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            new GuiRebirth(player, plugin);
        }

        return false;
    }
}
