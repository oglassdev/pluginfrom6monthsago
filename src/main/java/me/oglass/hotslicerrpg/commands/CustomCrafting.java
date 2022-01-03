package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.classes.RPGClasses;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomCrafting implements CommandExecutor {

    private Main plugin;

    public CustomCrafting(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("ccraft").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.nocrafting")) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        } else {
            p.openInventory(MenuManager.craftingMenu.get(p.getUniqueId()));
        }
        return false;
    }

}
