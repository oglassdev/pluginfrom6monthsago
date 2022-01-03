package me.oglass.hotslicerrpg.items;

import de.tr7zw.nbtapi.*;
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

public class ItemManager {

	public static ItemStack WitherSword;
	public static ItemStack BoomBoomSword;
	public static ItemStack SlicerMenu;
	public static ItemStack MenuGlass;
	public static ItemStack WarpMenu;
	public static ItemStack CraftingMenu;
	public static ItemStack EnderChest;
	public static ItemStack Settings;
	public static ItemStack Close;
	public static ItemStack ToggleNightVision;
	public static ItemStack BackToMenu;
	public static ItemStack NotReleased;
	public static ItemStack WaterGun;
	public static ItemStack ETGrowth;
	public static ItemStack ETProt;
	public static ItemStack GrapplingHook;
	public static ItemStack Boomerang;
	public static ItemStack BoomerangUsed;
	public static ItemStack Knight;
	public static ItemStack ClassMenu;
	public static ItemStack Healer;
	public static ItemStack Archer;
	public static ItemStack CraftGo;
	public static ItemStack MoltenFury;
	public static ItemStack RedGlass;
	public static ItemStack AdminMenu;
	public static ItemStack AdminItems;
	public static ItemStack AdminStats;
	public static ItemStack AdminBack;
	public static ItemStack AdminHealth;
	public static ItemStack AdminMaxHealth;
	public static ItemStack AdminDefense;
	public static ItemStack AdminEnergy;
	public static ItemStack AdminMaxEnergy;
	public static ItemStack AdminCItems;
	public static ItemStack Debug;

	public static ItemStack CustomItemClick;
	public static ItemStack DisplayName;
	public static ItemStack Abilities;
	public static ItemStack Lore;
	public static ItemStack CustomItemID;
	public static ItemStack LoreLine;
	public static ItemStack CustomNBTTags;
	public static ItemStack LoreGenerator;
	public static ItemStack Enchantments;
	public static ItemStack NBT;
	public static ItemStack Unbreakable;
	public static ItemStack HideFlags;
	public static ItemStack CustomRepair;
	public static ItemStack CrystalBlue;
	public static ItemStack CrystalRed;
	public static ItemStack CrystalCorrupt;
	public static ItemStack CrystalGold;
	public static ItemStack CrystalOreBlue;
	public static ItemStack CrystalOreRed;
	public static ItemStack CrystalOreCorrupt;
	public static ItemStack CrystalOreGold;
	public static ItemStack CoolCP;
	public static ItemStack CoolLG;

	public static void init() {
		createAdminButton();
		createAdminItems();
		createAdminStats();
		createAdminBack();
		createAdminHealth();
		createAdminMaxHealth();
		createAdminDefense();
		createAdminEnergy();
		createAdminMaxEnergy();
		createAdminCustomItems();

		createKnight();

		createWitherSword();
		createSlicerMenu();
		createGrapplingHook();
		createMenuGlass();
		createWarpMenu();
		createCraftingTable();
		createEnderChest();
		createSettings();
		createClose();
		createNightVisionToggle();
		createBackToMenu();
		createBoomBoomSword();
		createNotReleased();
		createWaterGun();
		createETGrowth();
		createETProt();
		createBoomerang();
		createBoomerangUsed();
		createClassMenu();
		createHealer();
		createArcher();
		createCraftGo();
		createMoltenFury();
		createDebug();
		createRedGlass();

		createCustomItemClick();
		createLore();
		createLore0();
		createAbilities();
		createDisplayName();
		createCustomItemID();
		createCustomNBTTags();
		createLoreGenerator();
		createEnchantments();
		createNBT();
		createUnbreakable();
		createHideFlags();
		createCustomRepair();
		createCrystalBlue();
		createCrystalCorrupt();
		createCrystalRed();
		createCrystalGold();
		createCrystalOreBlue();
		createCrystalOreCorrupt();
		createCrystalOreRed();
		createCrystalOreGold();
		createCoolCP();
		createCoolLG();
	}

	// Admin stuff
	private static void createAdminButton() {
		ItemStack itemStack = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&4&lAdmin Menu"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7secret menu uwu"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_MENU");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminMenu = itemStack;
	}
	private static void createAdminItems() {
		ItemStack itemStack = new ItemStack(Material.MOB_SPAWNER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&c&lCustom Items"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Give yourself custom"));
		lore.add(Utils.chat("&4items!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_ITEMS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminItems = itemStack;
	}
	private static void createAdminStats() {
		ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&c&lPlayer Stats"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your player stats!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_STATS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminStats = itemStack;
	}
	private static void createAdminCustomItems() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&c&lCustom Items"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Edit an item's abilities!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "ADMIN_CITEMS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminCItems = itemStack;
	}
	private static void createAdminBack() {
		ItemStack itemStack = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&4&lBack to Admin Menu"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_BACK");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminBack = itemStack;
	}
	private static void createAdminHealth() {
		ItemStack itemStack = new ItemStack(Material.APPLE, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&c&lHealth"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your health!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_HEALTH");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminHealth = itemStack;
	}
	private static void createAdminMaxHealth() {
		ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&c&lMax Health"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your max health!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_MAXHEALTH");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminMaxHealth = itemStack;
	}
	private static void createAdminDefense() {
		ItemStack itemStack = new ItemStack(Material.BEDROCK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&a&lDefense"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your defense!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_DEFENSE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminDefense = itemStack;
	}
	private static void createAdminEnergy() {
		ItemStack itemStack = new ItemStack(Material.DAYLIGHT_DETECTOR, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&6&lEnergy"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your energy!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_ENERGY");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminEnergy = itemStack;
	}
	private static void createAdminMaxEnergy() {
		ItemStack itemStack = new ItemStack(Material.DAYLIGHT_DETECTOR, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&6&lMax Energy"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Set your max energy!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "ADMIN_MAXENERGY");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		AdminMaxEnergy = itemStack;
	}

	// Classes
	private static void createClassMenu() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&b&lClasses"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3Choose your class for battle!"));
		lore.add(Utils.chat("&3Each class abilities and perks!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "CLASSES");
		nbti.setBoolean("ENERGY_ABILITY", true);

		itemStack = nbti.getItem();

		ClassMenu = itemStack;
	}
	// CLASS_KNIGHT
	private static void createKnight() {
		ItemStack itemStack = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cKnight"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "CLASS_KNIGHT");
		nbti.setBoolean("ENERGY_ABILITY", true);

		itemStack = nbti.getItem();

		Knight = itemStack;
	}
	// CLASS_HEALER
	private static void createHealer() {
		ItemStack itemStack = new ItemStack(Material.APPLE, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cHealer"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "CLASS_HEALER");
		nbti.setBoolean("ENERGY_ABILITY", true);

		itemStack = nbti.getItem();

		Healer = itemStack;
	}
	// CLASS_ARCHER
	private static void createArcher() {
		ItemStack itemStack = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cArcher"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "CLASS_ARCHER");
		nbti.setBoolean("ENERGY_ABILITY", true);

		itemStack = nbti.getItem();

		Archer = itemStack;
	}

	private static void createCraftGo() {
		ItemStack itemStack = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cCraft!"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3If the output is not"));
		lore.add(Utils.chat("&3showing up, click this!"));
		meta.setLore(lore);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "CRAFT_GO");
		nbti.setBoolean("ENERGY_ABILITY", true);

		itemStack = nbti.getItem();

		CraftGo = itemStack;
	}

	// WITHER_SWORD
	private static void createWitherSword() {
		ItemStack itemStack = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&8Wither Sword"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Utils.chat("&7Shoot wither skulls!"));
		lore.add(" ");
		lore.add(Utils.chat("&6Item Ability: Wither"));
		lore.add(Utils.chat("&6Energy Cost: 15"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.addCompound("ENERGY_COST");
		nbti.setString("CUSTOM_ID", "WITHER_SWORD");
		nbti.setBoolean("ENERGY_ABILITY", true);
		nbti.setInteger("ENERGY_COST", 15);
		itemStack = nbti.getItem();

		WitherSword = itemStack;
	}
	// BOOM_BOOM_SWORD
	private static void createBoomBoomSword() {
		ItemStack itemStack = new ItemStack(Material.GOLD_SWORD, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&6Excalibur"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&cDamage: &f30"));
		lore.add(" ");
		lore.add(Utils.chat("&bObliterate your foes"));
		lore.add(Utils.chat("&bwith a single right click!"));
		lore.add(Utils.chat("&6Item Ability: Explosion"));
		lore.add(Utils.chat("&6Energy Cost: 50"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
		NBTListCompound mod1 = attribute.addCompound();
		mod1.setDouble("Amount", 30.0);
		mod1.setString("AttributeName", "generic.attackDamage");
		mod1.setString("Name", "generic.attackDamage");
		mod1.setInteger("Operation", 0);
		mod1.setInteger("UUIDLeast", 59764);
		mod1.setInteger("UUIDMost", 31483);

		nbti.setString("CUSTOM_ID", "BOOM_BOOM_SWORD");
		nbti.setBoolean("ENERGY_ABILITY", true);
		nbti.setInteger("ENERGY_COST", 50);

		itemStack = nbti.getItem();

		BoomBoomSword = itemStack;
	}
	// BOOMERANG
	private static void createBoomerang() {
		ItemStack itemStack = new ItemStack(Material.BONE, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&6Boomerang"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&cDamage: &f10"));
		lore.add("");
		lore.add(Utils.chat("&bPierce your enemies!"));
		lore.add(Utils.chat("&6Item Ability: Throw!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "BOOMERANG");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Boomerang = itemStack;
	}
	// MOLTEN_FURY
	private static void createMoltenFury() {
		ItemStack itemStack = new ItemStack(Material.BOW, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cMolten Fury"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&cDamage: &f8"));
		lore.add("");
		lore.add(Utils.chat("&bInstantly shoots!"));
		lore.add(Utils.chat("&6Item Ability: Fury"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "MOLTEN_FURY");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		MoltenFury = itemStack;
	}
	// BOOMERANG_USED
	private static void createBoomerangUsed() {
		ItemStack itemStack = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&6Boomerang"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&cDamage: &f10"));
		lore.add("");
		lore.add(Utils.chat("&bPierce your enemies!"));
		lore.add(Utils.chat("&6Item Ability: Throw!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "BOOMERANG_USED");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		BoomerangUsed = itemStack;
	}
	// GRAPPLING_HOOK
	private static void createGrapplingHook() {
		ItemStack itemStack = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&bGrappling Hook"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Utils.chat("&bgrapple i guess lmao"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "GRAPPLING_HOOK");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		GrapplingHook = itemStack;
	}
	// WATER_GUN
	private static void createWaterGun() {
		ItemStack itemStack = new ItemStack(Material.BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&6shoot water lmao"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(" ");
		lore.add(Utils.chat("&bidk what to put here"));
		lore.add(" ");
		lore.add(Utils.chat("&6Item Ability: Water Bolt"));
		lore.add(Utils.chat("&6Energy Cost: 10"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);


		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "WATER_GUN");
		nbti.setBoolean("ENERGY_ABILITY", true);
		nbti.setInteger("ENERGY_COST", 10);
		itemStack = nbti.getItem();

		WaterGun = itemStack;
	}
	// SLICER_MENU
	private static void createSlicerMenu() {
		ItemStack itemStack = new ItemStack(Material.BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&6&lHotslicerRPG Menu"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Opens the menu!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "RPG_MENU");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		SlicerMenu = itemStack;
	}
	// MENU_GLASS
	private static void createMenuGlass() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(" "));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "MENU_GLASS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		MenuGlass = itemStack;
	}
	// RED_GLASS
	private static void createRedGlass() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat("&fClick Craft!"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "RED_GLASS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		RedGlass = itemStack;
	}
	// MENU_WARPS
	private static void createWarpMenu() {
		ItemStack itemStack = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&d&lWarps")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&5Warp to different locations!"));
		lore.add(Utils.chat("&c(NOTE: If you are in combat"));
		lore.add(Utils.chat("&cyou cannot warp out!)"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);


		NBTItem nbti = new NBTItem(itemStack);

		nbti.addCompound("CUSTOM_ID");
		nbti.addCompound("ENERGY_ABILITY");
		nbti.setString("CUSTOM_ID", "MENU_WARPS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();
		WarpMenu = itemStack;
	}
	// MENU_CRAFTING
	private static void createCraftingTable() {
		ItemStack itemStack = new ItemStack(Material.WORKBENCH, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&6&lCustom Crafting")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3Use this to craft custom items,"));
		lore.add(Utils.chat("&3like weapons and armor!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "MENU_CRAFTING");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CraftingMenu = itemStack;
	}
	// MENU_ECHEST
	private static void createEnderChest() {
		ItemStack itemStack = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&a&lEnder Chest")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&9Extra storage!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "MENU_ECHEST");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		EnderChest = itemStack;
	}
	//MENU_SETTINGS
	private static void createSettings() {
		ItemStack itemStack = new ItemStack(Material.COMMAND_MINECART, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&c&lSettings")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Change settings, like turning on"));
		lore.add(Utils.chat("&4debug mode, or using night vision!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "MENU_SETTINGS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Settings = itemStack;
	}
	// CLOSE_MENU
	private static void createClose() {
		ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&l&bClose")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CLOSE_MENU");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Close = itemStack;
	}
	// NIGHT_VISION_TOGGLE
	private static void createNightVisionToggle() {
		ItemStack itemStack = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&l&3Toggle Night Vision")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "NIGHT_VISION_TOGGLE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		ToggleNightVision = itemStack;
	}
	private static void createDebug() {
		ItemStack itemStack = new ItemStack(Material.RECORD_12, 1);
		ItemMeta meta = itemStack.getItemMeta();

		meta.setDisplayName(Utils.chat("&cDebug"));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3secret information"));
		meta.setLore(lore);

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "DEBUG");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Debug = itemStack;
	}
	// BACK_TO_MENU
	private static void createBackToMenu() {
		ItemStack itemStack = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&a&lHotslicerRPG Menu")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "BACK_TO_MENU");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		BackToMenu = itemStack;
	}
	// NOT_RELEASED
	private static void createNotReleased() {
		ItemStack itemStack = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&4&lNot Released Yet")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "NOT_RELEASED");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		NotReleased = itemStack;
	}
	// ETGrowth
	private static void createETGrowth() {
		ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&lGrowth")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("Grants player health.");
		lore.add("15 health per level.");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "ET_GROWTH");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		ETGrowth = itemStack;
	}
	// ETProt
	private static void createETProt() {
		ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&lGProtection")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("Grants player defense.");
		lore.add("3 health per level.");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "ETProt");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		ETProt = itemStack;
	}

	private static void createCustomItemClick() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&c&lClick an Item!")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Click an item in your inventory"));
		lore.add(Utils.chat("&4to add custom traits to it!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_ITEM_EMPTY");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CustomItemClick = itemStack;
	}
	private static void createDisplayName() {
		ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&a&lDisplay Name")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&2Create a custom item name!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_NAME");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		DisplayName = itemStack;
	}
	private static void createAbilities() {
		ItemStack itemStack = new ItemStack(Material.REDSTONE, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&c&lItem Abilities")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4NOTE: (This feature is a WIP,"));
		lore.add(Utils.chat("&4and will be released at a later date!)"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_ABILITIES");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Abilities = itemStack;
	}
	private static void createLore() {
		ItemStack itemStack = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&b&lItem Lore")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&9Change the lore on an item!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_LORE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Lore = itemStack;
	}
	private static void createLore0() {
		ItemStack itemStack = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&b&lSet line")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "LORELINE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		LoreLine = itemStack;
	}
	private static void createCustomItemID() {
		ItemStack itemStack = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&b&lCustom ID")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&9Change the ID on an item!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);

		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_ITEM_ID");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CustomItemID = itemStack;
	}
	private static void createCustomNBTTags() {
		ItemStack itemStack = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&b&lNBT Tags")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&9Change NBT tags!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_NBT_TAGS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CustomNBTTags = itemStack;
	}
	private static void createNBT() {
		ItemStack itemStack = new ItemStack(Material.IRON_INGOT, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&b&lNBT")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&9Change NBT tags!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_NBT");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		NBT = itemStack;
	}

	private static void createLoreGenerator() {
		ItemStack itemStack = new ItemStack(Material.BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&c&lLore Generator")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Generates a custom lore based"));
		lore.add(Utils.chat("&4on the item stats!"));
		lore.add(Utils.chat("&c(NOTE: You can use the set feature"));
		lore.add(Utils.chat("&cto add custom lore!)"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_LORE_GENERATOR");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		LoreGenerator = itemStack;
	}
	private static void createEnchantments() {
		ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&c&lEnchantment Creator")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&4Allows you to set any enchantment"));
		lore.add(Utils.chat("&4with any level as an integer!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_ENCHANTMENTS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Enchantments = itemStack;
	}
	private static void createUnbreakable() {
		ItemStack itemStack = new ItemStack(Material.GLASS, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9&lSet Unbreakable")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3Allows the item to be unbreakable!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_UNBREAKABLE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		Unbreakable = itemStack;
	}
	private static void createCustomRepair() {
		ItemStack itemStack = new ItemStack(Material.IRON_AXE, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9&lRepair Item")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3Repairs the item!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_REPAIR");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CustomRepair = itemStack;
	}
	private static void createHideFlags() {
		ItemStack itemStack = new ItemStack(Material.BANNER, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9&lHide Flags")));
		meta.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&3Hide tags on the item, such as the"));
		lore.add(Utils.chat("&3unbreakable, enchantments, or attribute tags!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CUSTOM_HIDEFLAGS");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		HideFlags = itemStack;
	}

	private static void createCrystalBlue() {
		ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 6);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9Crystallized Lapis")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL");
		nbti.setString("VARIANT", "BLUE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalBlue = itemStack;
	}
	private static void createCrystalRed() {
		ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&cCrystallized Redstone")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL");
		nbti.setString("VARIANT", "RED");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalRed = itemStack;
	}
	private static void createCrystalCorrupt() {
		ItemStack itemStack = new ItemStack(Material.QUARTZ, 1);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9Corrupted Crystal")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL");
		nbti.setString("VARIANT", "CORRUPT");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalCorrupt = itemStack;
	}
	private static void createCrystalGold() {
		ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&6Molten Gold")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL");
		nbti.setString("VARIANT", "GOLD");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalGold = itemStack;
	}

	private static void createCrystalOreBlue() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS, 65, (byte) 11);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9Crystallized Lapis Ore")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL_ORE");
		nbti.setString("VARIANT", "BLUE");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalOreBlue = itemStack;
	}
	private static void createCrystalOreRed() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS, 64, (byte) 14);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&cCrystallized Redstone Ore")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL_ORE");
		nbti.setString("VARIANT", "RED");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalOreRed = itemStack;
	}
	private static void createCrystalOreCorrupt() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS, 64, (byte) 10);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&9Corrupted Crystal Ore")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL_ORE");
		nbti.setString("VARIANT", "CORRUPT");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalOreCorrupt = itemStack;
	}
	private static void createCrystalOreGold() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS, 64, (byte) 4);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&r&6Molten Gold Ore")));
		List<String> lore = new ArrayList<>();
		lore.add(Utils.chat("&7Found deep in the caves, this"));
		lore.add(Utils.chat("&7item can be used to craft items!"));
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemStack.setItemMeta(meta);
		NBTItem nbti = new NBTItem(itemStack);

		nbti.setString("CUSTOM_ID", "CRYSTAL_ORE");
		nbti.setString("VARIANT", "GOLD");
		nbti.setBoolean("ENERGY_ABILITY", false);

		itemStack = nbti.getItem();

		CrystalOreGold = itemStack;
	}

	private static void createCoolCP() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "YEEE");
		NBTCompound nbtc = nbti.getOrCreateCompound("STATS");
		nbtc.setDouble("HEALTH", 350.0);
		nbtc.setDouble("DEFENSE", 200.0);
		nbtc.setDouble("ENERGY", 200.0);
		nbtc.setDouble("STRENGTH", 75.0);
		nbtc.setDouble("MAGIC_DAMAGE", 50.0);
		nbti.setBoolean("ENERGY_ABILITY", false);
		itemStack = nbti.getItem();

		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&c&lcp")));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.setLore(generateLore(itemStack));

		itemStack.setItemMeta(meta);

		CoolCP = itemStack;
	}
	private static void createCoolLG() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_LEGGINGS, 1);

		NBTItem nbti = new NBTItem(itemStack);
		nbti.setString("CUSTOM_ID", "YEEE");
		NBTCompound nbtc = nbti.getOrCreateCompound("STATS");
		nbtc.setDouble("HEALTH", 350.0);
		nbtc.setDouble("DEFENSE", 200.0);
		nbtc.setDouble("ENERGY", 200.0);
		nbtc.setDouble("STRENGTH", 75.0);
		nbtc.setDouble("MAGIC_DAMAGE", 50.0);
		nbti.setBoolean("ENERGY_ABILITY", false);
		itemStack = nbti.getItem();

		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(Utils.chat(Utils.chat("&c&lcp")));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.setLore(generateLore(itemStack));

		itemStack.setItemMeta(meta);

		CoolLG = itemStack;
	}

	//Profile
	public static ItemStack head(String DisplayName, String Name, String... lore) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(Utils.chat(DisplayName));
		meta.setLore(Arrays.asList(lore));
		head.setItemMeta(meta);

		NBTItem nbti = new NBTItem(head);
		NBTCompound skull = nbti.addCompound("SkullOwner");
		skull.setString("Name", Name);

		head = nbti.getItem();
		return(head);
	}

	public static ItemStack playerhead(String DisplayName, String ID, String Value, String... lore) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(Utils.chat(DisplayName));
		meta.setLore(Arrays.asList(lore));
		head.setItemMeta(meta);

		NBTItem nbti = new NBTItem(head);
		NBTCompound skull = nbti.addCompound("SkullOwner");
		skull.setString("Id", ID);
		NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();

		texture.setString("Value", Value);

		head = nbti.getItem();
		return(head);
	}

	public static ItemStack createUUID(ItemStack itemStack) {
		NBTItem nbti = new NBTItem(itemStack);
		String str = randomChars((int) (4 + (Math.random() * 6))) + "-" + (int) (Math.random() * ((int) (1000 + (Math.random() * 900000)))) + "-" + randomChars((int) (4 + (Math.random() * 6)));
		nbti.setString("UUID", str);
		itemStack = nbti.getItem();
		Bukkit.getLogger().log(Level.INFO, "Created new " + itemStack.getItemMeta().getDisplayName() + " with UUID " + str);
		return itemStack;
	}

	private static String randomChars(int charNum) {
		Random r = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(charNum);
		for (int i = 0; i < charNum; i++) {
			int index
					= (int)(alphabet.length()
					* Math.random());
			sb.append(alphabet
					.charAt(index));
		}
		return sb.toString();
	}

	public static List<String> generateLore(ItemStack itemStack) {
		NBTItem inbt = new NBTItem(itemStack);
		List<String> lore = new ArrayList<>();
		NBTCompound nbtc = inbt.getCompound("ENCHANTMENTS");
		NBTCompound nbtb = inbt.getCompound("STATS");
		if (nbtb != null) {
			if (nbtb.hasKey("STRENGTH")) lore.add(Utils.chat("&7Strength: &c+" + nbtb.getInteger("STRENGTH")));
			if (nbtb.hasKey("MAGIC_DAMAGE")) lore.add(Utils.chat("&7Magic Damage: &d+" + nbtb.getInteger("MAGIC_DAMAGE") + "%"));
			if ((nbtb.hasKey("STRENGTH") || nbtb.hasKey("MAGIC_DAMAGE")) && (nbtb.hasKey("HEALTH") || nbtb.hasKey("DEFENSE"))) lore.add("");

			if (nbtb.hasKey("HEALTH") && nbtc != null && nbtc.hasKey("GROWTH")) lore.add(Utils.chat("&7Health: &a+" + nbtb.getInteger("HEALTH") + " &8(+" + (nbtc.getInteger("GROWTH") * 15) + ")"));
			else if (nbtb.hasKey("HEALTH")) lore.add(Utils.chat("&7Health: &a+" + nbtb.getInteger("HEALTH")));
			if (nbtb.hasKey("DEFENSE") && nbtc != null && nbtc.hasKey("PROTECTION")) lore.add(Utils.chat("&7Defense: &a+" + nbtb.getInteger("DEFENSE") + " &8(+" + (nbtc.getInteger("PROTECTION") * 3) + ")"));
			else if (nbtb.hasKey("DEFENSE")) lore.add(Utils.chat("&7Defense: &a+" + nbtb.getInteger("DEFENSE")));
		}
		if (nbtc != null) {
			lore.add("");
			for (String str : nbtc.getKeys()) {
				String[] things = str.toLowerCase().split("_");
				List<String> list = Arrays.asList(things);
				StringBuilder strbuilder = new StringBuilder();
				for (int i = 0; i < list.size(); i++) {
					list.set(i, list.get(i).substring(0, 1).toUpperCase() + list.get(i).substring(1));
					if (i == list.size() - 1) {
						strbuilder.append(list.get(i));
					} else strbuilder.append(list.get(i)).append(" ");
				}
				lore.add(Utils.chat("&9" + strbuilder + " " + Utils.toRoman(nbtc.getInteger(str))));
			}
		}
		if (inbt.hasKey("CUSTOM_LORE")) {
			lore.add("");
			String[] list = inbt.getString("CUSTOM_LORE").split("&nl");
			lore.addAll(Arrays.asList(list));
		}
		return lore;
	}
}
