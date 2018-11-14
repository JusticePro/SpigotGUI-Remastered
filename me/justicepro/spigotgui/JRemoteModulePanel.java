package me.justicepro.spigotgui;

import javax.swing.JPanel;

import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;

public class JRemoteModulePanel extends JPanel {
	
	private String title;
	private RClient client;
	
	public JRemoteModulePanel(String title, RClient client) {
		this.title = title;
		this.client = client;
	}
	
	public RClient getClient() {
		return client;
	}
	
	public String getTitle() {
		return title;
	}
	
}