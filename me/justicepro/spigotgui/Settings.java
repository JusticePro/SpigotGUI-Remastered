package me.justicepro.spigotgui;

import java.io.Serializable;

public class Settings implements Serializable {
	
	private ServerSettings serverSettings;
	private Theme theme;
	private Object fontSize;
	
	public Settings(ServerSettings serverSettings, Theme theme, Object fontSize) {
		this.serverSettings = serverSettings;
		this.theme = theme;
		this.fontSize = fontSize;
	}
	
	public ServerSettings getServerSettings() {
		return serverSettings;
	}
	
	public Object getFontSize() {
		return fontSize;
	}
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}