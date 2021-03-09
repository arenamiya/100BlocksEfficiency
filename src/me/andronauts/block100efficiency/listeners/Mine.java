package me.andronauts.block100efficiency.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.andronauts.block100efficiency.Main;

public class Mine implements Listener {
	
	Map<UUID, Integer> blockCount = new HashMap<UUID, Integer>();
	
	public Mine(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void hundredBlock(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();
		ItemStack item = p.getInventory().getItemInMainHand();
		
		if(!blockCount.containsKey(uuid)) {
			blockCount.put(uuid, 1);
		} else {
			blockCount.replace(uuid, blockCount.get(uuid)+1);
		}
		
		if(blockCount.get(uuid) % 100 == 0) {
			
			Bukkit.broadcastMessage(p.getDisplayName() + " has mined: " + blockCount.get(uuid) + " blocks.");
			
			ItemMeta meta = item.getItemMeta();
			int eLvl = 0;
			
			if(item.containsEnchantment(Enchantment.DIG_SPEED)) 
				eLvl = item.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1;
			else 
				eLvl = 1;
			
			meta.addEnchant(Enchantment.DIG_SPEED, eLvl, true);
			item.setItemMeta(meta);
				
		} 
	}

}
