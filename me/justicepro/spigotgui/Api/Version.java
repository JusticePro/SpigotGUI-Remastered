package me.justicepro.spigotgui.Api;

public enum Version {
	
	V17("1.7"),
	V18("1.8"),
	V19("1.9"),
	V110("1.10"),
	V111("1.11"),
	V112("1.12"),
	V113("1.13"),
	
	;
	
	private String name;
	
	private Version(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}