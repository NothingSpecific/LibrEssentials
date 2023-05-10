package mc.libre.nobodyspecial.libressentials.runnable;

import java.time.Instant;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import mc.libre.nobodyspecial.libressentials.Config;
import mc.libre.nobodyspecial.libressentials.LibrEssentials;
import mc.libre.nobodyspecial.libressentials.command.AFK;

public class PlayerAfkKicker extends BukkitRunnable {
	LibrEssentials plugin;
	AFK parent;
	
	public PlayerAfkKicker(LibrEssentials plugin, AFK parent) {
		this.plugin = plugin;
		this.parent = parent;
	}

	@Override
	public void run() {
		long now = Instant.now().getEpochSecond();
		Map<String, Long> playerLastActionTimes = parent.getPlayerLastActionTimes();
		Map<String, Long> playerAfkTimes = parent.getPlayerAfkTimes();
		if(Config.playerAfkSeconds > 0)
			for(String s : playerLastActionTimes.keySet()) {
				if(now - playerLastActionTimes.get(s) > Config.playerAfkSeconds)
					parent.afk(s);
			}
		if(Config.playerAfkKickSeconds > 0)
			for(String s : playerAfkTimes.keySet()) {
				if(now - playerAfkTimes.get(s) > Config.playerAfkKickSeconds) {
					Player player = plugin.getServer().getPlayerExact(s);
					player.kickPlayer(Config.playerAfkKickMessage.replace("{PLAYER}", player.getDisplayName()));
					plugin.getServer().getConsoleSender().sendMessage(Config.playerAfkKickedMessage.replace("{PLAYER}", player.getDisplayName()));
				}
			}
	}

}
