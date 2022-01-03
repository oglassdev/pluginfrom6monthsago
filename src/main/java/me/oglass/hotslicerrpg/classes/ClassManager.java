package me.oglass.hotslicerrpg.classes;

import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ClassManager {
    public static HashMap<String, String> Players;

    public static HashMap<String, Integer> KnightXP;
    public static HashMap<String, Integer> ArcherXP;
    public static HashMap<String, Integer> HealerXP;

    public static HashMap<Integer, Integer> KnightXPL;
    public static HashMap<Integer, Integer> ArcherXPL;
    public static HashMap<Integer, Integer> HealerXPL;

    public static void setupClasses() {
        Players = new HashMap<>();

        KnightXP = new HashMap<>();
        ArcherXP = new HashMap<>();
        HealerXP = new HashMap<>();

        KnightXPL = new HashMap<>();
        ArcherXPL = new HashMap<>();
        HealerXPL = new HashMap<>();

        KnightXPL.put(0, 0);
        KnightXPL.put(1, 28);
        KnightXPL.put(2, 60);
        KnightXPL.put(3, 130);
        KnightXPL.put(4, 230);
        KnightXPL.put(5, 390);
        KnightXPL.put(6, 2147483647);

        ArcherXPL.put(0, 0);
        ArcherXPL.put(1, 28);
        ArcherXPL.put(2, 60);
        ArcherXPL.put(3, 130);
        ArcherXPL.put(4, 230);
        ArcherXPL.put(5, 390);
        ArcherXPL.put(6, 2147483647);

        HealerXPL.put(0, 0);
        HealerXPL.put(1, 20);
        HealerXPL.put(2, 55);
        HealerXPL.put(3, 110);
        HealerXPL.put(4, 190);
        HealerXPL.put(5, 290);
        HealerXPL.put(6, 2147483647);
    }

    public static void changeClasses(Player p, String Class) {
        Players.put(p.getUniqueId().toString(), Class);
    }

    public static void updateGUI(Player p) {
        Inventory inv = MenuManager.classMenu.get(p.getUniqueId());
        ItemStack k = ItemManager.Knight;
        ItemStack a = ItemManager.Archer;
        ItemStack h = ItemManager.Healer;

        // THE UPDATE THING DO NOT TOUCH
        ItemMeta HMeta = h.getItemMeta();
        List<String> HLore = new ArrayList<String>();
        HLore.add("");
        if (ClassManager.HealerXP.get(p.getUniqueId().toString()) == null || ClassManager.HealerXP.get(p.getUniqueId().toString()) == 0) {
                HLore.add(Utils.chat("&fYou have not played"));
                HLore.add(Utils.chat("&fthis class yet!"));
            } else if (ClassManager.getLevel(p, RPGClasses.Healer) == 5 || ClassManager.getLevel(p, RPGClasses.Healer) == 6) {
                HLore.add(Utils.chat("&fClass level MAXED"));
            } else {
                StringBuilder HStrPro = new StringBuilder();
                for (int i = 0; i < Math.floor(ClassManager.getPercNextLevel(p, RPGClasses.Healer) * 14); i++) {
                    HStrPro.append("&f=");
                }
                for (int i = 0; i < Math.floor(14 - ClassManager.getPercNextLevel(p, RPGClasses.Healer) * 14); i++) {
                    HStrPro.append("&8=");
                }
                HLore.add(Utils.chat("&fProgress to next level: " + (ClassManager.getPercNextLevel(p, RPGClasses.Healer) * 100) + "%"));
                HLore.add(Utils.chat("&f[" + ClassManager.getLevel(p, RPGClasses.Healer)
                        + "] [" + HStrPro + "&f] [" + (ClassManager.getLevel(p, RPGClasses.Healer) + 1) + "]"));
            }
        HMeta.setLore(HLore);

        ItemMeta AMeta = a.getItemMeta();
        List<String> ALore = new ArrayList<String>();
        ALore.add("");
        if (ClassManager.ArcherXP.get(p.getUniqueId().toString()) == null || ClassManager.ArcherXP.get(p.getUniqueId().toString()) == 0) {
                ALore.add(Utils.chat("&fYou have not played"));
                ALore.add(Utils.chat("&fthis class yet!"));
            } else if (ClassManager.getLevel(p, RPGClasses.Archer) == 5 || ClassManager.getLevel(p, RPGClasses.Archer) == 6) {
                ALore.add(Utils.chat("&fClass level MAXED"));
            } else {
                StringBuilder AStrPro = new StringBuilder();
                for (int i = 0; i < Math.floor(ClassManager.getPercNextLevel(p, RPGClasses.Archer) * 14); i++) {
                    AStrPro.append("&f=");
                }
                for (int i = 0; i < Math.floor(14 - ClassManager.getPercNextLevel(p, RPGClasses.Archer) * 14); i++) {
                    AStrPro.append("&8=");
                }
                ALore.add(Utils.chat("&fProgress to next level: " + (ClassManager.getPercNextLevel(p, RPGClasses.Archer) * 100) + "%"));
                ALore.add(Utils.chat("&f[" + ClassManager.getLevel(p, RPGClasses.Archer)
                        + "] [" + AStrPro + "&f] [" + (ClassManager.getLevel(p, RPGClasses.Archer) + 1) + "]"));
            }
        AMeta.setLore(ALore);

        ItemMeta KMeta = k.getItemMeta();
        List<String> KLore = new ArrayList<String>();
        KLore.add("");
        if (ClassManager.KnightXP.get(p.getUniqueId().toString()) == null || ClassManager.KnightXP.get(p.getUniqueId().toString()) == 0) {
                KLore.add(Utils.chat("&fYou have not played"));
                KLore.add(Utils.chat("&fthis class yet!"));
            } else if (ClassManager.getLevel(p, RPGClasses.Knight) == 5 || ClassManager.getLevel(p, RPGClasses.Knight) == 6) {
                KLore.add(Utils.chat("&fClass level MAXED"));
            } else {
                StringBuilder KStrPro = new StringBuilder();
                for (int i = 0; i < Math.floor(ClassManager.getPercNextLevel(p, RPGClasses.Knight) * 14); i++) {
                    KStrPro.append("&f=");
                }
                for (int i = 0; i < Math.floor(14 - ClassManager.getPercNextLevel(p, RPGClasses.Knight) * 14); i++) {
                    KStrPro.append("&8=");
                }
                KLore.add(Utils.chat("&fProgress to next level: " + (ClassManager.getPercNextLevel(p, RPGClasses.Knight) * 100) + "%"));
                KLore.add(Utils.chat("&f[" + ClassManager.getLevel(p, RPGClasses.Knight)
                        + "] [" + KStrPro + "&f] [" + (ClassManager.getLevel(p, RPGClasses.Knight) + 1) + "]"));
            }
        KMeta.setLore(KLore);

        String c = "";
        if (Players.containsKey(p.getUniqueId().toString())) {
            c = Players.get(p.getUniqueId().toString());
        }
        switch (c) {
            case "Knight":
                KMeta.setDisplayName(Utils.chat("&aKnight (Selected)"));
                KMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                k.setType(Material.DIAMOND_CHESTPLATE);
                HMeta.setDisplayName(Utils.chat("&cHealer"));
                HMeta.getEnchants().forEach((enchantment, integer) -> { HMeta.removeEnchant(enchantment); });
                h.setType(Material.APPLE);
                AMeta.setDisplayName(Utils.chat("&cArcher"));
                AMeta.getEnchants().forEach((enchantment, integer) -> { AMeta.removeEnchant(enchantment); });
                a.setType(Material.ARROW);
                k.setItemMeta(KMeta);
                h.setItemMeta(HMeta);
                a.setItemMeta(AMeta);
                break;
            case "Archer":
                KMeta.setDisplayName(Utils.chat("&cKnight"));
                KMeta.getEnchants().forEach((enchantment, integer) -> { KMeta.removeEnchant(enchantment); });
                k.setType(Material.IRON_CHESTPLATE);
                HMeta.setDisplayName(Utils.chat("&cHealer"));
                HMeta.getEnchants().forEach((enchantment, integer) -> { HMeta.removeEnchant(enchantment); });
                h.setType(Material.APPLE);
                AMeta.setDisplayName(Utils.chat("&aArcher (Selected)"));
                AMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                a.setType(Material.BOW);
                k.setItemMeta(KMeta);
                h.setItemMeta(HMeta);
                a.setItemMeta(AMeta);
                break;
            case "Healer":
                KMeta.setDisplayName(Utils.chat("&cKnight"));
                KMeta.getEnchants().forEach((enchantment, integer) -> { KMeta.removeEnchant(enchantment); });
                k.setType(Material.IRON_CHESTPLATE);
                HMeta.setDisplayName(Utils.chat("&aHealer (Selected)"));
                HMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                h.setType(Material.GOLDEN_APPLE);
                AMeta.setDisplayName(Utils.chat("&cArcher"));
                AMeta.getEnchants().forEach((enchantment, integer) -> { AMeta.removeEnchant(enchantment); });
                a.setType(Material.ARROW);
                k.setItemMeta(KMeta);
                h.setItemMeta(HMeta);
                a.setItemMeta(AMeta);
                break;
            default:
                KMeta.setDisplayName(Utils.chat("&cKnight"));
                KMeta.getEnchants().forEach((enchantment, integer) -> { KMeta.removeEnchant(enchantment); });
                k.setType(Material.IRON_CHESTPLATE);
                HMeta.setDisplayName(Utils.chat("&cHealer"));
                HMeta.getEnchants().forEach((enchantment, integer) -> { HMeta.removeEnchant(enchantment); });
                h.setType(Material.APPLE);
                AMeta.setDisplayName(Utils.chat("&cArcher"));
                AMeta.getEnchants().forEach((enchantment, integer) -> { AMeta.removeEnchant(enchantment); });
                a.setType(Material.ARROW);
                k.setItemMeta(KMeta);
                h.setItemMeta(HMeta);
                a.setItemMeta(AMeta);
        }
        inv.setItem(11, k);
        inv.setItem(15, a);
        inv.setItem(13, h);
    }

    public static void addXP(Player player, Integer Amount, String Class) {
        if (Class.equals(RPGClasses.Knight)) {
            if (KnightXP.get(player.getUniqueId().toString()) != null) {
                KnightXP.put(player.getUniqueId().toString(), KnightXP.get(player.getUniqueId().toString()) + Amount);
            } else {
                KnightXP.put(player.getUniqueId().toString(), Amount);
            }
        }
        if (Class.equals(RPGClasses.Archer)) {
            if (ArcherXP.get(player.getUniqueId().toString()) != null) {
                ArcherXP.put(player.getUniqueId().toString(), ArcherXP.get(player.getUniqueId().toString()) + Amount);
            } else {
                ArcherXP.put(player.getUniqueId().toString(), Amount);
            }
        }
        if (Class.equals(RPGClasses.Healer)) {
            if (HealerXP.get(player.getUniqueId().toString()) != null) {
                HealerXP.put(player.getUniqueId().toString(), HealerXP.get(player.getUniqueId().toString()) + Amount);
            } else {
                HealerXP.put(player.getUniqueId().toString(), Amount);
            }
        }
    }

    public static Integer getLevel(Player player, String Class) {
        if (Class.equals(RPGClasses.Knight)) {
            if (KnightXP.containsKey(player.getUniqueId().toString())
                    && KnightXP.get(player.getUniqueId().toString()) > 0) {
                for (Map.Entry<Integer, Integer> entry : KnightXPL.entrySet()) {
                    if (KnightXP.get(player.getUniqueId().toString()) >= entry.getValue()
                            && KnightXP.get(player.getUniqueId().toString()) <= KnightXPL.get(entry.getKey() + 1)) {
                        return entry.getKey();
                    }
                }
            } else {
                return 0;
            }
        } else if (Class.equals(RPGClasses.Archer)) {
            if (ArcherXP.containsKey(player.getUniqueId().toString())
                    && ArcherXP.get(player.getUniqueId().toString()) > 0) {
                for (Map.Entry<Integer, Integer> entry : ArcherXPL.entrySet()) {
                    if (ArcherXP.get(player.getUniqueId().toString()) >= entry.getValue()
                            && ArcherXP.get(player.getUniqueId().toString()) <= ArcherXPL.get(entry.getKey() + 1)) {
                        return entry.getKey();
                    }
                }
            } else {
                return 0;
            }
        } else if (Class.equals(RPGClasses.Healer)) {
            if (HealerXP.containsKey(player.getUniqueId().toString())
                    && HealerXP.get(player.getUniqueId().toString()) > 0) {
                for (Map.Entry<Integer, Integer> entry : HealerXPL.entrySet()) {
                    if (HealerXP.get(player.getUniqueId().toString()) >= entry.getValue()
                            && HealerXP.get(player.getUniqueId().toString()) <= HealerXPL.get(entry.getKey() + 1)) {
                        return entry.getKey();
                    }
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public static double getPercNextLevel(Player player, String Class) {
        if (Class.equals(RPGClasses.Knight)) {
            if (KnightXP.containsKey(player.getUniqueId().toString())
                    && KnightXP.get(player.getUniqueId().toString()) > 0) {
                if (getLevel(player, Class) == 5 || getLevel(player, Class) == 6) {
                    return 1.0;
                } else {
                    return Math.floor((KnightXP.get(player.getUniqueId().toString()) - KnightXPL.get(getLevel(player, Class)) + 0.0)
                            / (KnightXPL.get(getLevel(player, Class) + 1) - KnightXPL.get(getLevel(player, Class)) + 0.0)
                             * 1000) / 1000;
                }
            } else return 0.0;
        } if (Class.equals(RPGClasses.Archer)) {
            if (ArcherXP.containsKey(player.getUniqueId().toString())
                    && ArcherXP.get(player.getUniqueId().toString()) > 0) {
                if (getLevel(player, Class) == 5 || getLevel(player, Class) == 6) {
                    return 1.0;
                } else {
                    return Math.floor((ArcherXP.get(player.getUniqueId().toString()) - ArcherXPL.get(getLevel(player, Class)) + 0.0)
                            / (ArcherXPL.get(getLevel(player, Class) + 1) - ArcherXPL.get(getLevel(player, Class)) + 0.0)
                            * 100) / 100;
                }
            } else return 0.0;
        } if (Class.equals(RPGClasses.Healer)) {
            if (HealerXP.containsKey(player.getUniqueId().toString())
                    && HealerXP.get(player.getUniqueId().toString()) > 0) {
                if (getLevel(player, Class) == 5 || getLevel(player, Class) == 6) {
                    return 1.0;
                } else {
                    return Math.floor((HealerXP.get(player.getUniqueId().toString()) - HealerXPL.get(getLevel(player, Class)) + 0.0)
                            / (HealerXPL.get(getLevel(player, Class) + 1) - HealerXPL.get(getLevel(player, Class)) + 0.0)
                            * 100) / 100;
                }
            } else return 0.0;
        }
        return 0.0;
    }
}
