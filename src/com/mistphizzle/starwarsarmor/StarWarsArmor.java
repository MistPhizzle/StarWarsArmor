package com.mistphizzle.starwarsarmor;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class StarWarsArmor extends JavaPlugin {

	public static Plugin plugin;
	public static Logger log;
	
	public void onEnable() {
		plugin = this;
		StarWarsArmor.log = this.getLogger();
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		getServer().getPluginManager().registerEvents(new ArmorListener(this), this);
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player: Bukkit.getOnlinePlayers()) {
                	ArmorListener.damage(player);
                }
            }
        }, 0, 20L);
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				JetPack.check();
			}
        	
        }, 0, 1L);

	}
}
