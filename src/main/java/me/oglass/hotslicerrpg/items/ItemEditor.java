package me.oglass.hotslicerrpg.items;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.enums.EnchantType;
import me.oglass.hotslicerrpg.enums.PlayerStat;

public class ItemEditor {
    public static void addEnchant(NBTItem nbtItem, EnchantType type, Integer level) {
        NBTCompound nbtc = nbtItem.getOrCreateCompound("ENCHANTMENTS");
        if (type.getMaxLevel() > 0) {
            if (level < type.getMaxLevel()) nbtc.setInteger(type.getName(), level);
        } else nbtc.setInteger(type.getName(), level);
    }
    public static void addPlayerStat(NBTItem nbtItem, PlayerStat playerStat, Integer amount) throws NullPointerException {
        if (playerStat == PlayerStat.MaxEnergy || playerStat == PlayerStat.MaxHealth) {
            throw new NullPointerException("You may not use the stat: " + playerStat.name());
        }
        NBTCompound nbtc = nbtItem.getOrCreateCompound("STATS");
        nbtc.setInteger(playerStat.getName(), Integer.valueOf(amount));
    }
}