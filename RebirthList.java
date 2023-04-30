package dev.jake.breakgrasssimulator.rebirthSetup;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum RebirthList {
    Zero(ChatColor.translateAlternateColorCodes('&', "&l&70")),
    One(ChatColor.translateAlternateColorCodes('&', "&l&fI")),
    Two(ChatColor.translateAlternateColorCodes('&', "&l&7II")),
    Three(ChatColor.translateAlternateColorCodes('&', "&l&7III")),
    Four(ChatColor.translateAlternateColorCodes('&', "&l&3IV")),
    Five(ChatColor.translateAlternateColorCodes('&', "&l&3V")),
    Six(ChatColor.translateAlternateColorCodes('&', "&l&4VI")),
    Seven(ChatColor.translateAlternateColorCodes('&', "&l&4VII")),
    Eight(ChatColor.translateAlternateColorCodes('&', "&l&4VII")),
    Nine(ChatColor.translateAlternateColorCodes('&', "&l&5IX")),
    Ten(ChatColor.translateAlternateColorCodes('&', "&l&5X"));
    private String prefix;
    RebirthList(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

}
