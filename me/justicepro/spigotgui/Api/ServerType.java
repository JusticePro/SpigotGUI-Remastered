package me.justicepro.spigotgui.Api;

public enum ServerType {
	
	Bukkit(true),
	Spigot(true),
	
	SpongeVanilla(false),
	SpongeForge(false),
	Vanilla(false),
	
	Unknown(false),
	
	;
	
	private boolean bukkit;
	
	private ServerType(boolean bukkit) {
		this.bukkit = bukkit;
	}
	
	public boolean isBukkit() {
		return bukkit;
	}

	public String getName() {
		return name();
	}
	
}