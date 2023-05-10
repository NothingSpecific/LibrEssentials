package mc.libre.nobodyspecial.libressentials;

import mc.libre.nobodyspecial.libressentials.command.*;

public class CommandRegistrationHelper {
	public static void registerCommands(LibrEssentials parent) {
		L1CommandHandler handler = parent.getL1CommandHandler();
		handler.registerL2Handler(new ToggleNCR(parent));
		handler.registerL2Handler(new AFK(parent));
	}
}
