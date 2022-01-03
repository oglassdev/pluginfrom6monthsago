package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.classes.RPGClasses;
import me.oglass.hotslicerrpg.mobs.Midas;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddXP implements CommandExecutor {

    private Main plugin;

    public AddXP(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("addxp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            ClassManager.addXP(p, 30, RPGClasses.Knight);
            ClassManager.addXP(p, 15, RPGClasses.Archer);
            ClassManager.addXP(p, 10, RPGClasses.Healer);
            p.sendMessage("Added 30 xp to knight class, 15 xp to the archer class, and 10 xp to the healer class");
            Midas.spawnMidas(p.getLocation());
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }

}
