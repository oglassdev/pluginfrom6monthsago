package me.oglass.hotslicerrpg.items;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MenuManager {

    public static HashMap<UUID, Inventory> slicermenu = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, Inventory> settingsMenu = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, Inventory> warpMenu = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, Inventory> classMenu = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, Inventory> givemenu = new HashMap<UUID, Inventory>();
    // public static HashMap<UUID, Inventory> enchanttable = new HashMap<UUID, Inventory>();
    public static HashMap<UUID, Inventory> craftingMenu = new HashMap<UUID, Inventory>();

    // Admin Stuff
    public static HashMap<UUID, Inventory> adminControls = new HashMap<>();
    public static HashMap<UUID, Inventory> adminStats = new HashMap<>();
    public static void createAdminMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 45, Utils.chat("&r&4&lAdmin Controls"));
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemManager.MenuGlass);
        }

        inv.setItem(11, ItemManager.AdminItems);
        inv.setItem(13, ItemManager.AdminCItems);
        inv.setItem(15, ItemManager.AdminStats);
        inv.setItem(21, ItemManager.NotReleased);
        inv.setItem(23, ItemManager.NotReleased);
        inv.setItem(39, ItemManager.BackToMenu);
        inv.setItem(40, ItemManager.Close);
        adminControls.put(p.getUniqueId(), inv);
    }
    public static void createAdminStats(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&4&lPlayer Stats"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.MenuGlass);
        inv.setItem(11, ItemManager.AdminHealth);
        inv.setItem(12, ItemManager.AdminMaxHealth);
        inv.setItem(13, ItemManager.AdminDefense);
        inv.setItem(14, ItemManager.AdminEnergy);
        inv.setItem(15, ItemManager.AdminMaxEnergy);
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.MenuGlass);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.AdminBack);
        inv.setItem(22, ItemManager.Close);
        inv.setItem(23, ItemManager.MenuGlass);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.MenuGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        adminStats.put(p.getUniqueId(), inv);
    }
    public static HashMap<UUID, Inventory> CustomItem = new HashMap<>();
    public static HashMap<UUID, Inventory> CustomLore = new HashMap<>();
    public static HashMap<UUID, Inventory> CustomNBTC = new HashMap<>();
    public static HashMap<UUID, Inventory> AbilityItem = new HashMap<>();
    public static void createCustomItemMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 54, Utils.chat("&r&4&lCustom Item Maker"));
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemManager.MenuGlass);
        }
        inv.setItem(13, ItemManager.CustomItemClick);
        inv.setItem(21, ItemManager.CustomItemID);
        inv.setItem(23, ItemManager.NBT);
        inv.setItem(33, ItemManager.DisplayName);
        inv.setItem(29, ItemManager.Lore);
        inv.setItem(31, ItemManager.Abilities);
        inv.setItem(48, ItemManager.AdminBack); // DONT TOUCH
        inv.setItem(49, ItemManager.Close); // DONT TOUCH
        CustomItem.put(p.getUniqueId(), inv);
    }
    public static void createCustomLoreMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&4&lCustom Item Maker"));
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemManager.MenuGlass);
        }
        inv.setItem(12, ItemManager.LoreLine);
        inv.setItem(14, ItemManager.LoreGenerator);
        CustomLore.put(p.getUniqueId(), inv);
    }
    public static void createCustomNBTC(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&4&lCustom Item Maker"));
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemManager.MenuGlass);
        }
        inv.setItem(11, ItemManager.CustomNBTTags);
        inv.setItem(12, ItemManager.HideFlags);
        inv.setItem(13, ItemManager.Enchantments);
        inv.setItem(14, ItemManager.CustomRepair);
        inv.setItem(15, ItemManager.Unbreakable);
        CustomNBTC.put(p.getUniqueId(), inv);
    }
    public static void createAbilityItemMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&4&lCustom Item Abilities (COMING SOON)"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.MenuGlass);
        inv.setItem(11, ItemManager.AdminHealth);
        inv.setItem(12, ItemManager.AdminMaxHealth);
        inv.setItem(13, ItemManager.AdminDefense);
        inv.setItem(14, ItemManager.AdminEnergy);
        inv.setItem(15, ItemManager.AdminMaxEnergy);
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.MenuGlass);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.AdminBack);
        inv.setItem(22, ItemManager.Close);
        inv.setItem(23, ItemManager.MenuGlass);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.MenuGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        adminStats.put(p.getUniqueId(), inv);
    }

    public static void createSlicerMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 54, Utils.chat("&d&l" + p.getDisplayName() + "'s Profile"));
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemManager.MenuGlass);
        }
        if (p.isOp()) inv.setItem(0, ItemManager.AdminMenu);
        inv.setItem(13, ItemManager.head("&6&lYour Profile", p.getName()));
        inv.setItem(19, ItemManager.WarpMenu);
        inv.setItem(20, ItemManager.NotReleased);
        inv.setItem(21, ItemManager.ClassMenu);
        inv.setItem(22, ItemManager.CraftingMenu);
        inv.setItem(23, ItemManager.NotReleased);
        inv.setItem(24, ItemManager.NotReleased);
        inv.setItem(25, ItemManager.EnderChest);
        inv.setItem(31, ItemManager.Settings);
        inv.setItem(49, ItemManager.Close);
        slicermenu.put(p.getUniqueId(), inv);
    }
    public static void createGiveMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 54, Utils.chat("&r&6&lCustom Items"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);

        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.WitherSword);
        inv.setItem(11, ItemManager.MenuGlass);
        inv.setItem(12, ItemManager.BoomBoomSword);
        inv.setItem(13, ItemManager.MenuGlass);
        inv.setItem(14, ItemManager.WaterGun);
        inv.setItem(15, ItemManager.MenuGlass);
        inv.setItem(16, ItemManager.GrapplingHook);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.CrystalOreBlue);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.CrystalOreRed);
        inv.setItem(22, ItemManager.MenuGlass);
        inv.setItem(23, ItemManager.CrystalOreCorrupt);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.CrystalOreGold);
        inv.setItem(26, ItemManager.MenuGlass);
        inv.setItem(27, ItemManager.MenuGlass);
        inv.setItem(28, ItemManager.Boomerang);
        inv.setItem(29, ItemManager.MenuGlass);
        inv.setItem(30, DropsManager.BoneFragment);
        inv.setItem(31, ItemManager.MenuGlass);
        inv.setItem(32, ItemManager.MoltenFury);
        inv.setItem(33, ItemManager.MenuGlass);
        inv.setItem(34, DropsManager.MoltenRemnant);
        inv.setItem(35, ItemManager.CoolCP);
        inv.setItem(36, ItemManager.CoolLG);
        inv.setItem(37, ItemManager.MenuGlass);
        inv.setItem(38, ItemManager.MenuGlass);
        inv.setItem(39, ItemManager.MenuGlass);
        inv.setItem(40, ItemManager.MenuGlass);
        inv.setItem(41, ItemManager.MenuGlass);
        inv.setItem(42, ItemManager.MenuGlass);
        inv.setItem(43, ItemManager.MenuGlass);
        inv.setItem(44, ItemManager.MenuGlass);
        inv.setItem(45, ItemManager.MenuGlass);
        inv.setItem(46, ItemManager.MenuGlass);
        inv.setItem(47, ItemManager.MenuGlass);
        inv.setItem(48, ItemManager.AdminBack);
        inv.setItem(49, ItemManager.Close);
        inv.setItem(50, ItemManager.MenuGlass);
        inv.setItem(51, ItemManager.MenuGlass);
        inv.setItem(52, ItemManager.MenuGlass);
        inv.setItem(53, ItemManager.MenuGlass);
        givemenu.put(p.getUniqueId(), inv);
    }
    public static void createCraftingMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 45, Utils.chat("&r&6&lCustom Crafting Table"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        // air
        // air
        // air
        inv.setItem(13, ItemManager.MenuGlass);
        inv.setItem(14, ItemManager.MenuGlass);
        inv.setItem(15, ItemManager.MenuGlass);
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        // air
        // air
        // air
        inv.setItem(22, ItemManager.MenuGlass);
        inv.setItem(23, ItemManager.CraftGo);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.RedGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        inv.setItem(27, ItemManager.MenuGlass);
        // air
        // air
        // air
        inv.setItem(31, ItemManager.MenuGlass);
        inv.setItem(32, ItemManager.MenuGlass);
        inv.setItem(33, ItemManager.MenuGlass);
        inv.setItem(34, ItemManager.MenuGlass);
        inv.setItem(35, ItemManager.MenuGlass);

        inv.setItem(36, ItemManager.MenuGlass);
        inv.setItem(37, ItemManager.MenuGlass);
        inv.setItem(38, ItemManager.MenuGlass);
        inv.setItem(39, ItemManager.MenuGlass);
        inv.setItem(40, ItemManager.MenuGlass);
        inv.setItem(41, ItemManager.MenuGlass);
        inv.setItem(42, ItemManager.MenuGlass);
        inv.setItem(43, ItemManager.MenuGlass);
        inv.setItem(44, ItemManager.MenuGlass);
        craftingMenu.put(p.getUniqueId(), inv);
    }
    public static void createSettingsMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&c&lSettings"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.MenuGlass);
        inv.setItem(11, ItemManager.ToggleNightVision);
        inv.setItem(12, ItemManager.MenuGlass);
        inv.setItem(13, ItemManager.NotReleased);
        inv.setItem(14, ItemManager.MenuGlass);
        inv.setItem(15, ItemManager.Debug);
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.MenuGlass);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.BackToMenu);
        inv.setItem(22, ItemManager.Close);
        inv.setItem(23, ItemManager.MenuGlass);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.MenuGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        settingsMenu.put(p.getUniqueId(), inv);
    }
    public static void createWarpsMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&d&lFast Warps"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.MenuGlass);
// 11
        Main.getPlugin().getConfig().getConfigurationSection("Portals").getKeys(false).forEach(key -> {
            if (Main.getPlugin().getConfig().getString("Portals." + key + ".Item").equalsIgnoreCase("skull")) {
                String[] desc = Main.getPlugin().getConfig().getStringList("Portals." + key + ".Description").toArray(new String[0]);
                ItemStack i = ItemManager.playerhead(Main.getPlugin().getConfig().getString("Portals." + key + ".DisplayName"), Main.getPlugin().getConfig().getString("Portals." + key + ".Head.ID"), Main.getPlugin().getConfig().getString("Portals." + key + ".Head.Value"), Utils.chat(desc));
                NBTItem nbti = new NBTItem(i);
                nbti.setString("CUSTOM_ID", "FWARP");
                nbti.setDouble("X", Main.getPlugin().getConfig().getDouble("Portals." + key + ".X"));
                nbti.setDouble("Y", Main.getPlugin().getConfig().getDouble("Portals." + key + ".Y"));
                nbti.setDouble("Z", Main.getPlugin().getConfig().getDouble("Portals." + key + ".Z"));
                nbti.setString("WORLD", Main.getPlugin().getConfig().getString("Portals." + key + ".World"));

                i = nbti.getItem();
                if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 1) {
                    inv.setItem(11, i);
                } else if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 2) {
                    inv.setItem(13, i);
                } else if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 3) {
                    inv.setItem(15, i);
                }
            } else {
                ItemStack i = new ItemStack(Material.matchMaterial(Main.getPlugin().getConfig().getString("Portals." + key + ".Item")));
                NBTItem nbti = new NBTItem(i);
                nbti.addCompound("CUSTOM_ID");
                nbti.setString("CUSTOM_ID", "FWARP");
                nbti.addCompound("X");
                nbti.setDouble("X", Main.getPlugin().getConfig().getDouble("Portals." + key + ".X"));
                nbti.addCompound("Y");
                nbti.setDouble("Y", Main.getPlugin().getConfig().getDouble("Portals." + key + ".Y"));
                nbti.addCompound("Z");
                nbti.setDouble("Z", Main.getPlugin().getConfig().getDouble("Portals." + key + ".Z"));

                i = nbti.getItem();
                if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 1) {
                    inv.setItem(11, i);
                } else if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 2) {
                    inv.setItem(13, i);
                } else if (Main.getPlugin().getConfig().getInt("Portals." + key + ".Slot") == 3) {
                    inv.setItem(15, i);
                }
            }
        });
        inv.setItem(12, ItemManager.MenuGlass);
// 13
        inv.setItem(14, ItemManager.MenuGlass);
// 15
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.MenuGlass);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.BackToMenu);
        inv.setItem(22, ItemManager.Close);
        inv.setItem(23, ItemManager.MenuGlass);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.MenuGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        warpMenu.put(p.getUniqueId(), inv);
    }
    public static void createClassMenu(Player p) {
        Inventory inv = Bukkit.getServer().createInventory(p, 27, Utils.chat("&r&b&lClasses"));
        inv.setItem(0, ItemManager.MenuGlass);
        inv.setItem(1, ItemManager.MenuGlass);
        inv.setItem(2, ItemManager.MenuGlass);
        inv.setItem(3, ItemManager.MenuGlass);
        inv.setItem(4, ItemManager.MenuGlass);
        inv.setItem(5, ItemManager.MenuGlass);
        inv.setItem(6, ItemManager.MenuGlass);
        inv.setItem(7, ItemManager.MenuGlass);
        inv.setItem(8, ItemManager.MenuGlass);
        inv.setItem(9, ItemManager.MenuGlass);
        inv.setItem(10, ItemManager.MenuGlass);
        inv.setItem(11, ItemManager.Knight);
        inv.setItem(12, ItemManager.MenuGlass);
        inv.setItem(13, ItemManager.Healer);
        inv.setItem(14, ItemManager.MenuGlass);
        inv.setItem(15, ItemManager.Archer);
        inv.setItem(16, ItemManager.MenuGlass);
        inv.setItem(17, ItemManager.MenuGlass);
        inv.setItem(18, ItemManager.MenuGlass);
        inv.setItem(19, ItemManager.MenuGlass);
        inv.setItem(20, ItemManager.MenuGlass);
        inv.setItem(21, ItemManager.BackToMenu);
        inv.setItem(22, ItemManager.Close);
        inv.setItem(23, ItemManager.MenuGlass);
        inv.setItem(24, ItemManager.MenuGlass);
        inv.setItem(25, ItemManager.MenuGlass);
        inv.setItem(26, ItemManager.MenuGlass);
        classMenu.put(p.getUniqueId(), inv);
    }
}
