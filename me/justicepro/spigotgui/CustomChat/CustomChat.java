package me.justicepro.spigotgui.CustomChat;

import java.util.HashMap;

import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ProcessException;

public class CustomChat extends Module {

	public static String chat;
	
	public CustomChat() {
		super("CustomChat");
	}
	
	@Override
	public void onChatMessage(String message, String player) {
		
		if (message.startsWith("!")) {
			
			//chat = chat + "tellraw @a [{\"text\":\"" + player + " executed " + command + "\"}]\n";
			
			if (message.equalsIgnoreCase("!creative")) {
				//chat = chat + "tellraw @a [{\"text\":\"" + player + " executed " + command + "\"}]\n";
				try {
					sendCommand("gamemode creative " + player);
				} catch (ProcessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (message.equalsIgnoreCase("!survival")) {
				//chat = chat + "tellraw @a [{\"text\":\"" + player + " executed " + command + "\"}]\n";
				try {
					sendCommand("gamemode survival " + player);
				} catch (ProcessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else {
			chat = chat + "tellraw @a [{\"text\":\"" + player + ": " + message + "\"}]\n";
		}

		try {
			clearChat();
			printChat();
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sendCommand("tellraw @a [{\"text\":\"" + "--------------------------------------" + "\"}]");
			sendCommand("tellraw @a [\"\",{\"text\":\"[Creative]\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!creative\"}},{\"text\":\" \",\"color\":\"none\"},{\"text\":\"[Survival]\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"!survival\"}}]");
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clearChat() throws ProcessException {
		for (int i = 0; i < 100; i++) {
			sendCommand("tellraw @a [{\"text\":\"\"}]");
		}
	}

	public void printChat() throws ProcessException {
		
		for (String message : chat.split("\n")) {
			sendCommand(message);
		}
		
	}

}