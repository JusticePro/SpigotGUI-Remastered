package me.justicepro.spigotgui;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JMenuItem;

import me.justicepro.spigotgui.Api.ServerType;
import me.justicepro.spigotgui.Api.Version;
import me.justicepro.spigotgui.Core.SpigotGUI;
import me.justicepro.spigotgui.RemoteAdmin.Permission;
import me.justicepro.spigotgui.RemoteAdmin.User;
import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;

public class Module {
	
	private String name;
	private Version version;
	private ServerType serverType = ServerType.Unknown;
	
	/**
	 * Modules for SpigotGUI
	 * @param name The module name
	 */
	public Module(String name) {
		this.name = name;
	}
	
	/**
	 * @return The module name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The detected version
	 * @return The version of the running server.
	 */
	public Version getVersion() {
		return version;
	}
	
	/**
	 * Has bukkit been detected
	 * @return If bukkit has been detected.
	 */
	public boolean isBukkit() {
		return serverType.isBukkit();
	}
	
	/**
	 * @param message Raw Console Printed
	 */
	public void onConsolePrintRaw(String message) {
		
		if (message.split("INFO]: ").length>1) {
			onConsolePrint(message.split("INFO]: ")[1]);
		}
		
		if (message.split("INFO] ").length>1) {
			onConsolePrint(message.split("INFO] ")[1]);
		}
		
	}
	
	/**
	 * @return The type of server
	 */
	public ServerType getServerType() {
		return serverType;
	}
	
	/**
	 * @param message Message to send to console
	 * Messages to console that have "INFO]: " and removes them.
	 */
	public void onConsolePrint(String message) {
		
		if (message.startsWith("Starting minecraft server version ")) {
			onVersionDetected(message.split("Starting minecraft server version ")[1]);
			onServerInit();
		}
		
		if (message.startsWith("This server is running CraftBukkit ")) {
			onBukkitVersionDetected(message.split("This server is running CraftBukkit version ")[1].split(" ")[0]);
		}
		
		if (message.startsWith("[Sponge]: This server is running Sponge")) {
			System.out.println(message);
			onSpongeVersionDetected(message.split("running ")[1].split(" version")[0]);
		}
		
		if (version != null) {
			
			/**
			 * 1.7 Handler
			 */
			if (version.equals(Version.V17)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				
				if (message.contains(" lost connection: ")) {
					if (isBukkit()) {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
					}else {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1].split("TextComponent\\{text='")[1].split("'")[0]);
					}
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.8 Handler
			 */
			if (version.equals(Version.V18)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					if (isBukkit()) {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
					}else {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1].split("TextComponent\\{text='")[1].split("'")[0]);
					}
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.9 Handler
			 */
			if (version.equals(Version.V19)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					if (isBukkit()) {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
					}else {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1].split("TextComponent\\{text='")[1].split("'")[0]);
					}
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.10 Handler
			 */
			if (version.equals(Version.V110)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					if (isBukkit()) {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
					}else {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1].split("TextComponent\\{text='")[1].split("'")[0]);
					}
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.11 Handler
			 */
			if (version.equals(Version.V111)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					if (isBukkit()) {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
					}else {
						onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1].split("TextComponent\\{text='")[1].split("'")[0]);
					}
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.12 Handler
			 */
			if (version.equals(Version.V112)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\" or \"?\"")) {
					onServerReady();
				}
				
			}
			
			/**
			 * 1.13 Handler
			 */
			if (version.equals(Version.V113)) {
				
				if (message.startsWith("<")) {
					onChatMessage(message.split("<")[1].split("> ")[1], message.split("<")[1].split(">")[0]);
				}
				
				if (message.contains("issued server command: /")) {
					onCommandSent(message.split(" issued server command: /")[1], message.split(" issued server command: /")[0]);
				}
				
				if (message.contains(" logged in with entity id ")) {
					onPlayerJoin(message.split("\\[/")[0], message.split("\\[/")[1].split(":")[0]);
				}
				
				if (message.contains(" lost connection: ")) {
					onPlayerLeave(message.split(" lost connection: ")[0], message.split(" lost connection: ")[1]);
				}
				
				if (message.startsWith("Done ") && message.endsWith("! For help, type \"help\"")) {
					onServerReady();
				}
				
			}
			
		}
		
	}
	
	/**
	 * When the version has been detected.
	 * @param version The version string
	 */
	public void onVersionDetected(String version) {
		
		for (Version v : Version.values()) {
			
			if (version.startsWith(v.getName())) {
				this.version = v;
				break;
			}
			
		}
		
	}
	
	/**
	 * When bukkit is detected.
	 * @param version The version detected.
	 */
	public void onBukkitVersionDetected(String version) {
		
		if (version.toLowerCase().contains("spigot")) {
			serverType = ServerType.Spigot;
		}else {
			serverType = ServerType.Bukkit;
		}
		
	}
	
	/**
	 * When sponge is detected.
	 * @param version The version detected.
	 */
	public void onSpongeVersionDetected(String version) {
		
		if (version.equalsIgnoreCase("SpongeVanilla")) {
			serverType = ServerType.SpongeVanilla;
		}else if (version.equalsIgnoreCase("SpongeForge")) {
			serverType = ServerType.SpongeForge;
		}
		
	}
	
	/**
	 * @param message Message Sent
	 * @param player The Player that sent the message
	 */
	public void onChatMessage(String message, String player) {}
	
	/**
	 * When the server is ready.
	 */
	public void onServerReady() {}
	
	/**
	 * When the server is initiated.
	 */
	public void onServerInit() {}
	
	/**
	 * @param message Command Sent
	 * @param player The Player that Sent the Command
	 * Bukkit and Spigot
	 */
	public void onCommandSent(String message, String player) {}
	
	// [09:07:22 INFO]: JusticePro[/127.0.0.1:51536] logged in with entity id 208 at ([world]-134.42308268456057, 72.0, 212.69999998807907)
	/**
	 * @param player The Player that joined.
	 * @param ip The ip of the player.
	 */
	public void onPlayerJoin(String player, String ip) {}
	
	// [11:00:47 INFO]: JusticePro lost connection: We're Under Maintenance
	/**
	 * @param player The Player that left.
	 * @param reason The reason of disconnect
	 */
	public void onPlayerLeave(String player, String reason) {}
	
	/**
	 * @param command Command to Send
	 * @throws ProcessException
	 */
	public void sendCommand(String command) throws ProcessException {
		
		if (SpigotGUI.server != null) {
			SpigotGUI.server.sendCommand(command);
		}else {
			throw new ProcessException();
		}
		
	}
	
	/**
	 * When the server closes.
	 */
	public void onServerClosed() {
		serverType = ServerType.Unknown;
		version = null;
	}
	
	/**
	 * When the module is loaded.
	 */
	public void init() {
		
		if (getPermissions() != null) {
			
			for (Permission permission : getPermissions()) {
				User.registeredPermissions.add(permission);
			}
			
		}
		
	}
	
	/**
	 * Menu Items on Module Right Click
	 */
	public JMenuItem[] getMenuItems() {
		return null;
	}

	/**
	 * Menu Items on Module Right Click when in remote admin.
	 */
	public JMenuItem[] getRemoteMenuItems(RClient client) {
		return null;
	}

	/**
	 * Custom Panel for Main Window
	 * @return The page for your module.
	 */
	public JModulePanel getPage() {
		return null;
	}
	
	/**
	 * Custom Panel for Remote Admin Window
	 * @param client The active client.
	 * @return The remote admin page for your module.
	 */
	public JRemoteModulePanel getRemotePage(RClient client) {
		return null;
	}
	
	/**
	 * Custom Panel for Remote Admin Window
	 * @return The server manager page for your module.
	 */
	public JModulePanel getRemoteManagePage() {
		return null;
	}
	
	/**
	 * Registered Permissions
	 * @return The registered permissions.
	 */
	public Permission[] getPermissions() {
		return null;
	}
	
	/**
	 * @return The website that displays to download the module.
	 */
	public String getWebsite() {
		return null;
	}
	
	/**
	 * @return The instruction manual that'll show up in the help menu.
	 */
	public String getManual() {
		return null;
	}
	
	/**
	 * Get the content from an input stream and closes the stream.
	 * @param input The stream to read.
	 * @return The content.
	 */
	public static String getContent(InputStream input) throws IOException {
		String content = "";
		
		int b1;
		while ((b1 = input.read()) != -1) {
			content = content + (char)b1;
		}
		
		input.close();
		
		return content;
	}
	
}