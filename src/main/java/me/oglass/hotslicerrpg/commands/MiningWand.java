package me.oglass.hotslicerrpg.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.mobs.CustomZombie;
import me.oglass.hotslicerrpg.mobs.EntityTypes;
import me.oglass.hotslicerrpg.mobs.MobHealthManager;
import me.oglass.hotslicerrpg.utils.Utils;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityZombie;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MiningWand implements CommandExecutor {

    private Main plugin;

    public MiningWand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("miningwand").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            ItemStack itemStack = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(Utils.chat("&c&lMines Wand"));
            meta.spigot().setUnbreakable(true);
            List<String> lore = new ArrayList<>();
            lore.add(Utils.chat("&4Select an area for the mines!"));
            meta.setLore(lore);
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            itemStack.setItemMeta(meta);

            NBTItem nbti = new NBTItem(itemStack);

            nbti.setString("CUSTOM_ID", "ADMIN_MINES_WAND");
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
