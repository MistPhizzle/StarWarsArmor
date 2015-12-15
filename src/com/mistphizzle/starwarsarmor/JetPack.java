package com.mistphizzle.starwarsarmor;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class JetPack {
	
	public static Set<Player> players = new HashSet<Player>();
	
	public static void check() {
		for (Player player: players) {

			if (!player.isOnline() || player.isDead()) { 
				players.remove(player); // Make sure we aren't trying to move someone that isn't there.
			}
			if (!player.isSneaking()) {
				players.remove(player); // They are no longer sneaking
			}
			
			if (!player.hasPermission("starwarsarmor.jetpack")) {
				players.remove(player);
			}
			if (player.getInventory().getChestplate() == null) players.remove(player); // The player isn't wearing anything.
			if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() != Material.CHAINMAIL_CHESTPLATE) players.remove(player);
			Vector velocity = player.getEyeLocation().getDirection().clone().normalize().multiply(StarWarsArmor.plugin.getConfig().getDouble("JetPack.Speed"));
			player.setVelocity(velocity);
			player.setFallDistance(0);
		}
	}

}
