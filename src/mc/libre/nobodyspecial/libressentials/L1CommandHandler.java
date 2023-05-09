package mc.libre.nobodyspecial.libressentials;

import java.util.Map;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Layer 1 handler for commands issued to this plugin
 * The L1CommandHandler identified which L2CommandHandler is responsible for executing the specified command,
 * checks if the CommandSender has permission to execute the command, and if yes, passes the command to the
 * L2CommandHandler responsible for executing the command
 * 
 * Is this necessary? Not strictly. But if I'm reimplementing a plugin as large as Essentials I'm making my
 * life easier by adding an abstraction layer to handle command registration
 * 
 * Also, the custom permission checking this enables allows more flexibility in the future
 *  
 * @author NothingSpecific
 *
 */
public class L1CommandHandler implements CommandExecutor {
	private Map<String, Map<String, Object>> commands;
	private Map<String, L2CommandHandler> commandHandlers = new HashMap<>();
	private LibrEssentials parent;
	public L1CommandHandler(LibrEssentials parent) {
		this.parent = parent;
	}
	public void onEnable() {
		commands = parent.getDescription().getCommands();
		for(String s : commands.keySet()) {
			parent.getCommand(s).setExecutor(this);
		}
	}

	/**
	 * Registers a command and all its aliases to a given L2CommandHandler
	 * This will infer the the command to be registered from the L2CommandHandler's 
	 * 
	 * @param l2Handler The L2CommandHandler responsible for handling the command
	 */
	public void registerL2Handler(L2CommandHandler l2Handler) {
		String command = l2Handler.getCommand();
		PluginCommand c = parent.getCommand(command);
		commandHandlers.put(command, l2Handler);
		commandHandlers.put(c.getName(), l2Handler);
		commandHandlers.put(c.getLabel(), l2Handler);
		for(String s : c.getAliases()){
			commandHandlers.put(s, l2Handler);
		}
	}
	/**
	 * Registers a command and all its aliases to a given L2CommandHandler
	 * 
	 * @param l2Handler The L2CommandHandler responsible for handling the command
	 * @param command The command to be handled by l2Handler
	 */
	public void registerL2Handler(L2CommandHandler l2Handler, String command) {
		PluginCommand c = parent.getCommand(command);
		commandHandlers.put(command, l2Handler);
		commandHandlers.put(c.getName(), l2Handler);
		commandHandlers.put(c.getLabel(), l2Handler);
		for(String s : c.getAliases()){
			commandHandlers.put(s, l2Handler);
		}
	}
	/**
	 * Registers a command to be handled by a given L2CommandHandler, but does not register any aliases
	 * Useful if you want certain aliases to be handled by a different handler
	 * You can also pass an alias as `command` and the alias will be registered to l2Handler
	 * 
	 * @param l2Handler The L2CommandHandler responsible for handling the command/alias
	 * @param command The command or alias to be handled by l2Handler
	 */
	public void registerL2HandlerIgnoreAliases(L2CommandHandler l2Handler, String command) {
		PluginCommand c = parent.getCommand(command);
		commandHandlers.put(command, l2Handler);
	}
	/**
	 * Executes the command. This method is called by the L1CommandSender responsible for handling LibrEssentials commands
	 * 
	 * @param sender The player who sent the command
	 * @param command The Command object representing the command to be executed
	 * @param alias The command alias which was invoked
	 * @param args Any arguments passed to the command
	 * @return True if the command is valid, false if the command is invalid or the player doesn't have the required permission
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		L2CommandHandler handler = commandHandlers.get(alias);
		if(handler == null) return false;
		if(handler.hasPermission(sender, command, alias, args)) {
			handler.onCommand(sender, command, alias, args);
		} else {
			parent.sendPermissionError(sender);
		}
		return true;
	}
}
