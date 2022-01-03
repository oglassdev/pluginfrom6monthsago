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

import java.util.ArrayList;
import java.util.List;

public class CrystalWand implements CommandExecutor {

    private Main plugin;

    public CrystalWand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("crystalwand").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE, 1);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(Utils.chat("&c&lGlass Convertor"));
            meta.spigot().setUnbreakable(true);
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            itemStack.setItemMeta(meta);

            NBTItem nbti = new NBTItem(itemStack);

            nbti.addCompound("CUSTOM_ID");
            nbti.addCompound("ENERGY_ABILITY");
            nbti.setString("CUSTOM_ID", "ADMIN_GLASS_CONVERSION");
            nbti.setBoolean("ENERGY_ABILITY", false);

            itemStack = nbti.getItem();
            p.getInventory().addItem(itemStack);
            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }

}
