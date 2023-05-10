package mc.libre.nobodyspecial.libressentials.command;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Map;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import mc.libre.nobodyspecial.libressentials.Config;
import mc.libre.nobodyspecial.libressentials.DeprecatedConstructorException;
import mc.libre.nobodyspecial.libressentials.L2CommandHandler;
import mc.libre.nobodyspecial.libressentials.LibrEssentials;
import mc.libre.nobodyspecial.libressentials.runnable.PlayerAfkKicker;

public class AFK extends L2CommandHandler implements Listener {
	private Map<String, Long> playerLastActionTimes;
	private Map<String, Long> playerAfkTimes;
	
	PlayerAfkKicker afkKicker;
	
	public AFK(LibrEssentials parent) {
		super(parent);
		afkKicker = new PlayerAfkKicker(parent, this);
		/*
		 * We can save a bit of processing if we avoid running this task unnecessarily
		 */
		if(Config.playerAfkSeconds > 0 || Config.playerAfkKickSeconds > 0)
			afkKicker.runTaskTimer(parent, 0, 1);
		
		playerLastActionTimes = new HashMap<>();
		playerAfkTimes = new HashMap<>();
	}
	
	@Override
	public void onDisable() {
		afkKicker.cancel();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		String player = sender.getName();
		if(isAfk(player)) {
			unAfk(player);
		} else {
			afk(player);
		}
		return true;
	}

	@Override
	public boolean hasPermission(CommandSender sender, Command command, String alias, String[] args) {
		return sender.hasPermission("essentials.afk");
	}

	@Override
	public String getCommand() {
		return "afk";
	}

	@EventHandler(priority=EventPriority.MONITOR)
	public void onMove(PlayerMoveEvent e) {
		unAfk(e.getPlayer().getName());
	}
    @EventHandler(priority=EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent e){
		unAfk(e.getPlayer().getName());
    }
    @EventHandler(priority=EventPriority.MONITOR)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
    	if(!event.getMessage().strip().equals("/afk"))
    		unAfk(event.getPlayer().getName());
    }
	@EventHandler(priority=EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent e) {
		String player = e.getPlayer().getName();
		playerLastActionTimes.put(player, Instant.now().getEpochSecond());
	}
	@EventHandler(priority=EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent e) {
		String player = e.getPlayer().getName();
		playerLastActionTimes.remove(player);
		playerAfkTimes.remove(player);
	}
	
	public Map<String, Long> getPlayerLastActionTimes() {
		return playerLastActionTimes;
	}
	public Map<String, Long> getPlayerAfkTimes() {
		return playerAfkTimes;
	}
	public void afk(String player) {
		if(isAfk(player)) return;
		String displayName = parent.getServer().getPlayerExact(player).getDisplayName();
		playerAfkTimes.put(player, Instant.now().getEpochSecond());
		for(Player p : parent.getServer().getOnlinePlayers()) {
			p.sendMessage(Config.playerAfkMessage.replace("{PLAYER}", displayName));
		}
		parent.getServer().getConsoleSender().sendMessage(Config.playerAfkMessage.replace("{PLAYER}", displayName));
	}
	public void unAfk(String player) {
		if(isAfk(player)) {
			String displayName = parent.getServer().getPlayerExact(player).getDisplayName();
			for(Player p : parent.getServer().getOnlinePlayers()) {
				p.sendMessage(Config.playerUnAfkMessage.replace("{PLAYER}", displayName));
			}
			parent.getServer().getConsoleSender().sendMessage(Config.playerUnAfkMessage.replace("{PLAYER}", displayName));
		}
		unAfkSilent(player);
	}
	public void unAfkSilent(String player) {
		playerAfkTimes.remove(player);
		playerLastActionTimes.put(player, Instant.now().getEpochSecond());
	}
	public boolean isAfk(String player) {
		return playerAfkTimes.containsKey(player);
	}
}
