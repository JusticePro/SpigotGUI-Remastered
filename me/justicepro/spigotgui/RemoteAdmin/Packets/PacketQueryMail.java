package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Mail;
import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketQueryMail extends Packet {
	
	private String id;
	
	public PacketQueryMail(Packet packet) {
		super("mail_query", packet.getData());
		id = packet.getData();
	}
	
	public PacketQueryMail(int id) {
		super("mail_query", id + "");
		this.id = id + "";
	}
	
	public String getId() {
		return id;
	}
	
	public boolean isNumber() {
		try {
			Integer.parseInt(id);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
}