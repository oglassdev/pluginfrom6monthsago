package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.items.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class ItemMoveListener implements Listener {

    private Main plugin;

    public ItemMoveListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(InventoryMoveItemEvent event) {
        Bukkit.getLogger().log(Level.INFO, "Item moved by " + event.getHandlers());
        if (event.getHandlers() instanceof Player) {
            Player p = (Player) event.getHandlers();
            ItemStack i = event.getItem();
            Inventory inv = event.getDestination();

            if (inv.equals(MenuManager.craftingMenu.get(p.getUniqueId()))) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Integer[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
                        Inventory inv = event.getDestination();

                        NBTItem nbt0 = null;
                        if (inv.getItem(slots[0]) != null) {
                            nbt0 = new NBTItem(inv.getItem(slots[0]));
                        }
                        NBTItem nbt1 = null;
                        if (inv.getItem(slots[1]) != null) {
                            nbt1 = new NBTItem(inv.getItem(slots[1]));
                        }
                        NBTItem nbt2 = null;
                        if (inv.getItem(slots[2]) != null) {
                            nbt2 = new NBTItem(inv.getItem(slots[2]));
                        }
                        NBTItem nbt3 = null;
                        if (inv.getItem(slots[3]) != null) {
                            nbt3 = new NBTItem(inv.getItem(slots[3]));
                        }
                        NBTItem nbt4 = null;
                        if (inv.getItem(slots[4]) != null) {
                            nbt4 = new NBTItem(inv.getItem(slots[4]));
                        }
                        NBTItem nbt5 = null;
                        if (inv.getItem(slots[5]) != null) {
                            nbt5 = new NBTItem(inv.getItem(slots[5]));
                        }
                        NBTItem nbt6 = null;
                        if (inv.getItem(slots[6]) != null) {
                            nbt6 = new NBTItem(inv.getItem(slots[6]));
                        }
                        NBTItem nbt7 = null;
                        if (inv.getItem(slots[7]) != null) {
                            nbt7 = new NBTItem(inv.getItem(slots[7]));
                        }
                        NBTItem nbt8 = null;
                        if (inv.getItem(slots[8]) != null) {
                            nbt8 = new NBTItem(inv.getItem(slots[8]));
                        }

                        // Full Table Recipes
                        NBTItem fnbt0 = nbt0;
                        NBTItem fnbt1 = nbt1;
                        NBTItem fnbt2 = nbt2;
                        NBTItem fnbt3 = nbt3;
                        NBTItem fnbt4 = nbt4;
                        NBTItem fnbt5 = nbt5;
                        NBTItem fnbt6 = nbt6;
                        NBTItem fnbt7 = nbt7;
                        NBTItem fnbt8 = nbt8;
                        if (inv.getItem(slots[0]) != null && inv.getItem(slots[1]) != null && inv.getItem(slots[2]) != null
                                && inv.getItem(slots[3]) != null && inv.getItem(slots[4]) != null && inv.getItem(slots[5]) != null
                                && inv.getItem(slots[6]) != null && inv.getItem(slots[7]) != null && inv.getItem(slots[8]) != null) {
                            // Boomerang
                            if (inv.getItem(slots[0]).getType().equals(Material.STRING)
                                    && fnbt1.getString("CUSTOM_ID").equals("BONE_FRAGMENT")
                                    && inv.getItem(slots[2]).getType().equals(Material.STRING)

                                    && inv.getItem(slots[3]).getType().equals(Material.STRING)
                                    && fnbt4.getString("CUSTOM_ID").equals("BONE_FRAGMENT")
                                    && inv.getItem(slots[5]).getType().equals(Material.STRING)

                                    && inv.getItem(slots[6]).getType().equals(Material.STRING)
                                    && fnbt7.getString("CUSTOM_ID").equals("BONE_FRAGMENT")
                                    && inv.getItem(slots[8]).getType().equals(Material.STRING)
                            ) {
                                inv.setItem(24, ItemManager.createUUID(ItemManager.Boomerang));
                            }
                        } else inv.setItem(24, null);
                    }
                }, 1L);
            }
        }
    }
}
