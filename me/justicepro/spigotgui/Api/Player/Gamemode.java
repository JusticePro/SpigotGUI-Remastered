package me.justicepro.spigotgui.Api.Player;

public enum Gamemode {
	
	SURVIVAL("survival", "s", 0),
	CREATIVE("creative", "c", 1),
	ADVENTURE("adventure", "a", 2),
	SPECTATOR("spectator", "sp", 3),
	
	;
	
	private String full;
	private String shortened;
	private int id;
	
	private Gamemode(String full, String shortened, int id) {
		this.full = full;
		this.shortened = shortened;
		this.id = id;
	}
	
	/**
	 * @return The full name of the gamemode.
	 */
	public String getFull() {
		return full;
	}
	
	/**
	 * @return The shortened name of the gamemode.
	 */
	public String getShortened() {
		return shortened;
	}
	
	/**
	 * @return The id of the gamemode.
	 */
	public int getId() {
		return id;
	}
	
}