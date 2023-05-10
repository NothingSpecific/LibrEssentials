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
	
    @Override
    public void onEnable() {
    	server = getServer();
    	server.getPluginManager().registerEvents(new PlayerChatRewriter(this), this);
    	l1ch = new L1CommandHandler(this);
    	l1ch.onEnable();
    	CommandRegistrationHelper.registerCommands(this);
    }
    @Override
    public void onLoad() {
    	config = getConfig();
    	config.addDefault("permissionErrorMessage", Config.permissionErrorMessage);
    	Config.permissionErrorMessage = config.getString("permissionErrorMessage");
    	config.set("permissionErrorMessage", Config.permissionErrorMessage);

    	config.addDefault("blockChatReports", Config.blockChatReports);
    	Config.blockChatReports = config.getBoolean("blockChatReports");
    	config.set("blockChatReports", Config.blockChatReports);

    	config.addDefault("playerAfkSeconds", Config.playerAfkSeconds);
    	Config.playerAfkSeconds = config.getLong("playerAfkSeconds");
    	config.set("playerAfkSeconds", Config.playerAfkSeconds);

    	config.addDefault("playerAfkKickSeconds", Config.playerAfkKickSeconds);
    	Config.playerAfkKickSeconds = config.getLong("playerAfkKickSeconds");
    	config.set("playerAfkKickSeconds", Config.playerAfkKickSeconds);
    	

    	config.addDefault("playerAfkKickMessage", Config.playerAfkKickMessage);
    	Config.playerAfkKickMessage = config.getString("playerAfkKickMessage");
    	config.set("playerAfkKickMessage", Config.playerAfkKickMessage);

    	config.addDefault("playerAfkKickedMessage", Config.playerAfkKickedMessage);
    	Config.playerAfkKickedMessage = config.getString("playerAfkKickedMessage");
    	config.set("playerAfkKickedMessage", Config.playerAfkKickedMessage);

    	config.addDefault("playerAfkMessage", Config.playerAfkMessage);
    	Config.playerAfkMessage = config.getString("playerAfkMessage");
    	config.set("playerAfkMessage", Config.playerAfkMessage);
    	
    	config.addDefault("playerUnAfkMessage", Config.playerUnAfkMessage);
    	Config.playerUnAfkMessage = config.getString("playerUnAfkMessage");
    	config.set("playerUnAfkMessage", Config.playerUnAfkMessage);
    	
    	saveConfig();
    }
    @Override
    public void onDisable() {
    	saveConfig();
    }
    public void sendPermissionError(CommandSender sender) {
    	sender.sendMessage(Config.permissionErrorMessage);
    }
    public void setBlockChatReports(boolean blockChatReports) {
    	if(blockChatReports)
    		Config.blockChatReports = blockChatReports;
    	config.set("blockChatReports", blockChatReports);
    	saveConfig();
    }
    public L1CommandHandler getL1CommandHandler() {
    	return l1ch;
    }
}