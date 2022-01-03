package me.oglass.hotslicerrpg.items;

import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Admin {
    public static String Health = "Health";
    public static String MaxHealth = "Max Health";

    public static String Defense = "Defense";

    public static String Energy = "Energy";
    public static String MaxEnergy = "Max Energy";

    public static HashMap<UUID, String> Players;
    public static HashMap<UUID, Long> PlayerTime;
    public static HashMap<UUID, Integer> LoreInt;

    public static void init() {
        Players = new HashMap<>();
        PlayerTime = new HashMap<>();
        LoreInt = new HashMap<>();
    }

    public static void setPlayer(Player player, String stat) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, stat);
            PlayerTime.put(uuid, System.currentTimeMillis() + 15000);
            player.sendMessage(Utils.chat("&aYou have &c15 seconds&a to type a number in chat!"));
        } else {
            player.sendMessage(Utils.chat("&4You selected a stat within"));
            player.sendMessage(Utils.chat("&4the last 15 seconds, please wait!"));
        }
    }
    public static void setItemName(Player player) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, "DisplayName");
            PlayerTime.put(uuid, System.currentTimeMillis() + 20000);
            player.sendMessage(Utils.chat("&aYou have &c20 seconds&a to type a name in chat!"));
        } else {
            player.sendMessage(Utils.chat("&4You have been asked to enter a value within"));
            player.sendMessage(Utils.chat("&4the last 20 seconds, please wait!"));
        }
    }
    public static void setItemLore(Player player) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, "ItemLoreSet");
            PlayerTime.put(uuid, System.currentTimeMillis() + 10000);
            player.sendMessage(Utils.chat("&aYou have &c10 seconds&a to type a number in chat!"));
        } else {
            player.sendMessage(Utils.chat("&4You have been asked to enter a value within"));
            player.sendMessage(Utils.chat("&4the last 10 seconds, please wait!"));
        }
    }
    public static void setItemCustomID(Player player) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, "CustomID");
            PlayerTime.put(uuid, System.currentTimeMillis() + 15000);
            player.sendMessage(Utils.chat("&aYou have &c15 seconds&a to type a string in chat!"));
        } else {
            player.sendMessage(Utils.chat("&4You have been asked to enter a value within"));
            player.sendMessage(Utils.chat("&4the last 15 seconds, please wait!"));
        }
    }
    public static void setItemNBTTags(Player player) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, "NBTTags");
            PlayerTime.put(uuid, System.currentTimeMillis() + 20000);
            player.sendMessage(Utils.chat("&aYou have &c20 seconds&a to type the values in chat!"));
            player.sendMessage(Utils.chat("&ayou can either use an integer, string, or boolean!"));
            player.sendMessage(Utils.chat("&cSyntax: Dog:Cat"));
            player.sendMessage(Utils.chat("&cSyntax: Dog:1"));
            player.sendMessage(Utils.chat("&cSyntax: Dog:true"));
        } else {
            player.sendMessage(Utils.chat("&4You have been asked to enter a value within"));
            player.sendMessage(Utils.chat("&4the last 20 seconds, please wait!"));
        }
    }
    public static void setItemEnchantments(Player player) {
        UUID uuid = player.getUniqueId();
        if (!PlayerTime.containsKey(uuid) || PlayerTime.get(uuid) <= System.currentTimeMillis()) {
            Players.put(uuid, "NBTEnchantments");
            PlayerTime.put(uuid, System.currentTimeMillis() + 20000);
            player.sendMessage(Utils.chat("&aYou have &c20 seconds&a to type the values in chat!"));
            player.sendMessage(Utils.chat("&aYou are allowed to use any enchant and number!"));
            player.sendMessage(Utils.chat("&aSyntax Example: Protection:5"));
        } else {
            player.sendMessage(Utils.chat("&4You have been asked to enter a value within"));
            player.sendMessage(Utils.chat("&4the last 20 seconds, please wait!"));
        }
    }
}
