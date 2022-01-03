package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminMenu implements CommandExecutor {

    private Main plugin;

    public AdminMenu(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("adminmenu").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            p.openInventory(MenuManager.adminControls.get(p.getUniqueId()));
            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }
}
