package mc.libre.nobodyspecial.libressentials.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import mc.libre.nobodyspecial.libressentials.DeprecatedConstructorException;
import mc.libre.nobodyspecial.libressentials.L2CommandHandler;
import mc.libre.nobodyspecial.libressentials.LibrEssentials;

public class ToggleNCR extends L2CommandHandler {
	public ToggleNCR(LibrEssentials parent) {
		super(parent);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		boolean blockChatReports = !super.parent.getConfig().getBoolean("blockChatReports");
		super.parent.setBlockChatReports(blockChatReports);
		sender.sendMessage(blockChatReports ?
				"§l§eLibrE§l§cssentials§r§7:§r Chat reports §aBLOCKED§r." :
					
				"§l§eLibrE§l§cssentials§r§7:§r Chat reports §4ALLOWED§r.\n"
				+ "§6Changes will be applied on server restart§r.");
		return true;
	}

	@Override
	public boolean hasPermission(CommandSender sender, Command command, String alias, String[] args) {
		return sender.hasPermission("libressentials.admin.ncr");
	}

	@Override
	public String getCommand() {
		return "ncr";
	}
	
}
