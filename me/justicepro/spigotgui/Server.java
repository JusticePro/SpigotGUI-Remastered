package me.justicepro.spigotgui;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Server {
	
	private File jar;
	private String switches;
	private String arguments;
	
	private Process process;
	
	public Server(File jar, String arguments, String switches) {
		this.jar = jar;
		this.arguments = arguments;
		this.switches = switches;
	}
	
	public Thread start(String arguments, String switches) throws IOException, ProcessException {
		
		boolean isWindows = System.getProperty("os.name")
				  .toLowerCase().startsWith("windows");
		
		if (process!=null) {
			if (process.isAlive()) {
				throw new ProcessException();
			}
		}else {
			System.out.println("Started Server");
			
			if (isWindows) {
				process = Runtime.getRuntime().exec("cmd");

				//PrintWriter output = new PrintWriter(process.getOutputStream(), true);

//				output.println("java " + switches + " -jar \"" + jar.getAbsolutePath() + "\" " + arguments + " & exit");


			}else {
				process = Runtime.getRuntime().exec("sh");

				//PrintWriter output = new PrintWriter(process.getOutputStream(), true);

//				output.println("java " + switches + " -jar \"" + jar.getAbsolutePath() + "\" " + arguments + " & exit");
			}
			
			PrintWriter output = new PrintWriter(process.getOutputStream(), true);
			
			output.println("java " + switches + " -jar \"" + jar.getAbsolutePath() + "\" " + arguments + " & exit");
			
			Thread thread = createThread();
			
			thread.start();
			
			return thread;
			
		}
		
		return null;
	}

	public Thread start() throws IOException, ProcessException {
		return start(arguments, switches);
	}
	
	public boolean isRunning() {
		
		if (process == null) {
			return false;
		}
		
		return process.isAlive();
	}
	
	private Thread createThread() {
		
		if (process == null) {
			return null;
		}
		
		return new Thread(new Runnable() {
			public void run() {
				Scanner scanner = new Scanner(process.getInputStream());
				while (isRunning()) {

					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						
						for (Module module : ModuleManager.modules) {
							module.onConsolePrintRaw(line);
						}
						
					}

				}
				scanner.close();
				
				for (Module module : ModuleManager.modules) {
					module.onServerClosed();
				}
				
			}
		});
	}

	public static String makeMemory(String ramMin, String ramMax) {

		String args = "-Xms" + ramMin + " -Xmx" + ramMax;

		return args;
	}

	public void sendCommand(String command) throws ProcessException {
		
		PrintWriter output = new PrintWriter(process.getOutputStream(), true);

		if (process.isAlive()) {
			output.println(command);
		}else {
			throw new ProcessException();
		}
		
	}
	
}