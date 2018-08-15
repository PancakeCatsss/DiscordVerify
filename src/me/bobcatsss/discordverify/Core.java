package me.bobcatsss.discordverify;

import org.bukkit.plugin.java.JavaPlugin;

import me.bobcatsss.discordverify.commands.VerifyCommand;

public class Core extends JavaPlugin {
		
	@Override
	public void onEnable() {
		registerCommands();
	}
	
	private void registerCommands() {
		getCommand("verify").setExecutor(new VerifyCommand());
	}

}
