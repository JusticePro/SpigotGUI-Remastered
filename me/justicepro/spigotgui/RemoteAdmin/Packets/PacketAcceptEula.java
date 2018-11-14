package me.justicepro.spigotgui.RemoteAdmin.Packets;

import me.justicepro.spigotgui.RemoteAdmin.Packet;

public class PacketAcceptEula extends Packet {
	
	public PacketAcceptEula(Packet packet) {
		super("acceptEula", " ");
	}
	
	public PacketAcceptEula() {
		super("acceptEula", " ");
	}
	
}