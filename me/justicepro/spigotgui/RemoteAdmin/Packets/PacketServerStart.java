package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketServerStart extends Packet {

	private String arguments, switches;
	private boolean acceptEula;
	private boolean functionalPacket = true;

	public PacketServerStart(Packet packet) {
		super("server_start", packet.getData());
		
		try {
			this.switches = getData().split("&")[0].replaceAll(";", "");
			this.arguments = getData().split("&")[1].replaceAll(";", "");
			this.acceptEula = getData().split("&")[2].replaceAll(";", "").equalsIgnoreCase("true");
		}catch (ArrayIndexOutOfBoundsException e) {
			functionalPacket = false;
		}
		
	}

	public PacketServerStart(String switches, String arguments) {
		this(switches, arguments, false);
	}

	public PacketServerStart(String switches, String arguments, boolean acceptEula) {
		super("server_start", switches + "&" + arguments + "&" + acceptEula);
		this.switches = switches;
		this.arguments = arguments;
		this.acceptEula = acceptEula;
	}
	
	public boolean doesAcceptEula() {
		return acceptEula;
	}

	public String getSwitches() {
		return switches;
	}

	public String getArguments() {
		return arguments;
	}
	
	public boolean isFunctionalPacket() {
		return functionalPacket;
	}

}