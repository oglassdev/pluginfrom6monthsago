package me.oglass.hotslicerrpg.classes;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Knight {
    public static ItemStack KHelmet;

    public static void switchToClass(Player p) {

    }

    private static void createKnightHelm1() {
        ItemStack itemStack = new ItemStack(Material.GOLD_HELMET, 1);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(Utils.chat("&7Knight Helmet - T1"));
        meta.spigot().setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(Utils.chat("&cHealth: &f+10"));
        lore.add(Utils.chat("&aDefense: &f+15"));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1,  true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemStack.setItemMeta(meta);

        NBTItem nbti = new NBTItem(itemStack);
        nbti.setString("CUSTOM_ID", "KNIGHT_HELM_1");
        nbti.setInteger("HEALTH", 10);
        nbti.setInteger("DEFENSE", 15);
        nbti.setBoolean("ENERGY_ABILITY", false);

        itemStack = nbti.getItem();

        KHelmet = itemStack;
    }
}
