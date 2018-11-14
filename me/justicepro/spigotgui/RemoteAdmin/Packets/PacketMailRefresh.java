package me.justicepro.spigotgui.RemoteAdmin.Packets;

import java.util.ArrayList;

import me.justicepro.spigotgui.RemoteAdmin.Mail;
import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketMailRefresh extends Packet {

	private ArrayList<String> mailList;

	public PacketMailRefresh(Packet packet) {
		super("mail_refresh", packet.getData());

		this.mailList = new ArrayList<>();

		for (String s : getData().split("&")) {
			mailList.add(
					new Mail(s.split(":")[0], s.split(":")[1]).getSubject());
		}

	}

	public PacketMailRefresh(ArrayList<Mail> mailList) {
		super("mail_refresh", mailToString(mailList));
		this.mailList = new ArrayList<>();
		for (String s : mailToString(mailList).split("&") ) {
			this.mailList.add(s);
		}
	}

	public ArrayList<String> getMailList() {
		return mailList;
	}

	private static String mailToString(ArrayList<Mail> mailList) {
		String text = "";

		if (mailList.size()==0) {
			mailList.add(new Mail("No Mail", "No Mail"));
		}

		for (Mail mail : mailList) {
			text = text + mail.getSubject() + ":" + mail.getBody() + "&";
			System.out.println(mail.getSubject());
		}
		text = text.substring(0, text.length() - 1);



		return text;
	}

}