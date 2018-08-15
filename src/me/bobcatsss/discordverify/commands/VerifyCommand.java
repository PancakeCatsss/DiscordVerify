package me.bobcatsss.discordverify.commands;

import me.bobcatsss.discordverify.Core;
import me.bobcatsss.discordverify.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import simple.brainsynder.nms.ITellraw;
import simple.brainsynder.utils.Reflection;
import simple.brainsynder.utils.WebConnector;

import java.util.concurrent.CompletableFuture;

public class VerifyCommand implements CommandExecutor {
	
	private Core plugin;
	public VerifyCommand(Core pl) {
		this.plugin = pl;
	}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        
        if(plugin.getCodes().containsKey(player.getUniqueId().toString())) {
        	String code = plugin.getCodes().get(player.getUniqueId().toString());
            ITellraw msg = Reflection.getTellraw(Utils.c("&f[&eDiscordVerify&f] &eYour code is&f: "));
            msg.color(ChatColor.GREEN);
            msg.then(ChatColor.UNDERLINE + code);
            msg.tooltip(Utils.c("&aClick here to copy"));
            msg.suggest(code);
            msg.send(player);
            return true;
        }
        
        PermissionUser user = PermissionsEx.getUser(player);
        if (user.getGroups().length == 1) {
            player.sendMessage(Utils.c("&f[&eDiscordVerify&f] &cIn order to join our discord server you must be a higher rank than Builder&f. Please use &e/ar check &fto see when you rank up&f."));
            return true;
        }

        String code = Utils.randomID("1234567890ABCDEFG", 10);
        ITellraw msg = Reflection.getTellraw(Utils.c("&f[&eDiscordVerify&f] &eYour code is&f: "));
        msg.color(ChatColor.GREEN);
        msg.then(ChatColor.UNDERLINE + code);
        msg.tooltip(Utils.c("&aClick here to copy"));
        msg.suggest(code);
        plugin.getCodes().put(player.getUniqueId().toString(), code);

        CompletableFuture.runAsync(() -> {
            WebConnector.getInputStream("http://pluginwiki.us/bobcats/upload.php?code="+code+"&uuid="+player.getUniqueId().toString());
            new BukkitRunnable() {
                @Override
                public void run() {
                    msg.send(player);

                }
            }.runTask(Core.getProvidingPlugin(Core.class));
        });
        return true;
    }
}
