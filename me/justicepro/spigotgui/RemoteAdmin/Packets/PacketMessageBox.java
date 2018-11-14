package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketMessageBox extends Packet {
	
	private String message;
	
	public PacketMessageBox(Packet packet) {
		super("msgbox", packet.getData());
		this.message = packet.getData();
	}
	
	public PacketMessageBox(String message) {
		super("msgbox", message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}