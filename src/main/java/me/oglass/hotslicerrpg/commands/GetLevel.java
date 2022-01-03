package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.classes.RPGClasses;
import me.oglass.hotslicerrpg.items.CraftingTable;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

public class GetLevel implements CommandExecutor {

    private Main plugin;

    public GetLevel(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("getlevel").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.getlevel")) {
            p.sendMessage("K: " + ClassManager.getLevel(p, RPGClasses.Knight));
            p.sendMessage(", A: " + ClassManager.getLevel(p, RPGClasses.Archer)
                    + ", H: " + ClassManager.getLevel(p, RPGClasses.Healer));
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }

}
