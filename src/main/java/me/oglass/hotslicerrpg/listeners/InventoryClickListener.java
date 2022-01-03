package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.classes.RPGClasses;
import me.oglass.hotslicerrpg.items.Admin;
import me.oglass.hotslicerrpg.items.CustomCraftingRecipes;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.playerstats.Debug;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryClickListener implements Listener {

    private final Main plugin;
    private Integer TaskID;

    public InventoryClickListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != null) {
            List<ItemStack> items = new ArrayList<>();
            items.add(event.getCurrentItem());
            items.add(event.getCursor());
            items.add((event.getClick() == org.bukkit.event.inventory.ClickType.NUMBER_KEY) ? event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) : event.getCurrentItem());
            for(ItemStack item : items) {
                if (item != null && item.hasItemMeta()) {
                    NBTItem nbti = new NBTItem(item);
                    if (nbti.getString("CUSTOM_ID").equals("RPG_MENU")) {
                        event.setCancelled(true);
                        p.openInventory(MenuManager.slicermenu.get(p.getUniqueId()));
                        return;
                    }
                }
            }
            if (!event.getCurrentItem().getType().equals(Material.AIR) && !(event instanceof InventoryCreativeEvent)) {
                if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
                    return;
                }
                NBTItem nbti = new NBTItem(event.getCurrentItem());
                switch (nbti.getString("CUSTOM_ID")) {
                    case "FWARP":
                        event.setCancelled(true);
                        World world;
                        if (Bukkit.getWorld(nbti.getString("WORLD")) != null) world = Bukkit.getWorld(nbti.getString("WORLD"));
                        else world = Bukkit.getWorlds().get(0);

                        Location loc = new Location(world, nbti.getDouble("X"), nbti.getDouble("Y"), nbti.getDouble("Z"));
                        p.teleport(loc);
                        p.closeInventory();
                        break;

                    case "CLASS_KNIGHT":
                        ClassManager.Players.put(p.getUniqueId().toString(), RPGClasses.Knight);
                        ClassManager.updateGUI(p);
                        p.openInventory(MenuManager.classMenu.get(p.getUniqueId()));
                        event.setCancelled(true);
                        break;


                    case "CLASS_HEALER":
                        ClassManager.Players.put(p.getUniqueId().toString(), RPGClasses.Healer);
                        ClassManager.updateGUI(p);
                        p.openInventory(MenuManager.classMenu.get(p.getUniqueId()));
                        event.setCancelled(true);
                        break;

                    case "CLASS_ARCHER":
                        ClassManager.Players.put(p.getUniqueId().toString(), RPGClasses.Archer);
                        ClassManager.updateGUI(p);
                        p.openInventory(MenuManager.classMenu.get(p.getUniqueId()));
                        event.setCancelled(true);
                        break;

                    case "CLASSES":
                        ClassManager.updateGUI(p);
                        p.openInventory(MenuManager.classMenu.get(p.getUniqueId()));
                        p.sendMessage(Utils.chat("&3WIP"));
                        event.setCancelled(true);
                        break;

                    case "MENU_CRAFTING":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.craftingMenu.get(p.getUniqueId()));
                        break;

                    case "MENU_ECHEST":
                        event.setCancelled(true);
                        p.openInventory(p.getEnderChest());
                        break;

                    case "MENU_WARPS":
                        event.setCancelled(true);
                        p.sendMessage("Warp menu WIP.");
                        p.openInventory(MenuManager.warpMenu.get(p.getUniqueId()));
                        break;

                    case "MENU_SETTINGS":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.settingsMenu.get(p.getUniqueId()));
                        break;

                    case "CLOSE_MENU":
                        event.setCancelled(true);
                        p.closeInventory();
                        break;

                    case "NOT_RELEASED":
                        event.setCancelled(true);
                        p.sendMessage(Utils.chat("&cThis feature is not released yet!"));
                        break;

                    case "MENU_GLASS":

                    case "CUSTOM_ITEM_EMPTY":
                        event.setCancelled(true);
                        break;

                    case "NIGHT_VISION_TOGGLE":
                        if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                            p.sendMessage(Utils.chat("You have toggled night vision on!"));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 1, true, true));
                        } else {
                            p.sendMessage(Utils.chat("You have toggled night vision off!"));
                            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        }
                        break;

                    case "BACK_TO_MENU":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.slicermenu.get(p.getUniqueId()));
                        break;

                    case "DEBUG":
                        event.setCancelled(true);
                        if (!Debug.get(p)) {
                            p.sendMessage(Utils.chat("&aYou have toggled debug mode on!"));
                            Debug.set(p, true);
                        } else {
                            p.sendMessage(Utils.chat("&aYou have toggled debug mode off!"));
                            Debug.set(p, false);
                        }
                        break;

                    case "ADMIN_MENU":
                    case "ADMIN_BACK":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.adminControls.get(p.getUniqueId()));
                        break;

                    case "ADMIN_ITEMS":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.givemenu.get(p.getUniqueId()));
                        break;

                    case "ADMIN_STATS":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.adminStats.get(p.getUniqueId()));
                        break;

                    case "ADMIN_HEALTH":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setPlayer(p, Admin.Health);
                        break;

                    case "ADMIN_MAXHEALTH":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setPlayer(p, Admin.MaxHealth);
                        break;

                    case "ADMIN_DEFENSE":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setPlayer(p, Admin.Defense);
                        break;

                    case "ADMIN_ENERGY":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setPlayer(p, Admin.Energy);
                        break;

                    case "ADMIN_MAXENERGY":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setPlayer(p, Admin.MaxEnergy);
                        break;

                    case "ADMIN_CITEMS":
                        event.setCancelled(true);
                        p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                        break;

                    case "CUSTOM_NAME":
                        event.setCancelled(true);
                        if (MenuManager.CustomItem.get(p.getUniqueId()).getItem(13).equals(ItemManager.CustomItemClick)) {
                            p.sendMessage(Utils.chat("&4You cannot change the name of this item!"));
                            break;
                        }
                        p.closeInventory();
                        Admin.setItemName(p);
                        break;

                    case "CUSTOM_LORE":
                        event.setCancelled(true);
                        if (MenuManager.CustomItem.get(p.getUniqueId()).getItem(13).equals(ItemManager.CustomItemClick)) {
                            p.sendMessage(Utils.chat("&4You cannot change the lore of this item!"));
                            break;
                        }
                        p.openInventory(MenuManager.CustomLore.get(p.getUniqueId()));
                        break;

                    case "LORELINE":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setItemLore(p);
                        break;
                    case "CUSTOM_ITEM_ID":
                        event.setCancelled(true);
                        if (MenuManager.CustomItem.get(p.getUniqueId()).getItem(13).equals(ItemManager.CustomItemClick)) {
                            p.sendMessage(Utils.chat("&4You cannot make a Custom ID for this item!"));
                            break;
                        }
                        p.closeInventory();
                        Admin.setItemCustomID(p);
                        break;

                    case "CUSTOM_NBT_TAGS":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setItemNBTTags(p);
                        break;
                    case "CUSTOM_NBT":
                        event.setCancelled(true);
                        if (MenuManager.CustomItem.get(p.getUniqueId()).getItem(13).equals(ItemManager.CustomItemClick)) {
                            p.sendMessage(Utils.chat("&4You cannot have Custom NBT for this item!"));
                            break;
                        }
                        p.openInventory(MenuManager.CustomNBTC.get(p.getUniqueId()));
                        break;
                    case "CUSTOM_ENCHANTMENTS":
                        event.setCancelled(true);
                        p.closeInventory();
                        Admin.setItemEnchantments(p);
                        break;
                    case "CUSTOM_LORE_GENERATOR":
                        event.setCancelled(true);
                        ItemStack item = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                        if (item.equals(ItemManager.CustomItemClick)) {
                            p.sendMessage(Utils.chat("&4You cannot generate the lore for this item!"));
                            break;
                        }
                        ItemMeta meta = item.getItemMeta();
                        meta.setLore(ItemManager.generateLore(item));
                        item.setItemMeta(meta);
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item);
                        p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                        break;
                    case "CUSTOM_UNBREAKABLE":
                        ItemStack item1 = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                        ItemMeta meta1 = item1.getItemMeta();
                        event.setCancelled(true);
                        if (meta1.spigot().isUnbreakable()) {
                            meta1.spigot().setUnbreakable(false);
                            p.sendMessage(Utils.chat("&aYou have made the item breakable!"));
                        }
                        else {
                            meta1.spigot().setUnbreakable(true);
                            p.sendMessage(Utils.chat("&aYou have made the item unbreakable!"));
                        }
                        item1.setItemMeta(meta1);
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item1);
                        break;
                    case "CUSTOM_REPAIR":
                        ItemStack item2 = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                        item2.setDurability((short) 0);
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, item2);
                        p.openInventory(MenuManager.CustomItem.get(p.getUniqueId()));
                        event.setCancelled(true);
                        break;
                    case "CUSTOM_HIDE_VANILLA_ENCHANTS":
                        event.setCancelled(true);
                        ItemStack item3 = MenuManager.CustomItem.get(p.getUniqueId()).getItem(13);
                        NBTItem item3NBT = new NBTItem(item3);
                        if (item3NBT.getInteger("HideFlags") != null && item3NBT.getInteger("HideFlags") != 0) {
                            if (item3NBT.getInteger("HideFlags") == 2)  item3NBT.setInteger("HideFlags", 3);
                            else if (item3NBT.getInteger("HideFlags") == 6)  item3NBT.setInteger("HideFlags", 7);
                            else if (item3NBT.getInteger("HideFlags") == 4)  item3NBT.setInteger("HideFlags", 5);
                            else if (item3NBT.getInteger("HideFlags") == 1)  item3NBT.setInteger("HideFlags", 0);
                        } else item3NBT.setInteger("HideFlags", 1);
                        break;
                    case "CUSTOM_HIDE_UNBREAKING":
                        event.setCancelled(true);
                        break;
                    case "CUSTOM_HIDE_MODIFIERS":
                        event.setCancelled(true);
                        break;
                }

                // Crafting
                if (event.getClickedInventory().equals(MenuManager.craftingMenu.get(p.getUniqueId()))) {
                    Integer[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
                    if (event.getSlot() == 25) {
                        event.setCancelled(true);
                        if (event.getInventory().getItem(event.getSlot()) != null) {
                            NBTItem inbt = new NBTItem(ItemManager.createUUID(event.getInventory().getItem(25)));
                            if (inbt.getString("TABLE_RESULT").equals("yes")) {
                                event.getClickedInventory().setItem(25, ItemManager.RedGlass);
                                for (int i : slots) {
                                    if (event.getInventory().getItem(i) != null) {
                                        if (event.getClickedInventory().getItem(i).getAmount() > 1)
                                            event.getClickedInventory().getItem(i).setAmount(event.getClickedInventory().getItem(i).getAmount() - 1);
                                        else event.getClickedInventory().setItem(i, null);
                                    }
                                }
                                p.openInventory(MenuManager.craftingMenu.get(p.getUniqueId()));
                                p.getInventory().addItem(p.getItemOnCursor());
                                p.setItemOnCursor(null);
                                inbt.removeKey("TABLE_RESULT");
                                if (event.isShiftClick()) p.getInventory().addItem(inbt.getItem());
                                else p.setItemOnCursor(inbt.getItem());
                            }
                        }
                    }
                    if (nbti.getString("CUSTOM_ID").equals("MENU_GLASS")) {
                        event.setCancelled(true);
                    } else /* if (nbti.getString("CUSTOM_ID").equals("CRAFT_GO")) */ {
                        if (nbti.getString("CUSTOM_ID").equals("CRAFT_GO")) event.setCancelled(true);
                        CustomCraftingRecipes.checkRecipe(event.getInventory(), p);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                Integer[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
                                Inventory inv = MenuManager.craftingMenu.get(p.getUniqueId());

                                NBTItem nbt0 = null;
                                if (inv.getItem(slots[0]) != null) {
                                    nbt0 = new NBTItem(event.getClickedInventory().getItem(slots[0]));
                                }
                                NBTItem nbt1 = null;
                                if (inv.getItem(slots[1]) != null) {
                                    nbt1 = new NBTItem(event.getClickedInventory().getItem(slots[1]));
                                }
                                NBTItem nbt2 = null;
                                if (inv.getItem(slots[2]) != null) {
                                    nbt2 = new NBTItem(event.getClickedInventory().getItem(slots[2]));
                                }
                                NBTItem nbt3 = null;
                                if (inv.getItem(slots[3]) != null) {
                                    nbt3 = new NBTItem(event.getClickedInventory().getItem(slots[3]));
                                }
                                NBTItem nbt4 = null;
                                if (inv.getItem(slots[4]) != null) {
                                    nbt4 = new NBTItem(event.getClickedInventory().getItem(slots[4]));
                                }
                                NBTItem nbt5 = null;
                                if (inv.getItem(slots[5]) != null) {
                                    nbt5 = new NBTItem(event.getClickedInventory().getItem(slots[5]));
                                }
                                NBTItem nbt6 = null;
                                if (inv.getItem(slots[6]) != null) {
                                    nbt6 = new NBTItem(event.getClickedInventory().getItem(slots[6]));
                                }
                                NBTItem nbt7 = null;
                                if (inv.getItem(slots[7]) != null) {
                                    nbt7 = new NBTItem(event.getClickedInventory().getItem(slots[7]));
                                }
                                NBTItem nbt8 = null;
                                if (inv.getItem(slots[8]) != null) {
                                    nbt8 = new NBTItem(event.getClickedInventory().getItem(slots[8]));
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
                                        NBTItem i = new NBTItem(ItemManager.Boomerang);
                                        i.setString("TABLE_RESULT", "yes");
                                        ItemStack item = i.getItem();
                                        inv.setItem(25, item);
                                    } else inv.setItem(25, ItemManager.RedGlass);
                                } else if (inv.getItem(slots[0]) != null && inv.getItem(slots[1]) != null && inv.getItem(slots[2]) == null
                                        && inv.getItem(slots[3]) != null && inv.getItem(slots[4]) == null && inv.getItem(slots[5]) != null
                                        && inv.getItem(slots[6]) != null && inv.getItem(slots[7]) != null && inv.getItem(slots[8]) == null) {
                                    if (inv.getItem(slots[0]).getType().equals(Material.STRING)
                                            && fnbt1.getString("CUSTOM_ID").equals("MOLTEN_REMNANT")

                                            && inv.getItem(slots[3]).getType().equals(Material.STRING)
                                            && fnbt5.getString("CUSTOM_ID").equals("MOLTEN_REMNANT")

                                            && inv.getItem(slots[6]).getType().equals(Material.STRING)
                                            && fnbt7.getString("CUSTOM_ID").equals("MOLTEN_REMNANT")) {
                                        NBTItem i = new NBTItem(ItemManager.MoltenFury);
                                        i.setString("TABLE_RESULT", "yes");
                                        ItemStack item = i.getItem();
                                        inv.setItem(25, item);
                                    } else inv.setItem(25, ItemManager.RedGlass);
                                }  else inv.setItem(25, ItemManager.RedGlass);
                            }
                        }, 1L);
                    }
                }

                // Random Stuff
                else if (event.getInventory().equals(MenuManager.slicermenu.get(p.getUniqueId()))) {
                    event.setCancelled(true);
                }
                else if (event.getClickedInventory().equals(p.getInventory()) && event.getView().getTopInventory().equals(MenuManager.CustomItem.get(p.getUniqueId()))) {
                    NBTItem inbt = new NBTItem(MenuManager.CustomItem.get(p.getUniqueId()).getItem(13));
                    if (inbt.getString("CUSTOM_ID").equals("CUSTOM_ITEM_EMPTY")) {
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, event.getCurrentItem());
                        event.setCurrentItem(null);
                    } else {
                        event.setCancelled(true);
                        p.sendMessage(Utils.chat("&cYou already have an item in that slot! Please take it out before you edit another item!"));
                    }
                }
                else if (event.getClickedInventory().equals(MenuManager.CustomItem.get(p.getUniqueId())) && event.getSlot() == 13) {
                    if (!nbti.getString("CUSTOM_ID").equals("CUSTOM_ITEM_EMPTY")) {
                        p.getInventory().addItem(event.getCurrentItem());
                        event.setCancelled(true);
                        MenuManager.CustomItem.get(p.getUniqueId()).setItem(13, ItemManager.CustomItemClick);
                    }
                }
                else if (event.getClickedInventory().equals(MenuManager.givemenu.get(p.getUniqueId()))
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.MenuGlass.getItemMeta())
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.CrystalOreGold.getItemMeta())
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.CrystalOreRed.getItemMeta())
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.CrystalOreBlue.getItemMeta())
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.CrystalOreCorrupt.getItemMeta())
                        && !event.getCurrentItem().getItemMeta().equals(ItemManager.AdminBack.getItemMeta())) {
                    event.setCancelled(true);
                    ItemStack i = event.getCurrentItem();
                    if (event.isShiftClick()) {
                        p.getInventory().addItem(ItemManager.createUUID(i));
                    } else {
                        p.setItemOnCursor(ItemManager.createUUID(i));
                    }
                }
            }
        }
    }
}