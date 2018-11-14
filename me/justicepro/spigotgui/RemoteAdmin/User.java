package me.justicepro.spigotgui.RemoteAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {
	
	/*
	 * Static Variables
	 */
	
	/**
	 * The users that are registered.
	 */
	public static ArrayList<User> users = new ArrayList<>();
	
	/**
	 * Registered Permissions.
	 */
	public static ArrayList<Permission> registeredPermissions = new ArrayList<>();
	
	/*
	 * User Variables
	 */
	
	/**
	 * Username for the user.
	 */
	public String username;
	
	/**
	 * Password for the user.
	 */
	public String password;
	
	/**
	 * Permissions
	 */
	public HashMap<String, Boolean> permissions;
	
	/**
	 * Mail in the user's mailbox.
	 */
	public ArrayList<Mail> mail = new ArrayList<>();
	
	
	/*
	 * User Methods
	 */
	
	
	/**
	 * A remote-admin user.
	 * @param username The username for the user.
	 * @param password The password for the user.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.permissions = new HashMap<>();
	}
	
	
	/**
	 * This method is for authentication.
	 * @param username The username to test.
	 * @param password The password to test.
	 * @return If the username and password are correct return true.
	 */
	public boolean isCorrect(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}
	
	
	/**
	 * This method adds mail to the user's mailbox.
	 * @param mail The mail to add.
	 */
	public void addMail(Mail mail) {
		this.mail.add(0, mail);
	}
	
	
	/**
	 * This method tests for a permission with a registered permission.
	 * @param permission The requested permission.
	 * @return If the user has the permission.
	 */
	public boolean hasPermission(Permission permission) {
		return hasPermission(permission.getPermission());
	}
	
	
	/**
	 * This method tests for a permission with a permission path.
	 * @param permission The requested permission path.
	 * @return If the user has the permission.
	 */
	public boolean hasPermission(String permission) {

		if (!permissions.containsKey(permission)) {
			setHasPermission(permission, false);
		}

		return permissions.get(permission);
	}
	
	
	/**
	 * This method allows the programmers to get a permission with the raw permission.
	 * @param permission This is the Permission Path
	 * @return This returns a registered permission. Returns null if not found.
	 */
	public Permission queryPermission(String permission) {

		for (Permission p : registeredPermissions) {

			if (p.getPermission().equalsIgnoreCase(permission)) {
				return p;
			}

		}

		return null;
	}
	
	
	/**
	 * Grant or deny a permission.
	 * @param permission The Targeted Permission to set
	 * @param hasPermission True to allow, False to deny
	 */
	public void setHasPermission(Permission permission, boolean hasPermission) {
		setHasPermission(permission.getPermission(), hasPermission);
	}
	
	
	/**
	 * Grant or deny a permission.
	 * @param permission The Targeted Permission to set
	 * @param hasPermission True to allow, False to deny
	 */
	public void setHasPermission(String permission, boolean hasPermission) {
		permissions.put(permission, hasPermission);
	}
	
	
	/**
	 * This method sets the user's password.
	 * @param password The new password for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * Static Methods
	 */
	
	/**
	 * Update the user on the users variable
	 * @param user The target user.
	 */
	public static void updateUser(User user) {
		ArrayList<User> urs = (ArrayList<User>) users.clone();

		for (int i = 0; i < urs.size(); i++) {
			User u = urs.get(i);

			if (u.username.equalsIgnoreCase(user.username)) {
				users.remove(i);
				users.add(i, user);
			}

		}

	}
	
	/**
	 * Save all the users in the selected file.
	 * @param file The file to save the users in.
	 * @throws IOException
	 */
	public static void saveUsers(File file) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
		output.writeObject(users);
		output.flush();
		output.close();
	}
	
	/**
	 * Save all the users and automatically creates the folders.
	 * @throws IOException
	 */
	public static void saveUsers() throws IOException {
		File dataFolder = new File("data", "remote-admin");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
		saveUsers(new File(dataFolder, "users.dat"));
	}
	
	/**
	 * Load all the users.
	 * @return The users saved in a file.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<User> loadUsers(File file) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
		ArrayList<User> urs = (ArrayList<User>) input.readObject();
		input.close();
		return urs;
	}
	
	/**
	 * Load all the users and automatically creates the folders.
	 * @return The users saved in data.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<User> loadUsers() throws IOException, ClassNotFoundException {
		File dataFolder = new File("data", "remote-admin");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
		return loadUsers(new File(dataFolder, "users.dat"));
	}
	
	/**
	 * This method gets a user from username and password for authentication.
	 * @param username The username for the user
	 * @param password The password for the user
	 * @return The user. Returns null if not found or incorrect.
	 */
	public static User query(String username, String password) {

		User user = null;

		for (User u : users) {

			if (u.isCorrect(username, password)) {
				user = u;
			}

		}

		return user;
	}
	
	/**
	 * This method removes the user with the selected username.
	 * @param username The selected username for the user
	 * @return If successful will return true, if not returns false
	 */
	public static boolean removeUser(String username) {

		ArrayList<User> u = (ArrayList<User>) users.clone();

		for (int i = 0; i < u.size(); i++) {
			User user = u.get(i);

			if (user.username.equalsIgnoreCase(username)) {
				users.remove(i);
				return true;
			}

		}
		return false;
	}

	/**
	 * This method gets a user from a username. (Ignore Case)
	 * @param username The username
	 * @return The user. Returns null if not found.
	 */
	public static User queryIgnoreCase(String username) {
		User user = null;

		for (User u : users) {

			if (u.username.equalsIgnoreCase(username)) {
				user = u;
			}

		}

		return user;
	}

}