package me.oglass.hotslicerrpg.items;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class DropsManager {
	public static ItemStack BoneFragment;
	public static ItemStack MoltenRemnant;
	
	public static void init() {
		createBoneDrop();
		createMoltenRemnant();
	}
	// Classes
	private static void createBoneDrop() {
		ItemStack itemStack  = new ItemStack(Material.INK_SACK, 1);
		itemStack.setDurability((short) 15);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&7Bone Fragment"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Utils.chat("&fUsed to craft bows."));
		lore.add(Utils.chat("&fUnlock recipes by leveling"));
		lore.add(Utils.chat("&fup the Archer class."));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "BONE_FRAGMENT");
		nbti.setBoolean("ENERGY_ABILITY", false);
		nbti.setBoolean("UNPLACEABLE", true);

		itemStack = nbti.getItem();

		BoneFragment = itemStack;
	}
	private static void createMoltenRemnant() {
		ItemStack itemStack  = ItemManager.playerhead("", "f3908bd3-3553-4d49-a14c-5f05f8403b51", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5NTk3NjRkZDEzMzI4NTZlZTRkMmE0NDQ3NDdmMzYzNjQ5MzQ5ZmE4YjEzNDU5ZjRkNzE3ZGM5ZTg1NWI2MSJ9fX0=");
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cMolten Remnant"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Utils.chat("&fUsed to craft bows."));
		lore.add(Utils.chat("&fUnlock recipes by leveling"));
		lore.add(Utils.chat("&fup the Archer class."));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "MOLTEN_REMNANT");
		nbti.setBoolean("ENERGY_ABILITY", false);
		nbti.setBoolean("UNPLACEABLE", true);

		itemStack = nbti.getItem();

		MoltenRemnant = itemStack;
	}
}
