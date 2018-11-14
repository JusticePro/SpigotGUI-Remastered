package me.justicepro.spigotgui.Api.Player;

import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ProcessException;
import me.justicepro.spigotgui.Api.ServerType;
import me.justicepro.spigotgui.Api.Version;

public class Player {
	
	/**
	 * The player's name.
	 */
	private String name;
	
	/**
	 * The version of the server.
	 */
	private Version version;
	
	/**
	 * The type of the server.
	 */
	private ServerType type;
	
	/**
	 * Module to send commands through.
	 */
	private Module module;
	
	
	/**
	 * The 'Player' class is for making player commands easier for several versions.
	 * @param name The player's name.
	 * @param version The version of the server.
	 * @param type The type of the server.
	 * @param module Module to send commands through.
	 */
	public Player(String name, Version version, ServerType type, Module module) {
		this.name = name;
		this.version = version;
		this.type = type;
		this.module = module;
	}
	
	/**
	 * The 'Player' class is for making player commands easier for several versions.
	 * @param name The player's name.
	 * @param version The version of the server.
	 * @param module Module to send commands through.
	 */
	public Player(String name, Version version, Module module) {
		this(name, version, ServerType.Vanilla, module);
	}
	
	/**
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The type of the server.
	 */
	public ServerType getType() {
		return type;
	}
	
	/**
	 * @return The version of the server.
	 */
	public Version getVersion() {
		return version;
	}
	
	/**
	 * Set the gamemode for the player.
	 * @param gamemode The gamemode for the player.
	 * @throws ProcessException 
	 */
	public void setGamemode(Gamemode gamemode) throws ProcessException {
		module.sendCommand("gamemode " + gamemode.getFull() + " " + getName());
	}
	
}