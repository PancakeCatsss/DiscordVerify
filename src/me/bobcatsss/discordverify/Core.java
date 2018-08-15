package me.bobcatsss.discordverify;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import me.bobcatsss.discordverify.commands.VerifyCommand;
import me.bobcatsss.discordverify.utils.FileManager;

public class Core extends JavaPlugin {
	
	private FileManager users;
	private Map<String, String> codes = new HashMap<>();

    @Override
    public void onEnable() {
        registerCommands();
        users = getUsers();
        loadUserCodes();
    }
    
    @Override
    public void onDisable() {
    	saveUsers();
    	users = null;
    	codes.clear();
    	codes = null;
    }

    private void registerCommands() {
        getCommand("verify").setExecutor(new VerifyCommand(this));
    }
    
    private void loadUserCodes() {
    	for(String key : getUsers().getConfig().getKeys(false)) {
    		getCodes().put(key, getUsers().getConfig().getString(key, ""));
    	}
    }
    
    private void saveUsers() {
    	if(getCodes().isEmpty()) return;
    	for(String key : getCodes().keySet()) {
    		getUsers().getConfig().set(key, getCodes().get(key));
    	}
    	getUsers().save();
    }
    
    public FileManager getUsers() {
    	if(users == null) {
    		users = new FileManager(this, "users.yml");
    	}
    	return users;
    }
    
    public Map<String, String> getCodes() {
    	return codes;
    }
}
