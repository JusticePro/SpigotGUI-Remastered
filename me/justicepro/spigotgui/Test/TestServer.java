package me.justicepro.spigotgui.Test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import me.justicepro.spigotgui.Server;

public class TestServer {

	@Test
	public void create() {
		File jar = new File("server.jar");
		String arguments = "";
		String switches = "";
		try {
			Server server = new Server(jar, arguments, switches);
		}catch(Exception e) {
			fail("Error Found");
		}
		
	}
	
	@Test
	public void makeMemory() {
		File jar = new File("server.jar");
		String arguments = "";
		String switches = null;
		try {
			switches = Server.makeMemory("1024M", "2048M");
		}catch(Exception e) {
			fail("Error Found");
		}
		
		Server server = new Server(jar, arguments, switches);
		
	}
	
	@Test
	public void isRunning() {
		File jar = new File("server.jar");
		String arguments = "";
		String switches = Server.makeMemory("1024M", "2048M");
		
		Server server = new Server(jar, arguments, switches);
		
		try {
			server.isRunning();
		}catch(Exception e) {
			fail("Error Found");
		}
		
	}
	
	@Test
	public void start() {
		File jar = new File("server.jar");
		String arguments = "";
		String switches = Server.makeMemory("1024M", "2048M");
		
		Server server = new Server(jar, arguments, switches);
		
		try {
			server.start();
		}catch(Exception e) {
			fail("Error Found");
		}
		
	}

}