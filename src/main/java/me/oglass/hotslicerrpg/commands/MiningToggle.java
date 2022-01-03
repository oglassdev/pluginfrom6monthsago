package me.oglass.hotslicerrpg.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class MiningToggle implements CommandExecutor {

    private Main plugin;

    public MiningToggle(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("minestoggle").setExecutor(this);
    }

    public static boolean toggle = Boolean.TRUE;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            if (!toggle) {
                toggle = true;
                p.sendMessage(Utils.chat("&aYou have toggled the mines on!"));
            } else {
                toggle = false;
                p.sendMessage(Utils.chat("&aYou have toggled the mines off!"));
            }

            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }

}
