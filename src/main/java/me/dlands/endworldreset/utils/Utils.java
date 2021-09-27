package me.dlands.endworldreset.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {

    public static boolean runAsPermission(CommandSender sender, String permissionName, Runnable runnable){
        boolean isHas = sender.hasPermission(permissionName);
        if (!isHas) {
            sender.sendMessage(ChatColor.RED + "You dont have permission to execute this command!");
        } else {
            runnable.run();
        }
        return isHas;
    }

}
