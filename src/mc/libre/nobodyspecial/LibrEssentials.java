package mc.libre.nobodyspecial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LibrEssentials extends JavaPlugin {
    @Override
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}