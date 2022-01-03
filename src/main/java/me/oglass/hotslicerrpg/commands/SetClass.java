package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.classes.RPGClasses;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetClass implements CommandExecutor {

    private Main plugin;

    public SetClass(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setclass").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.nosetclass")) {
            if (args[0].equalsIgnoreCase("knight")) {
                if (!ClassManager.Players.containsKey(p.getUniqueId().toString()) || !ClassManager.Players.get(p.getUniqueId().toString()).equals(RPGClasses.Knight)) {
                    ClassManager.changeClasses(p, RPGClasses.Knight);
                    p.sendMessage(Utils.chat("&3You have changed your class to &6Knight&3!"));
                } else p.sendMessage(Utils.chat("&cYou already have this class selected!"));
            }
            else if (args[0].equalsIgnoreCase("healer")) {
                if (!ClassManager.Players.containsKey(p.getUniqueId().toString()) || !ClassManager.Players.get(p.getUniqueId().toString()).equals(RPGClasses.Healer)) {
                    ClassManager.changeClasses(p, RPGClasses.Healer);
                    p.sendMessage(Utils.chat("&3You have changed your class to &4Healer&3!"));
                } else p.sendMessage(Utils.chat("&cYou already have this class selected!"));
            }
            else if (args[0].equalsIgnoreCase("archer")) {
                if (!ClassManager.Players.containsKey(p.getUniqueId().toString()) || !ClassManager.Players.get(p.getUniqueId().toString()).equals(RPGClasses.Archer)) {
                    ClassManager.changeClasses(p, RPGClasses.Archer);
                    p.sendMessage(Utils.chat("&3You have changed your class to &dArcher&3!"));
                } else p.sendMessage(Utils.chat("&cYou already have this class selected!"));
            } else {
                p.sendMessage(Utils.chat("&cPlease enter a valid class!"));
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }

}
