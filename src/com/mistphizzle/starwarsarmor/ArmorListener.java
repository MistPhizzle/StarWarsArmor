package com.mistphizzle.starwarsarmor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.PlayerInventory;


public class ArmorListener implements Listener {

	StarWarsArmor plugin;
	
	public ArmorListener(StarWarsArmor plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		
		if (!player.isSneaking()) {
			JetPack.players.add(player);
		}
	}
	
	public static boolean hasFullSetChainArmor(Player player) {
		PlayerInventory inv = player.getInventory();
		if (inv.getHelmet() == null) return false;
		if (inv.getChestplate() == null) return false;
		if (inv.getLeggings() == null) return false;
		if (inv.getBoots() == null) return false;
		if (inv.getHelmet().getType() != Material.CHAINMAIL_HELMET) return false;
		if (inv.getChestplate().getType() != Material.CHAINMAIL_CHESTPLATE) return false;
		if (inv.getLeggings().getType() != Material.CHAINMAIL_LEGGINGS) return false;
		if (inv.getBoots().getType() != Material.CHAINMAIL_BOOTS) return false;
		return true;
	}
	public static void damage(Player player) {
		if (player.hasPermission("starwarsarmor.suffocate.immune")) return;
		if (StarWarsArmor.plugin.getConfig().getStringList("ArmorSuffocate.Worlds").contains(player.getWorld().getName())) { // Player is in a world where armor suffocate is on
			if (!hasFullSetChainArmor(player)) {
				player.damage(1);
			}
		}
	}
	
}
