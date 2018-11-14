package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Mail;
import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketMail extends Packet {

	private String to;
	private String subject;
	private String body;
	private boolean functionalPacket = true;
	
	public PacketMail(Packet packet) {
		super("mail", packet.getData());
		try {
			to = packet.getData().split("&")[0];
			subject = packet.getData().split("&")[1];
			body = packet.getData().split("&")[2];
		}catch (ArrayIndexOutOfBoundsException e) {
			functionalPacket = false;
		}
	}

	public PacketMail(String to, String subject, String body) {
		super("mail", to + "&" + subject + "&" + body);
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	public boolean isFunctionalPacket() {
		return functionalPacket;
	}
	
	public String getTo() {
		return to;
	}

	public String getBody() {
		return body;
	}

	public String getSubject() {
		return subject;
	}

	public Mail getMail() {
		return new Mail(subject, body);
	}

}