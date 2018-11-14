package me.justicepro.spigotgui.RemoteAdmin;

public class Permission {
	
	/**
	 * The display name of the permission.
	 */
	private String name;
	
	/**
	 * The permission path of the permission.
	 */
	private String permission;
	
	
	/**
	 * Registered Permission
	 * @param name The display name of the permission.
	 * @param permission The permission path of the permission.
	 */
	public Permission(String name, String permission) {
		this.name = name;
		this.permission = permission;
	}
	
	/**
	 * The display name of the permission.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The permission path of the permission.
	 */
	public String getPermission() {
		return permission;
	}
	
}