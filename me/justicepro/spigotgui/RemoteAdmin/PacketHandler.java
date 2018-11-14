package me.justicepro.spigotgui.RemoteAdmin;

import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;
import me.justicepro.spigotgui.RemoteAdmin.Server.RConnection;
import me.justicepro.spigotgui.RemoteAdmin.Server.RServer;

public interface PacketHandler {
	
	public void onPacketRecievedServer(Packet packet, RConnection connection, RServer server);

	public void onPacketRecievedClient(Packet packet, RClient client);
	
}