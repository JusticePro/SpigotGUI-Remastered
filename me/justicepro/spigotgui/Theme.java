package me.justicepro.spigotgui;

import javax.swing.LookAndFeel;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public enum Theme {
	Graphite("Graphite", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel"),
	Aluminium("Aluminium", "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"),
	HiFi("HiFi", "com.jtattoo.plaf.hifi.HiFiLookAndFeel"),
	Noire("Noire", "com.jtattoo.plaf.noire.NoireLookAndFeel"),
	Texture("Texture", "com.jtattoo.plaf.texture.TextureLookAndFeel"),
	Acryl("Acryl", "com.jtattoo.plaf.acryl.AcrylLookAndFeel"),
	Windows("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"),
	Aero("Aero", "com.jtattoo.plaf.aero.AeroLookAndFeel"),
	Bernstein("Bernstein", "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel"),
	Fast("Fast", "com.jtattoo.plaf.fast.FastLookAndFeel"),
	Luna("Luna", "com.jtattoo.plaf.luna.LunaLookAndFeel"),
	McWin("McWin", "com.jtattoo.plaf.mcwin.McWinLookAndFeel"),
	Metal("Metal", "javax.swing.plaf.metal.MetalLookAndFeel"),
	Mint("Mint", "com.jtattoo.plaf.mint.MintLookAndFeel"),
	Motif("Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel"),
	Smart("Smart", "com.jtattoo.plaf.smart.SmartLookAndFeel"),
//	Windows_Classic("Windows Classic", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"),
	
	;
	
	private String name;
	private String lookAndFeel;
	
	private Theme(String name, String lookAndFeel) {
		this.name = name;
		this.lookAndFeel = lookAndFeel;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getLookAndFeel() {
		return lookAndFeel;
	}
	
}