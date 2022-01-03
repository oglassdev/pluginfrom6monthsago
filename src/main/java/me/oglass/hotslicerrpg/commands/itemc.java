package me.oglass.hotslicerrpg.commands;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.enums.EnchantType;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.items.ItemEditor;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.listeners.PlayerChatListener;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class itemc implements CommandExecutor {

    private Main plugin;

    public itemc(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("itemc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("slicer.admin")) {
            if (args[0].equals("create")) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
                for (String s : list) {
                    sender.sendMessage(s);
                }
                list.remove(0);
                if (Material.getMaterial(list.get(0)) == null) {
                    sender.sendMessage(Utils.chat("&cUnknown Material: " + list.get(0)));
                    return false;
                }
                ItemStack itemStack = new ItemStack(Material.getMaterial(list.get(0)), 1);
                Boolean generateLore = false;
                NBTItem nbti = new NBTItem(itemStack);
                String give = null;
                for (String s : list) {
                    ArrayList<String> stuff = new ArrayList<>(Arrays.asList(s.split(":")));
                    // Stats
                    if (stuff.get(0).equalsIgnoreCase("-h")) {
                        if (PlayerChatListener.isInteger(stuff.get(1))) {
                            ItemEditor.addPlayerStat(nbti, PlayerStat.Health, Integer.parseInt(stuff.get(1)));
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-d")) {
                        if (PlayerChatListener.isInteger(stuff.get(1))) {
                            ItemEditor.addPlayerStat(nbti, PlayerStat.Defense, Integer.parseInt(stuff.get(1)));
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-s")) {
                        if (PlayerChatListener.isInteger(stuff.get(1))) {
                            ItemEditor.addPlayerStat(nbti, PlayerStat.Strength, Integer.parseInt(stuff.get(1)));
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-m")) {
                        if (PlayerChatListener.isInteger(stuff.get(1))) {
                            ItemEditor.addPlayerStat(nbti, PlayerStat.MagicDamage, Integer.parseInt(stuff.get(1)));
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-e")) {
                        if (PlayerChatListener.isInteger(stuff.get(1))) {
                            ItemEditor.addPlayerStat(nbti, PlayerStat.Energy, Integer.parseInt(stuff.get(1)));
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    // Random Stuff
                    else if (stuff.get(0).equalsIgnoreCase("-enchant")) {
                        if (PlayerChatListener.isInteger(stuff.get(2))) {
                            if (EnchantType.getEnchant(stuff.get(1)) != null) ItemEditor.addEnchant(nbti, EnchantType.getEnchant(stuff.get(1)), Integer.parseInt(stuff.get(2)));
                            else {
                                sender.sendMessage(Utils.chat("&cInvalid enchant! Valid enchants:"));
                                for (EnchantType type : EnchantType.values()) {
                                    sender.sendMessage(Utils.chat("&c" + type.getName()));
                                }
                            }
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be an integer!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-gen")) {
                        if (stuff.get(1).equalsIgnoreCase("true") ||
                                stuff.get(1).equalsIgnoreCase("false")) {
                            generateLore = true;
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be a boolean!"));
                            return false;
                        }
                    }
                    else if (stuff.get(0).equalsIgnoreCase("-give")) {
                        if (stuff.get(1).equalsIgnoreCase("@p") ||
                                stuff.get(1).equalsIgnoreCase("@a")) {
                            give = stuff.get(1).toLowerCase();
                        } else {
                            sender.sendMessage(Utils.chat("&cAn error occured: The value of " + s + " must be a boolean!"));
                            return false;
                        }
                    }
                }
                itemStack = nbti.getItem();
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (generateLore) itemMeta.setLore(ItemManager.generateLore(itemStack));
                itemStack.setItemMeta(itemMeta);

                Location newloc = null;
                double closest = Double.MAX_VALUE;
                Player closestp = null;
                if (sender instanceof BlockCommandSender) {
                    newloc = ((BlockCommandSender) sender).getBlock().getLocation();
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        double dist = pl.getLocation().distance(newloc);
                        if (closest == Double.MAX_VALUE || dist < closest) {
                            closest = dist;
                            closestp = pl;
                        }
                    }
                }
                else if (sender instanceof Player) {
                    Player p = (Player) sender;
                    newloc = p.getLocation();
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        double dist = pl.getLocation().distance(newloc);
                        if (closest == Double.MAX_VALUE || dist < closest) {
                            closest = dist;
                            closestp = pl;
                        }
                    }
                }

                if (give != null) {
                    if (give.equals("@p")) {
                        if (closestp != null) closestp.getInventory().addItem(itemStack);
                        else sender.sendMessage(Utils.chat("&cNo players found"));
                    } else if (give.equals("@a")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.getInventory().addItem(itemStack);
                        }
                    }
                }
                else if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(itemStack);
                } else {
                    sender.sendMessage(Utils.chat("&cYou did not provide a -give statement!"));
                }
            }
            else if (args[0].equals("getjson")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    NBTItem nbti = new NBTItem(p.getItemInHand());
                    p.sendMessage(nbti.toString());
                }
            }
            return true;
        }
        else sender.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        return false;
    }
}
