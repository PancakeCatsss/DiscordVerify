package me.bobcatsss.discordverify.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bobcatsss.discordverify.utils.Utils;
import simple.brainsynder.nms.ITellraw;
import simple.brainsynder.utils.Reflection;

public class VerifyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command.");
			return true;
		}
		Player player = (Player) sender;
		String code = Utils.randomID("1234567890ABCDEFG", 10);
                ITellraw msg = Reflection.getTellraw("§f[§eDiscordVerify§f] §eYour code is§f: ");
                msg.color (ChatColor.GREEN); 
                msg.then(ChatColor.UNDERLINE + code);
                msg.tooltip("§aClick here to copy");
                msg.suggest(code);
                msg.send(player);
		return true;
	}
}
