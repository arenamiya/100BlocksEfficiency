package me.andronauts.block100efficiency;

import org.bukkit.plugin.java.JavaPlugin;

import me.andronauts.block100efficiency.listeners.Mine;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		new Mine(this);
		getServer().getLogger().info("Plugin Enabled.");
		
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("Plugin Disabled.");
	}


}
