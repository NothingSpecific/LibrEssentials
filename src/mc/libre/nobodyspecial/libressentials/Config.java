package mc.libre.nobodyspecial.libressentials;

public abstract class Config {
	public static String banner = "§r§l§eLibrE§l§cssentials§r";

	public static String permissionErrorMessage = Config.banner + "§7:§r §4Permission error§r.";
	public static boolean blockChatReports = true; // Default to true unless server admin specifies otherwise
	
	public static long playerAfkSeconds = 60*5;
	public static long playerAfkKickSeconds = 60*5;
	public static String playerAfkMessage = banner + "§7:§r {PLAYER}§r is now AFK.";
	public static String playerUnAfkMessage = banner + "§7:§r {PLAYER}§r is no longer AFK.";
	public static String playerAfkKickMessage = banner + "§7:§r You were kicked for AFKing too long.";
	public static String playerAfkKickedMessage = banner + "§7:§r Player§r {PLAYER}§r kicked for AFKing too long.";
	
}
