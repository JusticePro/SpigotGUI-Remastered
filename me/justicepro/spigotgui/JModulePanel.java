package me.justicepro.spigotgui;

import javax.swing.JPanel;

public class JModulePanel extends JPanel {
	
	private String title;
	
	public JModulePanel(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
}