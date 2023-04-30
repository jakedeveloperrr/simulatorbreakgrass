package dev.jake.breakgrasssimulator.levelsystem;

import dev.jake.breakgrasssimulator.BreakGrassSimulator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiList implements CommandExecutor {
    private final BreakGrassSimulator plugin;
    public GuiList(BreakGrassSimulator plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            new GuiLevel(player, plugin);
        }
        return false;
    }
}
