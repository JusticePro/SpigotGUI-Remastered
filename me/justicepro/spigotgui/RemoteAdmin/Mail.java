package me.justicepro.spigotgui.RemoteAdmin;

import java.io.Serializable;

public class Mail implements Serializable {
	
	private String subject;
	private String body;
	
	/**
	 * This is mail to send to a user from a user.
	 * @param subject The subject of the mail.
	 * @param body The body of the mail.
	 */
	public Mail(String subject, String body)
	{
		this.subject = subject;
		this.body = body;
	}
	
	/**
	 * @return The body of the mail.
	 */
	public String getBody()
	{
		return body;
	}
	
	/**
	 * @return The subject of the mail.
	 */
	public String getSubject()
	{
		return subject;
	}
	
}