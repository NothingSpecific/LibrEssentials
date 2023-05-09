package mc.libre.nobodyspecial.libressentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class L2CommandHandler {
	protected LibrEssentials parent;
	public L2CommandHandler(LibrEssentials parent) {
		this.parent = parent;
	}
	@Deprecated
	public L2CommandHandler() throws DeprecatedConstructorException {
		throw new DeprecatedConstructorException(this.getClass());
	}
	public abstract String getCommand();
	/**
	 * Executes the command. This method is called by the L1CommandSender responsible for handling LibrEssentials commands
	 * 
	 * @param sender The player who sent the command
	 * @param command The Command object representing the command to be executed
	 * @param alias The command alias which was invoked
	 * @param args Any arguments passed to the command
	 * @return True if the command is valid, false if the command is invalid
	 */
	public abstract boolean onCommand(CommandSender sender, Command command, String alias, String[] args);
	/**
	 * Executes the command. This method is called by the L1CommandSender responsible for handling LibrEssentials commands
	 * 
	 * @param sender The player who sent the command
	 * @param command The Command object representing the command to be executed
	 * @param alias The command alias which was invoked
	 * @param args Any arguments passed to the command
	 * @return True if the command is valid, false if the command is invalid
	 */
	public abstract boolean hasPermission(CommandSender sender, Command command, String alias, String[] args);
}