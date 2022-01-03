package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.ItemAbilities;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.BlockBox;
import me.oglass.hotslicerrpg.utils.Utils;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class RightClickListener implements Listener {

    private Main plugin;

    public RightClickListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR)
                    || ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
                    && (event.getClickedBlock().getType() != Material.CHEST)
                    && (event.getClickedBlock().getType() != Material.ENDER_CHEST)
                    && (event.getClickedBlock().getType() != Material.HOPPER)
                    && (event.getClickedBlock().getType() != Material.DROPPER)
                    && (event.getClickedBlock().getType() != Material.DISPENSER)
                    && (event.getClickedBlock().getType() != Material.TRAPPED_CHEST))) {
                // Custom Items
                NBTItem nbti = new NBTItem(event.getItem());

//          [DEBUG] p.sendMessage("" + nbti.getKeys() + " owo   " + nbti.getCompoundList("CUSTOM_TAGS"));
                if (nbti.getString("CUSTOM_ID") != null) {
                    // Boom Boom Sword lol
                    if (nbti.getString("CUSTOM_ID").equals("BOOM_BOOM_SWORD")) {
                        ItemAbilities.BoomBoomSword(event);
                    }
                    // Wither Sword
                    if (nbti.getString("CUSTOM_ID").equals("WITHER_SWORD")) {
                        ItemAbilities.WitherSword(event);
                    }
                    // Water Gun
                    if (nbti.getString("CUSTOM_ID").equals("WATER_GUN")) {
                        ItemAbilities.WaterGun(event);
                    }
                    // Menu
                    if (nbti.getString("CUSTOM_ID").equals("RPG_MENU")) {
                        p.openInventory(MenuManager.slicermenu.get(p.getUniqueId()));
                    }
                    // Boomerang
                    if (nbti.getString("CUSTOM_ID").equals("BOOMERANG")) {
                        ItemAbilities.Boomerang(event.getPlayer());
                        event.setCancelled(true);
                    }

                    if (nbti.getString("CUSTOM_ID").equals("BOOMERANG_USED")
                            && System.currentTimeMillis() - 1600 >= ItemAbilities.BoomerangUses.get(nbti.getString("UUID"))) {
                        ItemAbilities.Boomerang(event.getPlayer());
                        event.setCancelled(true);
                    }

                    if (nbti.getString("CUSTOM_ID").equals("MOLTEN_FURY")) {
                        ItemAbilities.MoltenFury(event);
                        event.setCancelled(true);
                    }
                    if (nbti.getString("CUSTOM_ID").equals("ADMIN_MINES_WAND")) {
                        if (p.hasPermission("slicer.admin")) {
                            event.setCancelled(true);
                            BlockBox.PlayerLoc2.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                            p.sendMessage(Utils.chat("&aYou have set location 2 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ() + ", "));
                            if (BlockBox.PlayerLoc1.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc1.get(p.getUniqueId()) != null) {
                                BlockBox.Blocks(BlockBox.PlayerLoc1.get(p.getUniqueId()), BlockBox.PlayerLoc2.get(p.getUniqueId()), p, "MiningBox");
                            }
                        } else {
                            event.setCancelled(true);
                            p.sendMessage(Utils.chat("&cYou may not use this item!"));
                            return;
                        }
                    }
                    if (nbti.getString("CUSTOM_ID").equals("ADMIN_GLASS_CONVERSION")) {
                        if (p.hasPermission("slicer.admin")) {
                            event.setCancelled(true);
                            BlockBox.PlayerLoc4.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                            p.sendMessage(Utils.chat("&aYou have set location 2 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ()));
                            if (BlockBox.PlayerLoc3.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc3.get(p.getUniqueId()) != null) {
                                BlockBox.GlassConversion(BlockBox.PlayerLoc3.get(p.getUniqueId()), BlockBox.PlayerLoc4.get(p.getUniqueId()));
                            }
                            p.sendMessage(Utils.chat("&aReplaced all stained glass in this area with crystal!"));
                        } else {
                            event.setCancelled(true);
                            p.sendMessage(Utils.chat("&cYou may not use this item!"));
                            return;
                        }
                    }
                    if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && nbti.getBoolean("UNPLACEABLE")) {
                        event.setCancelled(true);
                    }
                }
                // Non Custom Items
                else {
                    return;
                }
            }
            // left click
            else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                NBTItem nbti = new NBTItem(event.getItem());
                if (nbti.getBoolean("UNPLACEABLE")) {
                    event.setCancelled(true);
                }
                if (nbti.getString("CUSTOM_ID").equals("ADMIN_MINES_WAND")) {
                    if (p.hasPermission("slicer.admin")) {
                        event.setCancelled(true);
                        BlockBox.PlayerLoc2.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                        p.sendMessage(Utils.chat("&aYou have set location 2 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ() + ", "));
                        if (BlockBox.PlayerLoc1.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc1.get(p.getUniqueId()) != null) {
                            BlockBox.Blocks(BlockBox.PlayerLoc1.get(p.getUniqueId()), BlockBox.PlayerLoc2.get(p.getUniqueId()), p, "MiningBox");
                        }
                    } else {
                        event.setCancelled(true);
                        p.sendMessage(Utils.chat("&cYou may not use this item!"));
                        return;
                    }
                }
                if (nbti.getString("CUSTOM_ID").equals("ADMIN_GLASS_CONVERSION")) {
                    if (p.hasPermission("slicer.admin")) {
                        event.setCancelled(true);
                        BlockBox.PlayerLoc4.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                        p.sendMessage(Utils.chat("&aYou have set location 2 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ() + ", "));
                        if (BlockBox.PlayerLoc3.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc3.get(p.getUniqueId()) != null) {
                            BlockBox.GlassConversion(BlockBox.PlayerLoc3.get(p.getUniqueId()), BlockBox.PlayerLoc4.get(p.getUniqueId()));
                        }
                        p.sendMessage(Utils.chat("&aReplaced all stained glass in this area with crystal!"));
                    } else {
                        event.setCancelled(true);
                        p.sendMessage(Utils.chat("&cYou may not use this item!"));
                        return;
                    }
                }
            }
            if ((event.getAction() == Action.LEFT_CLICK_AIR
                    || event.getAction() == Action.LEFT_CLICK_BLOCK)
                    && event.getItem() != null) {
                // Custom Items
                NBTItem nbti = new NBTItem(event.getItem());

//          [DEBUG] p.sendMessage("" + nbti.getKeys() + " owo   " + nbti.getCompoundList("CUSTOM_TAGS"));
                if (nbti.getString("CUSTOM_ID") != null) {
                    /*if (event.getItem().getType().equals(Material.DIAMOND_PICKAXE) && event.getAction() != Action.LEFT_CLICK_AIR) {
                        event.setCancelled(true);
                        BreakAnimation.sendBreakAnimation(event.getClickedBlock().getLocation(), 100);
                    }*/
                    if (nbti.getString("CUSTOM_ID").equals("MOLTEN_FURY")) {
                        ItemAbilities.MoltenFury(event);
                        event.setCancelled(true);
                    } else if (nbti.getString("CUSTOM_ID").equals("WATER_GUN")) {
                        ItemAbilities.WaterGun(event);
                        event.setCancelled(true);
                    }
                    if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        if (nbti.getString("CUSTOM_ID").equals("ADMIN_MINES_WAND")) {
                            if (p.hasPermission("slicer.admin")) {
                                event.setCancelled(true);
                                BlockBox.PlayerLoc1.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                                p.sendMessage(Utils.chat("&aYou have set location 1 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ() + ", "));
                                if (BlockBox.PlayerLoc2.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc2.get(p.getUniqueId()) != null) {
                                    BlockBox.Blocks(BlockBox.PlayerLoc1.get(p.getUniqueId()), BlockBox.PlayerLoc2.get(p.getUniqueId()), p, "MiningBox");
                                }
                            } else {
                                event.setCancelled(true);
                                p.sendMessage(Utils.chat("&cYou may not use this item!"));
                                return;
                            }
                        }
                    }
                    if (nbti.getString("CUSTOM_ID").equals("ADMIN_GLASS_CONVERSION")) {
                        if (p.hasPermission("slicer.admin")) {
                            event.setCancelled(true);
                            BlockBox.PlayerLoc3.put(p.getUniqueId(), event.getClickedBlock().getLocation());
                            p.sendMessage(Utils.chat("&aYou have set location 1 to " + event.getClickedBlock().getLocation().getBlockX() + ", " + event.getClickedBlock().getLocation().getBlockY() + ", " + event.getClickedBlock().getLocation().getBlockZ() + ", "));
                            if (BlockBox.PlayerLoc4.containsKey(p.getUniqueId()) && BlockBox.PlayerLoc4.get(p.getUniqueId()) != null) {
                                BlockBox.GlassConversion(BlockBox.PlayerLoc3.get(p.getUniqueId()), BlockBox.PlayerLoc4.get(p.getUniqueId()));
                            }
                            p.sendMessage(Utils.chat("&aReplaced all stained glass in this area with crystal!"));
                        } else {
                            event.setCancelled(true);
                            p.sendMessage(Utils.chat("&cYou may not use this item!"));
                            return;
                        }
                    }

                }
                // Non Custom Items
                else {
                    if (event.getItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                        event.setCancelled(true);
                        CraftPlayer player = ((CraftPlayer) p);
                        Integer r1 = new Random().nextInt(20000);
                        PacketPlayOutBlockBreakAnimation l1 = new PacketPlayOutBlockBreakAnimation(r1, new BlockPosition(event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ()), 5);
                        player.getHandle().playerConnection.sendPacket(l1);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                            player.getHandle().playerConnection.sendPacket(new PacketPlayOutRemoveEntityEffect(r1, null));
                        }, 10);
                    }
                    return;
                }
            }
        }
    }
}
