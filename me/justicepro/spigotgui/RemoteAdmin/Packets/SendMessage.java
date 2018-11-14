package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class SendMessage extends Packet {
	
	private String message;
	
	public SendMessage(Packet packet) {
		super("serverchat_send", packet.getData());
		this.message = packet.getData();
	}
	
	public SendMessage(String message) {
		super("serverchat_send", message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}