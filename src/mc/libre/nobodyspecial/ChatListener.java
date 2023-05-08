package mc.libre.nobodyspecial;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	/*
	 * Dummy method for now. Once BetterNCR is completed this will use its implementation verbatim
	 */
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
    	return;
/*            //e.setCancelled(true);

            for (Player toPlayer : Bukkit.getOnlinePlayers()) {
                toPlayer.sendMessage("LE: " + String.format(e.getFormat(), e.getPlayer().getDisplayName(), e.getMessage()));
            }

            return;*/
    }
}
