package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketLogin extends Packet {

	private String username, password;
	private boolean functionalPacket = true;

	public PacketLogin(Packet packet) {
		super("login", packet.getData());
		try {
			this.username = packet.getData().split("&")[0];
			this.password = packet.getData().split("&")[1];
		}catch (ArrayIndexOutOfBoundsException e) {
			functionalPacket = false;
		}
	}

	public PacketLogin(String username, String password) {
		super("login", username + "&" + password);
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean isFunctionalPacket() {
		return functionalPacket;
	}
	
}