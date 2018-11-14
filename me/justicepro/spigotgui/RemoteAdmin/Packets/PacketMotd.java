package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketMotd extends Packet {
	
	private String title;
	private String body;
	
	public PacketMotd(Packet packet) {
		super("motd", packet.getData());
		this.title = packet.getData().split("&")[0];
		this.body = packet.getData().split("&")[1];
		
	}
	
	public PacketMotd(String title, String body) {
		super("motd", title + "&" + body);
		this.title = title;
		this.body = body;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBody() {
		return body;
	}
	
}