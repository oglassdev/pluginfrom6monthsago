package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.listeners.PlayerChatListener;
import me.oglass.hotslicerrpg.playerstats.Debug;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class giveChestItems implements CommandExecutor {

    private Main plugin;
    private Inventory inv;

    public giveChestItems(Main plugin) {

        this.plugin = plugin;
        plugin.getCommand("givechestitems").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof BlockCommandSender)) {
            sender.sendMessage(Utils.chat(Utils.chat(plugin.getConfig().getString("ConsoleWarning"))));
            return true;
        }
        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;
        }

        if (p != null && p.hasPermission("slicer.admin") || sender instanceof BlockCommandSender) {
            if (sender instanceof BlockCommandSender && args.length < 4) {
                sender.sendMessage(Utils.chat("&cInvalid Syntax"));
            }
            else if (sender instanceof Player && args.length == 3) {
                if (PlayerChatListener.isInteger(args[0]) && PlayerChatListener.isInteger(args[1]) && PlayerChatListener.isInteger(args[2])
                && p.getWorld().getBlockAt(new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]))).getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) p.getWorld().getBlockAt(new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                    for (ItemStack i : chest.getBlockInventory()) {
                        p.getInventory().addItem(i);
                    }
                    sender.sendMessage("&aYou have been given all items in the chest!");
                } else sender.sendMessage(Utils.chat("&cInvalid syntax or block is not a chest!"));
            }
            else if (args.length == 4) {
                if (Bukkit.getPlayer(args[3]) != null
                        || args[3].equalsIgnoreCase("@a")
                        || args[3].equalsIgnoreCase("@p")) {
                    if (PlayerChatListener.isInteger(args[0]) && PlayerChatListener.isInteger(args[1]) && PlayerChatListener.isInteger(args[2])) {
                        if ((sender instanceof Player && ((Player)sender).getWorld().getBlockAt(new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]))).getType() == Material.CHEST)
                         || (sender instanceof BlockCommandSender && ((BlockCommandSender)sender).getBlock().getWorld().getBlockAt(new Location(((BlockCommandSender)sender).getBlock().getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]))).getType() == Material.CHEST)) {
                            Location location = null;
                            if (sender instanceof BlockCommandSender) {
                                location = new Location(((BlockCommandSender) sender).getBlock().getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            } else {
                                location = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                            }
                            Chest chest = (Chest) location.getBlock().getState();
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
                            } else {
                                newloc = p.getLocation();
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    double dist = pl.getLocation().distance(newloc);
                                    if (closest == Double.MAX_VALUE || dist < closest) {
                                        closest = dist;
                                        closestp = pl;
                                    }
                                }
                            }
                            for (ItemStack i : chest.getBlockInventory()) {
                                if (i != null) {
                                    if (sender instanceof BlockCommandSender) {
                                        if (((BlockCommandSender) sender).getBlock().getLocation().getWorld().getBlockAt(new Location(((BlockCommandSender) sender).getBlock().getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]))).getType().equals(Material.CHEST)) {
                                            if (args[3].equalsIgnoreCase("@p")) {
                                                if (closestp == null) {
                                                    sender.sendMessage("There are no players in this world!");
                                                    return true;
                                                }
                                                closestp.getInventory().addItem(i);
                                            } else if (args[3].equalsIgnoreCase("@a")) {
                                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                                    pl.getInventory().addItem(i);
                                                }
                                            } else {
                                                Bukkit.getPlayer(args[3]).getInventory().addItem(i);
                                            }
                                        } else sender.sendMessage(Utils.chat("&cBlock is not a chest!"));
                                    } else {
                                        if (p.getWorld().getBlockAt(new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]))).getType().equals(Material.CHEST)) {
                                            if (args[3].equalsIgnoreCase("@p")) {
                                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                                    double dist = pl.getLocation().distance(newloc);
                                                    if (closest == Double.MAX_VALUE || dist < closest) {
                                                        closest = dist;
                                                        closestp = pl;
                                                    }
                                                }
                                                if (closestp == null) {
                                                    sender.sendMessage("There are no players in this world!");
                                                } else {
                                                    closestp.getInventory().addItem(i);
                                                }

                                            } else if (args[3].equalsIgnoreCase("@a")) {
                                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                                    pl.getInventory().addItem(i);
                                                }
                                            } else {
                                                Bukkit.getPlayer(args[3]).getInventory().addItem(i);
                                            }
                                        } else sender.sendMessage(Utils.chat("&cBlock is not a chest!"));
                                    }
                                }
                            }
                            sender.sendMessage(Utils.chat("&aPlayer has been given all items in the chest!"));
                            return true;
                        } else sender.sendMessage(Utils.chat("&cBlock is not a chest!"));
                    } else sender.sendMessage(Utils.chat("&cInvalid syntax!")); return false;
                } else sender.sendMessage(Utils.chat("&cThis player does not exist!")); return false;
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
        }
        return false;
    }
}
