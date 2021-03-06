package cz.Sicka_gp.MyServer.Configuration.Configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.utils.AnsiColor;
import cz.Sicka_gp.MyServer.utils.ColouredConsoleSender;

public class Lang {
	private FileConfiguration config = null;
	public File configfile = null;
	private MyServer plugin;
	
	public Lang(MyServer instance){
		plugin = instance;
	}
	
	
	public void reloadConfig() {
	    if (configfile == null) {
	    configfile = new File(plugin.getDataFolder(), "lang.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configfile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("lang.yml");
	    if (defConfigStream != null) {
	    	YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
	        config.setDefaults(defConfig);
	    }
	}
	
	
	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configfile == null) {
	        return;
	    }
	    try {
	        getConfig().save(configfile);
	    } catch (IOException ex) {
	    	plugin.getLog().log(Level.SEVERE, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, "Could not save config to "  + configfile), ex);
	    }
	}
	
	public void saveDefaultConfig() {
	    if (configfile == null) {
	        configfile = new File(plugin.getDataFolder(), "lang.yml");
	    }
	    if (!configfile.exists()) {            
	         plugin.saveResource("lang.yml", false);
	         plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, "Creating config file - "  + AnsiColor.GOLD + configfile.getName()));
	     }
	}

}
