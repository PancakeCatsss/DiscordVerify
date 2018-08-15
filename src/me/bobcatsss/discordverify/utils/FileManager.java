package me.bobcatsss.discordverify.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.bobcatsss.discordverify.Core;

public class FileManager {

	private File file;
    private FileConfiguration fileConfig;
	private Core plugin;
	
	public FileManager(Core pl, String fileName) {
		this.plugin = pl;
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
		
		file = new File(plugin.getDataFolder(), fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		fileConfig = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileConfiguration getConfig() {
		return this.fileConfig;
	}
	
	public void save() {
		try {
			fileConfig.save(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		fileConfig = YamlConfiguration.loadConfiguration(file);
	}
}
