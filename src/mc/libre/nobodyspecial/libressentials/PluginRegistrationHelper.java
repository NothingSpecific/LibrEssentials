package mc.libre.nobodyspecial.libressentials;

import mc.libre.nobodyspecial.libressentials.command.*;

public class PluginRegistrationHelper {
	public static void registerPlugins(LibrEssentials parent) {
		L1CommandHandler handler = parent.getL1CommandHandler();
		handler.registerL2Handler(new ToggleNCR(parent));
	}
}
