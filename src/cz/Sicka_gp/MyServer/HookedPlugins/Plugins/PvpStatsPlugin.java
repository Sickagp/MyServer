package cz.Sicka_gp.MyServer.HookedPlugins.Plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import praxis.slipcor.pvpstats.PSMySQL;
import cz.Sicka_gp.MyServer.MyServer;
import cz.Sicka_gp.MyServer.HookedPlugins.PluginsManager;

public class PvpStatsPlugin {
	private static boolean pvpstats;
	protected static MyServer plugin;
	private static PSMySQL psmysql;


	public PvpStatsPlugin(MyServer instance){
		plugin = instance;
		CheckPvPstats();
	}
	
	private static void CheckPvPstats(){
    	final PluginManager pm = Bukkit.getServer().getPluginManager();
    	Plugin pvpstatspl = pm.getPlugin("pvpstats");
    	
    	if(pvpstatspl != null && pvpstatspl.isEnabled()){
        	pvpstats = pm.getPlugin("pvpstats") != null;
        	PluginsManager.PluginList.add(pvpstatspl);
    	}
    }

	public boolean isPvpstats() {
		return pvpstats;
	}
	
	public PSMySQL getPSMySQL(){
		return psmysql;
	}

}
