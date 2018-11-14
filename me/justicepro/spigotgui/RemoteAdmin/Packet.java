package me.justicepro.spigotgui.RemoteAdmin;

public class Packet {

	private String packetName;
	private String data;
	private String utf;
	
	/**
	 * The {@link Packet} is data that is sent to a server or user from a server or user.
	 * @param utf The Raw String
	 */
	public Packet(String utf)
	{
		String u = decode(utf);
		this.packetName = u.split("~")[0];
		this.data = u.split("~")[1];
		this.utf = utf;
	}
	
	/**
	 * The {@link Packet} is data that is sent to a server or user from a server or user.
	 * @param packetName Name of the packet.
	 * @param data The data sent
	 */
	public Packet(String packetName, String data)
	{
		this.packetName = packetName;
		this.data = data;
		utf = encode(packetName + "~" + data);
	}
	
	/**
	 * @return The data sent.
	 */
	public String getData()
	{
		return data;
	}
	
	/**
	 * @return Name of the packet.
	 */
	public String getPacketName()
	{
		return packetName;
	}
	
	/**
	 * @return The Raw String
	 */
	public String getUTF()
	{
		return utf;
	}
	
	/**
	 * Encode the String
	 * @param text The text to encode
	 * @return The encoded string
	 */
	public static String encode(String text)
	{
		char[] cs = text.toCharArray();
		String t = "";
		
		for (char c : cs) {
			int e = (int)c + 10;
			t = t + (char)e;
		}
		
		return t;
	}
	
	/**
	 * Decode the string
	 * @param text The text to decode
	 * @return The decoded string
	 */
	public static String decode(String text)
	{
		char[] cs = text.toCharArray();
		String t = "";
		
		for (char c : cs) {
			int e = (int)c - 10;
			t = t + (char)e;
		}
		
		return t;
	}

}