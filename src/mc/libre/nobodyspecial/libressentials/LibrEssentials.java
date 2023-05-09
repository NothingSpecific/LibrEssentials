package mc.libre.nobodyspecial.libressentials;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LibrEssentials extends JavaPlugin {
	private Server server = null;
	private FileConfiguration config = null;
	private L1CommandHandler l1ch;
	
	private String permissionErrorMessage = "§l§eLibrE§l§cssentials§r§7:§r §4Permission error§r.";
	private boolean blockChatReports = true; // Default to true unless server admin specifies otherwise
	
    @Override
    public void onEnable() {
    	server = getServer();
    	server.getPluginManager().registerEvents(new PlayerChatRewriter(this), this);
    	l1ch = new L1CommandHandler(this);
    	l1ch.onEnable();
    	PluginRegistrationHelper.registerPlugins(this);
    }
    @Override
    public void onLoad() {
    	config = getConfig();
    	config.addDefault("permissionErrorMessage", permissionErrorMessage);
    	permissionErrorMessage = config.getString("permissionErrorMessage");
    	config.set("permissionErrorMessage", permissionErrorMessage);
    	
    	config.addDefault("blockChatReports", blockChatReports);
    	blockChatReports = config.getBoolean("blockChatReports");
    	config.set("blockChatReports", blockChatReports);
    	
    	saveConfig();
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	saveConfig();
    }
    public void sendPermissionError(CommandSender sender) {
    	sender.sendMessage(permissionErrorMessage);
    }
    public boolean shouldBlockChatReports() {
    	return blockChatReports;
    }
    public void setBlockChatReports(boolean blockChatReports) {
    	if(blockChatReports)
    		this.blockChatReports = blockChatReports;
    	config.set("blockChatReports", blockChatReports);
    	saveConfig();
    }
    public L1CommandHandler getL1CommandHandler() {
    	return l1ch;
    }
}