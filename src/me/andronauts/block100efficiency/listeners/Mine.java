package me.andronauts.block100efficiency.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.andronauts.block100efficiency.Main;

public class Mine implements Listener {
	
	Map<String, Integer> blockCount = new HashMap<String, Integer>();
	
	public Mine(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private boolean isTool(ItemStack i) {
		
		String item = i.getType().toString();
		if(item.contains("SWORD") || item.contains("AXE") || item.contains("SHEARS") ||
				item.contains("SHOVEL") || item.contains("HOE")) {
			return true;
		} else if (i.getType().equals(Material.WOODEN_SHOVEL) ||
				i.getType().equals(Material.STONE_SHOVEL) || 
				i.getType().equals(Material.IRON_SHOVEL) || 
				i.getType().equals(Material.GOLDEN_SHOVEL) ||
				i.getType().equals(Material.DIAMOND_SHOVEL) ||
				i.getType().equals(Material.NETHERITE_SHOVEL) ||
				i.getType().equals(Material.NETHERITE_AXE) ||
				i.getType().equals(Material.NETHERITE_PICKAXE) ||
				i.getType().equals(Material.NETHERITE_SWORD) ||
				i.getType().equals(Material.NETHERITE_HOE)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@EventHandler
	public void hundredBlock(BlockBreakEvent event) {
		
		String username = event.getPlayer().getDisplayName();
		
		if(!blockCount.containsKey(username)) {
			blockCount.put(username, 1);
		} else {
			blockCount.replace(username, blockCount.get(username)+1);
		}
		
		if(blockCount.get(username) % 100 == 0) {
			
			Bukkit.broadcastMessage(username + " has mined: " + blockCount.get(username) + " blocks.");
			
			Player p = event.getPlayer();
			
			for(ItemStack i : p.getInventory().getContents()) {
				if(isTool(i)) 
					i.addUnsafeEnchantment(Enchantment.DIG_SPEED, i.getEnchantmentLevel(Enchantment.DIG_SPEED)+1);
			}
			
		} 
	}

}
