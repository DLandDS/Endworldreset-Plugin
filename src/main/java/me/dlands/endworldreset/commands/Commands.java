package me.dlands.endworldreset.commands;

import me.dlands.endworldreset.settings.Config;
import me.dlands.endworldreset.utils.ScheduleTimer;
import me.dlands.endworldreset.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String adminPermission = "endworldreset.admin";
        if(args.length>0){
            if(args[0].equals("info")) {
                if(args.length == 1){
                    sender.sendMessage(ScheduleTimer.getLongPeriod());
                    return true;
                } else {
                    if(args[1].equals("setting")){
                        Utils.runAsPermission(sender, adminPermission, ()-> sender.sendMessage(Config.getSettings().print()));
                        return true;
                    }
                }
            }

            if(args[0].equalsIgnoreCase("reload")){
                Utils.runAsPermission(sender, adminPermission, ()->{
                    Config.reload();
                    sender.sendMessage("[Endworldreset] Configuration reloaded!");
                });
                return true;
            } else if(args[0].equals("help")){
                help(sender);
                return true;
            } else if(args[0].equals("autogen")){
                Utils.runAsPermission(sender, adminPermission, ()->{
                    sender.sendMessage("[Endworldreset] Autogen complete!");
                    Config.getSettings().set();
                });
                return true;
            }
            return false;
        }

        help(sender);
        return false;
    }

    void help(CommandSender sender){
        sender.sendMessage("Endworldreset Plugin\n" +
                "[Usages]:\n" +
                "/endworldreset reload           Reload config plugin (Admin)\n" +
                "/endworldreset autogen          Auto generate config (Admin)\n" +
                "/endworldreset info             Show time left\n" +
                "/endworldreset info setting     Show config info (Admin)\n");
    }
}
