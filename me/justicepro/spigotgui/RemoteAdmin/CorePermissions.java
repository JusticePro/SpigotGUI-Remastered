package me.justicepro.spigotgui.RemoteAdmin;

public class CorePermissions {
	
	public static final Permission CONSOLE_READ = new Permission("Console Read", "spigotgui.core.console.read");
	public static final Permission CONSOLE_SEND = new Permission("Console Send", "spigotgui.core.console.send");
	
	public static final Permission CHAT_READ = new Permission("Chat Read", "spigotgui.core.chat.read");
	public static final Permission CHAT_SEND = new Permission("Chat Send", "spigotgui.core.chat.send");
	
	public static final Permission ADMIN = new Permission("Server Admin", "spigotgui.core.server.admin");
	public static final Permission ALLOW_BOT = new Permission("Allow Bots", "spigotgui.core.server.allowbot");
	
}