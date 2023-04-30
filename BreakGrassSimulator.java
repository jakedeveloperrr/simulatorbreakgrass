package dev.jake.breakgrasssimulator;

import dev.jake.breakgrasssimulator.baseSetup.*;
import dev.jake.breakgrasssimulator.levelsystem.*;
import dev.jake.breakgrasssimulator.rebirthSetup.*;
import dev.jake.breakgrasssimulator.systemmenu.EventClassForMenu;
import dev.jake.breakgrasssimulator.systemmenu.MenuClass;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class BreakGrassSimulator extends JavaPlugin implements Listener {

    private static BreakGrassSimulator instance;

    public static BreakGrassSimulator getInstance() {
        return instance;
    }

    private LevelManager levelManager;
    private LevelManager rebithManager;
    public Economy economy;
    public ArrayList<Player> invsList = new ArrayList<>();
    private ScoreBoardManager scoreBoardManager;
    public ScoreBoardManager getScoreBoardManager() {
        return scoreBoardManager;
    }
    private static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public static HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }
    // getter levelmanager
    public LevelManager getLevelManager() {
        return levelManager;
    }

    @Override
    public void onEnable() {

        // >> Settings Plugin <<


        levelManager = new LevelManager(this);
        levelManager.loadLevelFile();
        scoreBoardManager = new ScoreBoardManager(this);
        instance = this;
        rebithManager = new LevelManager(this);


        // >> Settings Configuration <<

        saveDefaultConfig();


        // >> Console Message <<

        // check Vault
        getLogger().info("Grass Simulator >>> Check have vault in this server...");
        if (!setupEconomy()) {
            getLogger().info("Grass Simulator >>> No vault in this server!");
            getLogger().info("Grass Simulator >>> Plugin is off");
            // off plugin
            getServer().getPluginManager().disablePlugin(this);
        }
        else {
            // on plugin
            getLogger().info("Grass Simulator >>> Im ready to game!");
        }

        // >> Settings SRC <<

        getServer().getPluginManager().registerEvents(new InventoryClickInGame(this), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(this), this);
        // vanish command
        getCommand("va").setExecutor(new Vanish(this));
        // menu
        getServer().getPluginManager().registerEvents(new EventClassForMenu(this), this);
        getCommand("m").setExecutor(new MenuClass(this));
        // levels system
        getServer().getPluginManager().registerEvents(new Listenre(this), this);
        getCommand("level").setExecutor(new GuiList(this));
        getCommand("levelup").setExecutor(new LevelUpCommand(this));
        getServer().getPluginManager().registerEvents(new LevelUpListener(this), this);
        // setCancelled(true)
        getServer().getPluginManager().registerEvents(new InvListener(), this);
        // rebirth system
        getCommand("rebirth").setExecutor(new CmdGuiReb(this));
        getServer().getPluginManager().registerEvents(new RebirthListener(this), this);
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("bonus").setExecutor(new EventPlus(this));

    }


    // getter rebirthmanager
    public LevelManager getRebirthManager() {
        return rebithManager;
    }


    // setting Vault API
    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> registeredServiceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (registeredServiceProvider == null)
            return false;

        economy = registeredServiceProvider.getProvider();
        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info("Grass Simulator >>> GoodBye!");

        perms.clear();
    }

}
