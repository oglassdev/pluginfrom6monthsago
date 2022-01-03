package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.enums.EnchantType;
import me.oglass.hotslicerrpg.items.Admin;
import me.oglass.hotslicerrpg.items.ItemEditor;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.playerstats.PlayerStats;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerChatListener implements Listener {

    private final Main plugin;
    private Integer TaskID;

    public PlayerChatListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String message = event.getMessage();
        if (Admin.PlayerTime.containsKey(p.getUniqueId()) && Admin.PlayerTime.get(p.getUniqueId()) > System.currentTimeMillis()) {
            event.setCancelled(true);
            if (Admin.Players.get(p.getUniqueId()).equals("DisplayName")) {
                p.sendMessage(Utils.chat("&aSet item name to " + message));
                ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(Utils.chat(message));
                item.setItemMeta(meta);
                if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta() != null) {
                    MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                    p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                }
            }
            else if (Admin.Players.get(p.getUniqueId()).equals("Lore")) {
                int integer = Admin.LoreInt.get(p.getUniqueId()) - 1;
                p.sendMessage(Utils.chat("&aSet line " + (integer + 1) + " to " + message));
                ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                ItemMeta meta = item.getItemMeta();

                List<String> lore = new ArrayList<>();
                List<String> currentlore = new ArrayList<>();
                if (meta.getLore() != null) {
                    currentlore = meta.getLore();
                }
                for (int i = 0; i <= 30; i++) {
                    lore.add("");
                }
                for (int i = 0; i < currentlore.size(); i++) {
                    lore.set(i, currentlore.get(i));
                }
                int inte = 0;
                for (int i = 0; i <= 30; i++) {
                    if (i == integer) {
                        lore.set(i, Utils.chat(message));
                    }
                    if (!lore.get(i).equals("")) {
                        inte = i + 1;
                    }
                }
                lore.subList(inte, 31).clear();

                p.sendMessage(lore.size() + ", " + integer);

                meta.setLore(lore);
                item.setItemMeta(meta);
                if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta() != null) {
                    MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                    p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                }
                Admin.LoreInt.remove(p.getUniqueId());
            }
            else if (Admin.Players.get(p.getUniqueId()).equals("CustomID")) {
                if (!isAlpha(message)) {
                    p.sendMessage(Utils.chat("&cYou may only use letters!"));
                    return;
                }
                p.sendMessage(Utils.chat("&aSet &l&cCustom ID&r&a to &c" + message.toUpperCase()));
                ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                NBTItem nbti = new NBTItem(item);
                nbti.setString("CUSTOM_ID", message.toUpperCase());
                item = nbti.getItem();
                if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta() != null) {
                    MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                    p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                }
            }
            else if (Admin.Players.get(p.getUniqueId()).equals("NBTTags")) {
                String[] things = message.split(":");
                List<String> list = Arrays.asList(things);
                if (list.size() == 2) {
                    p.sendMessage(Utils.chat("&aSet &l&c"+ list.get(0) +"&r&a as &l&c" + list.get(1)));
                    ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                    NBTItem nbti = new NBTItem(item);
                    if (isInteger(list.get(1))) nbti.setInteger(list.get(0), Integer.valueOf(list.get(1)));
                    else if (list.get(1).equalsIgnoreCase("true")) nbti.setBoolean(list.get(0), true);
                    else if (list.get(1).equalsIgnoreCase("false")) nbti.setBoolean(list.get(0), false);
                    else nbti.setString(list.get(0), list.get(1));
                    item = nbti.getItem();
                    if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta() != null) {
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                        p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                    }
                } else {
                    p.sendMessage(Utils.chat("&cIncorrect Syntax!"));
                    return;
                }
            }
            else if (Admin.Players.get(p.getUniqueId()).equals("NBTEnchantments")) {
                String[] things = message.toUpperCase().split(":");
                List<String> list = Arrays.asList(things);
                if (list.size() == 2) {
                    ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                    NBTItem nbti = new NBTItem(item);
                    if (list.get(0).contains(" ")) {
                        p.sendMessage(Utils.chat("&cYou may not have any spaces in the enchantment name!"));
                        return;
                    }
                    if (isInteger(list.get(1))) {
                        if (EnchantType.getEnchant(list.get(0)) != null) ItemEditor.addEnchant(nbti, EnchantType.getEnchant(list.get(0)), Integer.parseInt(list.get(1)));
                        else {
                            p.sendMessage(Utils.chat("&cInvalid enchant! Valid enchants:"));
                            for (EnchantType type : EnchantType.values()) {
                                p.sendMessage(Utils.chat("&c" + type.getName()));
                            }
                            return;
                        }
                    } else {
                        p.sendMessage(Utils.chat("&cYou may only use an integer for the value!"));
                        return;
                    }
                    p.sendMessage(Utils.chat("&aSet &l&c"+ list.get(0) +"&r&a as &l&c" + list.get(1)));
                    item = nbti.getItem();
                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);
                    if (item != null && !item.getType().equals(Material.AIR) && item.getItemMeta() != null) {
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                        p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                    }
                } else {
                    p.sendMessage(Utils.chat("&cIncorrect Syntax!"));
                    return;
                }
            }
            else if (isInteger(message) && Integer.parseInt(message) > 0 && Integer.parseInt(message) <= 200000) {
                if (Admin.Players.get(p.getUniqueId()).equals(Admin.Health)) {
                    p.sendMessage(Utils.chat("&aSet &cHealth&a to " + message));
                    PlayerStats.setPlayerStat(p, PlayerStat.Health, (double) Integer.parseInt(message));
                }
                else if (Admin.Players.get(p.getUniqueId()).equals(Admin.MaxHealth)) {
                    p.sendMessage(Utils.chat("&aSet Max &cHealth&a to " + message));
                    PlayerStats.setPlayerStat(p, PlayerStat.MaxHealth, (double) Integer.parseInt(message));
                }
                else if (Admin.Players.get(p.getUniqueId()).equals(Admin.Defense)) {
                    p.sendMessage(Utils.chat("&aSet Defense to " + message));
                    PlayerStats.setPlayerStat(p, PlayerStat.Defense, (double) Integer.parseInt(message));
                }
                else if (Admin.Players.get(p.getUniqueId()).equals(Admin.Energy)) {
                    p.sendMessage(Utils.chat("&aSet &6Energy&a to " + message));
                    PlayerStats.setPlayerStat(p, PlayerStat.Energy, (double) Integer.parseInt(message));
                }
                else if (Admin.Players.get(p.getUniqueId()).equals(Admin.MaxEnergy)) {
                    p.sendMessage(Utils.chat("&aSet Max &6Energy&a to " + message));
                    PlayerStats.setPlayerStat(p, PlayerStat.MaxEnergy, (double) Integer.parseInt(message));
                }
                else if (Admin.Players.get(p.getUniqueId()).equals("ItemLoreSet")) {
                    if (Integer.parseInt(message) > 30) {
                        p.sendMessage(Utils.chat("&aYou may not have more than 30 lines!"));
                    } else {
                        p.sendMessage("");
                        p.sendMessage(Utils.chat("&aYou are now editing line " + message + "."));
                        p.sendMessage(Utils.chat("&aYour next message within 30 seconds"));
                        p.sendMessage(Utils.chat("&awill be line " + message + " of the item."));
                        Admin.Players.remove(p.getUniqueId());
                        Admin.PlayerTime.remove(p.getUniqueId());
                        Admin.LoreInt.put(p.getUniqueId(), Integer.parseInt(message));
                        Admin.Players.put(p.getUniqueId(), "Lore");
                        Admin.PlayerTime.put(p.getUniqueId(), System.currentTimeMillis() + 30000);
                        return;
                    }
                }
            } else {
                p.sendMessage(Utils.chat("&4Please enter a valid number!"));
            }
        }
        Admin.Players.remove(p.getUniqueId());
        Admin.PlayerTime.remove(p.getUniqueId());
        // Admin.LoreInt.remove(p.getUniqueId());
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z_]+");
    }
}
