package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.CustomBlockData;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.commands.MiningToggle;
import me.oglass.hotslicerrpg.CustomBlock;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.utils.BlockBox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BlockBreakListener implements Listener {

    private Main plugin;

    private static ArrayList<Location> stonelocations = new ArrayList<>();

    public BlockBreakListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (MiningToggle.toggle &&
                BlockBox.BlockHashMap.containsKey("MiningBox")
                && BlockBox.BlockHashMap.get("MiningBox").contains(event.getBlock().getLocation()) &&
        ((event.getBlock().getType().equals(Material.STONE) && event.getBlock().getData() == (byte) 0) ||
        event.getBlock().getType().equals(Material.DIAMOND_ORE) ||
        event.getBlock().getType().equals(Material.GOLD_ORE) ||
        event.getBlock().getType().equals(Material.IRON_ORE) ||
        event.getBlock().getType().equals(Material.LAPIS_ORE) ||
        event.getBlock().getType().equals(Material.REDSTONE_ORE) ||
        event.getBlock().getType().equals(Material.GLOWING_REDSTONE_ORE) ||
        event.getBlock().getType().equals(Material.EMERALD_ORE) ||
        event.getBlock().getType().equals(Material.COAL_ORE) ||
        event.getBlock().getType().equals(Material.EMERALD_BLOCK) ||
        event.getBlock().getType().equals(Material.DIAMOND_BLOCK) ||
        event.getBlock().getType().equals(Material.GOLD_BLOCK) ||
        event.getBlock().getType().equals(Material.IRON_BLOCK) ||
        event.getBlock().getType().equals(Material.LAPIS_BLOCK) ||
        event.getBlock().getType().equals(Material.REDSTONE_BLOCK) ||
        event.getBlock().getType().equals(Material.EMERALD_BLOCK) ||
        event.getBlock().getType().equals(Material.COAL_BLOCK) ||
        event.getBlock().getType().equals(Material.OBSIDIAN) ||
        event.getBlock().getType().equals(Material.COBBLESTONE) ||
                CustomBlock.getLocations().contains(event.getBlock().getLocation()))) {
            event.setCancelled(true);
            Material startingblock = event.getBlock().getType();
            Byte startingdata = event.getBlock().getData();
            ItemStack hand = p.getItemInHand();
            NBTItem handNBT = null;
            CustomBlock cblock = CustomBlock.getBlock(event.getBlock().getLocation());
            CustomBlockData cblockdata = null;
            if (cblock != null)  cblockdata = cblock.getData();
            if (hand != null && hand.getType() != Material.AIR && hand.getItemMeta() != null) handNBT = new NBTItem(hand);
            if (handNBT != null && handNBT.hasKey("ENCHANTMENTS") && handNBT.getCompound("ENCHANTMENTS").hasKey("FORTUNE")) {
                int randnum = (int) (Math.random() * (handNBT.getCompound("ENCHANTMENTS").getInteger("FORTUNE") * 2)) + handNBT.getCompound("ENCHANTMENTS").getInteger("FORTUNE");
                ItemStack item;
                if (event.getBlock().getType().equals(Material.LAPIS_ORE)) item = new ItemStack(Material.INK_SACK, randnum * 2, (byte) 4);
                else if (event.getBlock().getType().equals(Material.REDSTONE_ORE) || event.getBlock().getType().equals(Material.GLOWING_REDSTONE_ORE)) item = new ItemStack(Material.REDSTONE, randnum * 2);
                else if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) item = new ItemStack(Material.DIAMOND, randnum);
                else if (event.getBlock().getType().equals(Material.EMERALD_ORE)) item = new ItemStack(Material.EMERALD, randnum);
                else if (event.getBlock().getType().equals(Material.COAL_ORE)) item = new ItemStack(Material.COAL, randnum);
                else if (event.getBlock().getType().equals(Material.STONE)) item = new ItemStack(Material.COBBLESTONE, 1);
                else if (cblock != null && cblockdata.getString("VARIANT").equals("RED") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) {
                    item = ItemManager.CrystalRed;
                    item.setAmount((randnum / 2));
                }
                else if (cblock != null && cblockdata.getString("VARIANT").equals("BLUE") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) {
                    item = ItemManager.CrystalBlue;
                    item.setAmount((randnum / 2));
                }
                else if (cblock != null && cblockdata.getString("VARIANT").equals("CORRUPT") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) {
                    item = ItemManager.CrystalCorrupt;
                    item.setAmount((randnum / 2));
                }
                else if (cblock != null && cblockdata.getString("VARIANT").equals("GOLD") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) {
                    item = ItemManager.CrystalGold;
                    item.setAmount((randnum / 2));
                }
                else if (event.getBlock().getType().equals(Material.STONE)) item = new ItemStack(Material.COBBLESTONE, 1);
                else item = new ItemStack(event.getBlock().getType(), 1);

                if (handNBT.getCompound("ENCHANTMENTS").getInteger("AUTO_PICKUP") > 0) {
                    p.getInventory().addItem(item);
                } else {
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
                }
            } else if (handNBT != null && handNBT.hasKey("ENCHANTMENTS") && handNBT.getCompound("ENCHANTMENTS").hasKey("AUTO_PICKUP")) {
                ItemStack item;
                if (event.getBlock().getType().equals(Material.LAPIS_ORE)) item = new ItemStack(Material.INK_SACK, 2, (byte) 4);
                else if (event.getBlock().getType().equals(Material.REDSTONE_ORE) || event.getBlock().getType().equals(Material.GLOWING_REDSTONE_ORE)) item = new ItemStack(Material.REDSTONE, 2);
                else if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) item = new ItemStack(Material.DIAMOND, 1);
                else if (event.getBlock().getType().equals(Material.EMERALD_ORE)) item = new ItemStack(Material.EMERALD, 1);
                else if (event.getBlock().getType().equals(Material.COAL_ORE)) item = new ItemStack(Material.COAL, 1);
                else if (event.getBlock().getType().equals(Material.STONE)) item = new ItemStack(Material.COBBLESTONE, 1);
                else if (cblock != null && cblockdata.getString("VARIANT").equals("RED") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalRed;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("BLUE") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalBlue;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("CORRUPT") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalCorrupt;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("GOLD") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalGold;
                else item = new ItemStack(event.getBlock().getType(), 1);

                if (handNBT.getCompound("ENCHANTMENTS").getInteger("AUTO_PICKUP") > 0) {
                    p.getInventory().addItem(item);
                } else {
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
                }
            } else {
                ItemStack item;
                if (event.getBlock().getType().equals(Material.LAPIS_ORE)) item = new ItemStack(Material.INK_SACK, 2, (byte) 4);
                else if (event.getBlock().getType().equals(Material.REDSTONE_ORE) || event.getBlock().getType().equals(Material.GLOWING_REDSTONE_ORE)) item = new ItemStack(Material.REDSTONE, 2);
                else if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) item = new ItemStack(Material.DIAMOND, 1);
                else if (event.getBlock().getType().equals(Material.EMERALD_ORE)) item = new ItemStack(Material.EMERALD, 1);
                else if (event.getBlock().getType().equals(Material.COAL_ORE)) item = new ItemStack(Material.COAL, 1);
                else if (event.getBlock().getType().equals(Material.STONE)) item = new ItemStack(Material.COBBLESTONE, 1);
                else if (cblock != null && cblockdata.getString("VARIANT").equals("RED") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalRed;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("BLUE") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalBlue;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("CORRUPT") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalCorrupt;
                else if (cblock != null && cblockdata.getString("VARIANT").equals("GOLD") && cblockdata.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) item = ItemManager.CrystalGold;
                else item = new ItemStack(event.getBlock().getType(), 1);

                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
            }
            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
            if (startingblock.equals(Material.STONE)) {
                event.getBlock().setType(Material.COBBLESTONE);
                stonelocations.add(event.getBlock().getLocation());
            }

            else if (CustomBlock.getLocations().contains(event.getBlock().getLocation())) {
                event.getBlock().setType(Material.STAINED_GLASS);
                event.getBlock().setData((byte) 7);
                CustomBlock.getBlock(event.getBlock().getLocation()).removeBlock();
            }
            else event.getBlock().setType(Material.BEDROCK);
            int delay = (int) (Math.random() * 20) + 160;

            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                if (startingblock.equals(Material.COBBLESTONE) && stonelocations.contains(event.getBlock().getLocation())) {
                    event.getBlock().setType(Material.STONE);
                    stonelocations.remove(event.getBlock().getLocation());
                } else if (startingblock.equals(Material.STONE) && !stonelocations.contains(event.getBlock().getLocation())) {
                    event.getBlock().setType(Material.STONE);
                    stonelocations.remove(event.getBlock().getLocation());
                } else if (cblock != null) {
                    event.getBlock().setType(startingblock);
                    switch (cblock.getData().getString("VARIANT")) {
                        case "RED":
                            event.getBlock().setData((byte) 14);
                            break;
                        case "BLUE":
                            event.getBlock().setData((byte) 11);
                            break;
                        case "CORRUPT":
                            event.getBlock().setData((byte) 10);
                            break;
                        case "GOLD":
                            event.getBlock().setData((byte) 4);
                            break;
                        default:
                            break;
                    }
                    CustomBlock customBlock = new CustomBlock(event.getBlock());
                    customBlock.setData(cblock.getData());
                }
                else {
                    event.getBlock().setType(startingblock);
                    event.getBlock().setData(startingdata);
                }
            }, delay);
        } else if (!MiningToggle.toggle && CustomBlock.getLocations().contains(event.getBlock().getLocation())) {
            CustomBlock.getBlock(event.getBlock().getLocation()).removeBlock();
        }
    }
}
