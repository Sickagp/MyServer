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
import cz.Sicka_gp.MyServer.utils.MessageList;

public class ScoreboardConfig {
	private FileConfiguration config = null;
	public File configfile = null;
	private MyServer plugin;
	
	public ScoreboardConfig(MyServer instance){
		plugin = instance;
	}
	
	
	public void reloadConfig() {
	    if (configfile == null) {
	    configfile = new File(plugin.getDataFolder(), "scoreboard.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configfile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("scoreboard.yml");
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
	    	plugin.getLog().log(Level.SEVERE, ColouredConsoleSender.sendConsoleMessage(AnsiColor.RED, MessageList.CouldNotSaveConfig  + configfile), ex);
	    }
	}
	
	public void saveDefaultConfig() {
	    if (configfile == null) {
	        configfile = new File(plugin.getDataFolder(), "scoreboard.yml");
	    }
	    if (!configfile.exists()) {            
	         plugin.saveResource("scoreboard.yml", false);
	         plugin.getLog().log(Level.INFO, ColouredConsoleSender.sendConsoleMessage(AnsiColor.GREEN, MessageList.CreateConfigFile  + AnsiColor.GOLD + configfile.getName()));
	     }
	}

}
