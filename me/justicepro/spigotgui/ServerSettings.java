package me.justicepro.spigotgui;

import java.io.File;
import java.io.Serializable;

public class ServerSettings implements Serializable {
	
	private Object minRam;
	private Object maxRam;
	private String customArgs;
	private String customSwitches;
	private File jarFile;
	
	public ServerSettings(Object minRam, Object maxRam, String customArgs, String customSwitches, File jarFile) {
		this.minRam = minRam;
		this.maxRam = maxRam;
		
		this.customArgs = customArgs;
		this.customSwitches = customSwitches;
		this.jarFile = jarFile;
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

	public File getJarFile() {
		return jarFile;
	}
	
	public void setCustomArgs(String customArgs) {
		this.customArgs = customArgs;
	}
	
	public void setCustomSwitches(String customSwitches) {
		this.customSwitches = customSwitches;
	}
	
	public void setJarFile(File jarFile) {
		this.jarFile = jarFile;
	}
	
	public void setMaxRam(Object maxRam) {
		this.maxRam = maxRam;
	}
	
	public void setMinRam(Object minRam) {
		this.minRam = minRam;
	}
	
	public static ServerSettings getDefault() {
		return new ServerSettings(1024, 1024, "", "", new File("server.jar"));
	}
	
}