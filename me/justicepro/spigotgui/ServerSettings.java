package me.justicepro.spigotgui;

import java.io.Serializable;

public class ServerSettings implements Serializable {
	
	private Object minRam;
	private Object maxRam;
	private String customArgs;
	private String customSwitches;
	private String jarFilePath;
	
	public ServerSettings(Object minRam, Object maxRam, String customArgs, String customSwitches, String jarFilePath) {
		this.minRam = minRam;
		this.maxRam = maxRam;
		
		this.customArgs = customArgs;
		this.customSwitches = customSwitches;
		this.jarFilePath = jarFilePath;
	}
	
	public Object getMinRam() {
		return minRam;
	}
	
	public Object getMaxRam() {
		return maxRam;
	}
	
	public String getCustomArgs() {
		return customArgs;
	}
	
	public String getCustomSwitches() {
		return customSwitches;
	}

	public String getJarFilePath() {
		return jarFilePath;
	}
	
	public void setCustomArgs(String customArgs) {
		this.customArgs = customArgs;
	}
	
	public void setCustomSwitches(String customSwitches) {
		this.customSwitches = customSwitches;
	}
	
	public void setMaxRam(Object maxRam) {
		this.maxRam = maxRam;
	}
	
	public void setMinRam(Object minRam) {
		this.minRam = minRam;
	}
	
	public void setJarFilePath(String jarFilePath) {
		this.jarFilePath = jarFilePath;
	}
	
	public static ServerSettings getDefault() {
		return new ServerSettings(1024, 1024, "", "", "server.jar");
	}
	
}