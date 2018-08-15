package me.bobcatsss.discordverify;

import me.bobcatsss.discordverify.commands.VerifyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        getCommand("verify").setExecutor(new VerifyCommand());
    }

}
