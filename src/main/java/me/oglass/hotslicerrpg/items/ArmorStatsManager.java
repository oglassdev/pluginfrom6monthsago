package me.oglass.hotslicerrpg.items;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class ArmorStatsManager {
    public static HashMap<UUID, ItemStack> PlayerBoots;
    public static HashMap<UUID, ItemStack> PlayerLeggings;
    public static HashMap<UUID, ItemStack> PlayerChestplate;
    public static HashMap<UUID, ItemStack> PlayerHelmet;

    public static HashMap<UUID, Double> PlayerBootsDefense;
    public static HashMap<UUID, Double> PlayerLeggingsDefense;
    public static HashMap<UUID, Double> PlayerChestplateDefense;
    public static HashMap<UUID, Double> PlayerHelmetDefense;

    public static HashMap<UUID, Double> PlayerBootsHealth;
    public static HashMap<UUID, Double> PlayerLeggingsHealth;
    public static HashMap<UUID, Double> PlayerChestplateHealth;
    public static HashMap<UUID, Double> PlayerHelmetHealth;

    public static void setupArmorStats() {
        PlayerBoots = new HashMap<>();
        PlayerBootsDefense = new HashMap<>();
        PlayerBootsHealth = new HashMap<>();

        PlayerLeggings = new HashMap<>();
        PlayerLeggingsDefense = new HashMap<>();
        PlayerLeggingsHealth = new HashMap<>();
        
        PlayerChestplate = new HashMap<>();
        PlayerChestplateDefense = new HashMap<>();
        PlayerChestplateHealth = new HashMap<>();

        PlayerHelmet = new HashMap<>();
        PlayerHelmetDefense = new HashMap<>();
        PlayerHelmetHealth = new HashMap<>();
    }

    public static Double getBootsHealth(Player p) {
        if (!PlayerBootsHealth.containsKey(p.getUniqueId()) || PlayerBootsHealth.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerBootsHealth.get(p.getUniqueId());
    }
    public static Double getBootsDefense(Player p) {
        if (!PlayerBootsDefense.containsKey(p.getUniqueId()) || PlayerBootsDefense.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerBootsDefense.get(p.getUniqueId());
    }
    public static Double getLeggingsHealth(Player p) {
        if (!PlayerLeggingsHealth.containsKey(p.getUniqueId()) || PlayerLeggingsHealth.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerLeggingsHealth.get(p.getUniqueId());
    }
    public static Double getLeggingsDefense(Player p) {
        if (!PlayerLeggingsDefense.containsKey(p.getUniqueId()) || PlayerLeggingsDefense.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerLeggingsDefense.get(p.getUniqueId());
    }
    public static Double getChestplateHealth(Player p) {
        if (!PlayerChestplateHealth.containsKey(p.getUniqueId()) || PlayerChestplateHealth.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerChestplateHealth.get(p.getUniqueId());
    }
    public static Double getChestplateDefense(Player p) {
        if (!PlayerChestplateDefense.containsKey(p.getUniqueId()) || PlayerChestplateDefense.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerChestplateDefense.get(p.getUniqueId());
    }
    public static Double getHelmetHealth(Player p) {
        if (!PlayerHelmetHealth.containsKey(p.getUniqueId()) || PlayerHelmetHealth.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerHelmetHealth.get(p.getUniqueId());
    }
    public static Double getHelmetDefense(Player p) {
        if (!PlayerHelmetDefense.containsKey(p.getUniqueId()) || PlayerHelmetDefense.get(p.getUniqueId()) == null) return 0.0;
        else return PlayerHelmetDefense.get(p.getUniqueId());
    }

    public static void setBootsStats(Player p, ItemStack ArmorPiece) {
        if (ArmorPiece != null) {
            NBTItem nbti = new NBTItem(ArmorPiece);
            NBTCompound nbtc = null;
            Double GrowthHealth = 0.0;
            Double ProtectionDefense = 0.0;
            if (nbti.getCompound("ENCHANTMENTS") != null) {
                nbtc = nbti.getCompound("ENCHANTMENTS");
                GrowthHealth = nbtc.getInteger("GROWTH") * 15.0;
                ProtectionDefense = nbtc.getInteger("PROTECTION") * 3.0;
            }
            if (!nbti.hasKey("CUSTOM_ID")) {
                PlayerBoots.put(p.getUniqueId(), ArmorPiece);
                PlayerBootsDefense.put(p.getUniqueId(), vanillaDefenseValues(ArmorPiece) + ProtectionDefense);
                PlayerBootsHealth.put(p.getUniqueId(), GrowthHealth);
            } else {
                PlayerBoots.put(p.getUniqueId(), ArmorPiece);
                PlayerBootsDefense.put(p.getUniqueId(), nbti.getInteger("BASE_DEFENSE") + ProtectionDefense);
                PlayerBootsHealth.put(p.getUniqueId(), nbti.getInteger("BASE_HEALTH") + GrowthHealth);
            }
        } else {
            PlayerBoots.put(p.getUniqueId(), null);
            PlayerBootsDefense.put(p.getUniqueId(), 0.0);
            PlayerBootsHealth.put(p.getUniqueId(), 0.0);
        }
    }
    public static void setLeggingsStats(Player p, ItemStack ArmorPiece) {
        if (ArmorPiece != null) {
            NBTItem nbti = new NBTItem(ArmorPiece);
            NBTCompound nbtc = null;
            Double GrowthHealth = 0.0;
            Double ProtectionDefense = 0.0;
            if (nbti.getCompound("ENCHANTMENTS") != null) {
                nbtc = nbti.getCompound("ENCHANTMENTS");
                GrowthHealth = nbtc.getInteger("GROWTH") * 15.0;
                ProtectionDefense = nbtc.getInteger("PROTECTION") * 3.0;
            }
            if (!nbti.hasKey("CUSTOM_ID")) {
                PlayerLeggings.put(p.getUniqueId(), ArmorPiece);
                PlayerLeggingsDefense.put(p.getUniqueId(), vanillaDefenseValues(ArmorPiece) + ProtectionDefense);
                PlayerLeggingsHealth.put(p.getUniqueId(), GrowthHealth);
            } else {
                PlayerLeggings.put(p.getUniqueId(), ArmorPiece);
                PlayerLeggingsDefense.put(p.getUniqueId(), nbti.getInteger("BASE_DEFENSE") + ProtectionDefense);
                PlayerLeggingsHealth.put(p.getUniqueId(), nbti.getInteger("BASE_HEALTH") + GrowthHealth);
            }
        } else {
            PlayerLeggings.put(p.getUniqueId(), null);
            PlayerLeggingsDefense.put(p.getUniqueId(), 0.0);
            PlayerLeggingsHealth.put(p.getUniqueId(), 0.0);
        }
    }
    public static void setChestplateStats(Player p, ItemStack ArmorPiece) {
        if (ArmorPiece != null) {
            NBTItem nbti = new NBTItem(ArmorPiece);
            NBTCompound nbtc = null;
            Double GrowthHealth = 0.0;
            Double ProtectionDefense = 0.0;
            if (nbti.getCompound("ENCHANTMENTS") != null) {
                nbtc = nbti.getCompound("ENCHANTMENTS");
                GrowthHealth = nbtc.getInteger("GROWTH") * 15.0;
                ProtectionDefense = nbtc.getInteger("PROTECTION") * 3.0;
            }
            if (!nbti.hasKey("CUSTOM_ID")) {
                PlayerChestplate.put(p.getUniqueId(), ArmorPiece);
                PlayerChestplateDefense.put(p.getUniqueId(), vanillaDefenseValues(ArmorPiece) + ProtectionDefense);
                PlayerChestplateHealth.put(p.getUniqueId(), GrowthHealth);
            } else {
                PlayerChestplate.put(p.getUniqueId(), ArmorPiece);
                PlayerChestplateDefense.put(p.getUniqueId(), nbti.getInteger("BASE_DEFENSE") + ProtectionDefense);
                PlayerChestplateHealth.put(p.getUniqueId(), nbti.getInteger("BASE_HEALTH") + GrowthHealth);
            }
        } else {
            PlayerChestplate.put(p.getUniqueId(), null);
            PlayerChestplateDefense.put(p.getUniqueId(), 0.0);
            PlayerChestplateHealth.put(p.getUniqueId(), 0.0);
        }
    }
    public static void setHelmetStats(Player p, ItemStack ArmorPiece) {
        if (ArmorPiece != null) {
            NBTItem nbti = new NBTItem(ArmorPiece);
            NBTCompound nbtc = null;
            Double GrowthHealth = 0.0;
            Double ProtectionDefense = 0.0;
            if (nbti.getCompound("ENCHANTMENTS") != null) {
                nbtc = nbti.getCompound("ENCHANTMENTS");
                GrowthHealth = nbtc.getInteger("GROWTH") * 15.0;
                ProtectionDefense = nbtc.getInteger("PROTECTION") * 3.0;
            }
            if (!nbti.hasKey("CUSTOM_ID")) {
                PlayerHelmet.put(p.getUniqueId(), ArmorPiece);
                PlayerHelmetDefense.put(p.getUniqueId(), vanillaDefenseValues(ArmorPiece) + ProtectionDefense);
                PlayerHelmetHealth.put(p.getUniqueId(), GrowthHealth);
            } else {
                PlayerHelmet.put(p.getUniqueId(), ArmorPiece);
                PlayerHelmetDefense.put(p.getUniqueId(), nbti.getInteger("BASE_DEFENSE") + ProtectionDefense);
                PlayerHelmetHealth.put(p.getUniqueId(), nbti.getInteger("BASE_HEALTH") + GrowthHealth);
            }
        } else {
            PlayerHelmet.put(p.getUniqueId(), null);
            PlayerHelmetDefense.put(p.getUniqueId(), 0.0);
            PlayerHelmetHealth.put(p.getUniqueId(), 0.0);
        }
    }

    public static Double vanillaDefenseValues(ItemStack ArmorPiece) {
        // Boots or helmets
        if (ArmorPiece.getType().equals(Material.LEATHER_BOOTS) || ArmorPiece.getType().equals(Material.LEATHER_HELMET)) {
            return 2.0;
        } else if (ArmorPiece.getType().equals(Material.CHAINMAIL_BOOTS) || ArmorPiece.getType().equals(Material.CHAINMAIL_HELMET)) {
            return 4.0;
        } else if (ArmorPiece.getType().equals(Material.IRON_BOOTS) || ArmorPiece.getType().equals(Material.IRON_HELMET)) {
            return 6.0;
        } else if (ArmorPiece.getType().equals(Material.DIAMOND_BOOTS) || ArmorPiece.getType().equals(Material.DIAMOND_HELMET)) {
            return 10.0;
        }
        // Leggings
        else if (ArmorPiece.getType().equals(Material.LEATHER_LEGGINGS)) {
            return 5.0;
        } else if (ArmorPiece.getType().equals(Material.CHAINMAIL_LEGGINGS)) {
            return 8.0;
        } else if (ArmorPiece.getType().equals(Material.IRON_LEGGINGS)) {
            return 12.0;
        } else if (ArmorPiece.getType().equals(Material.DIAMOND_LEGGINGS)) {
            return 15.0;
        }
        // Chestplates
        else if (ArmorPiece.getType().equals(Material.LEATHER_CHESTPLATE)) {
            return 6.0;
        } else if (ArmorPiece.getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
            return 10.0;
        } else if (ArmorPiece.getType().equals(Material.IRON_CHESTPLATE)) {
            return 14.0;
        } else if (ArmorPiece.getType().equals(Material.DIAMOND_CHESTPLATE)) {
            return 16.0;
        } else return 0.0;
    }
}
