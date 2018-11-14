package me.justicepro.spigotgui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ModuleManager {
	
	public static ArrayList<Module> modules = new ArrayList<>();
	
	/**
	 * Register a Module
	 * @param module The module to register.
	 */
	public static void registerModule(Module module)
	{
		modules.add(module);
	}
	
	/**
	 * Load all modules from modules folder.
	 * @throws IOException
	 */
	public static void init() throws IOException
	{
		File modules = new File("modules");
		
		if (!modules.exists()) {
			modules.mkdirs();
		}
		
		for (File file : modules.listFiles()) {
			if (getFileExtension(file).equalsIgnoreCase(".jar")) {
				System.out.println("Trying to load '" + file.getName() + "'.");
				try {
					loadModule(file);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					JOptionPane.showMessageDialog(null, "Couldn't load '" + file.getName() + "'. Try removing it.");
					e.printStackTrace();
					System.err.println("Couldn't load '" + file.getName() + "': " + e.getMessage());
				}
			}
		}
		
	}
	
	/**
	 * Get extension from a file
	 * @param file the file
	 * @return
	 */
    private static String getFileExtension(File file)
    {
        String extension = "";
 
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
 
        return extension;
 
    }
	
	/**
	 * Read the text from a input stream.
	 * @param input The stream
	 * @return The text
	 * @throws IOException
	 */
    public static String readStream(InputStream input) throws IOException
	{
		String text = "";
		int b;
		while ((b = input.read()) != -1) {
			text = text + (char)b;
		}
		return text;
	}
	
    /**
     * Load a module from a File
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
	@SuppressWarnings("deprecation")
	public static void loadModule(File file) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		URL[] url = new URL[]{file.toURL()};
		
		URLClassLoader classLoader = new URLClassLoader(url);
		
		InputStream configStream = classLoader.getResourceAsStream("module.properties");
		
		if (configStream == null) {
			throw new IOException("module.properties not found.");
		}
		
		Properties config = new Properties();
		config.load(configStream);
		
		if (!config.containsKey("main")) {
			throw new IOException("'main' was not found.");
		}
		
		Class<?> mClass = classLoader.loadClass(config.getProperty("main"));
		
		Object classInstance = mClass.newInstance();
		
		if (classInstance instanceof Module) {
			Module module = (Module)classInstance;
			module.init();
			modules.add(module);
		}else {
			throw new IOException("'main' is not an instance of Module.");
		}
		
	}
	
	/**
	 * Get a module from a name.
	 * @param name The name of the module.
	 * @return The module
	 */
	public static Module getModule(String name)
	{
		Module m = null;
		for (Module module : modules) {
			if (module != null) {
				if (module.getName().equalsIgnoreCase(name)) {
					m = module;
				}
			}
		}
		
		return m;
	}
	
}