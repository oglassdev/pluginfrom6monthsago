package me.oglass.hotslicerrpg.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class setWarpLocation implements CommandExecutor {

	private Main plugin;
	private Inventory inv;

	public setWarpLocation(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("setwarp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(Utils.chat(plugin.getConfig().getString("ConsoleWarning"))));
			return true;
		}
		Player p = (Player) sender;
		
		if (p.hasPermission("slicer.admin")) {
			if (args.length != 1) {
				p.sendMessage(Utils.chat("Invalid Args!"));
				return false;
			}
			switch (args[0]) {
				case "1":
					for (Inventory i : MenuManager.warpMenu.values()) {
						ItemStack item = i.getItem(11);
						NBTItem nbti = new NBTItem(item);
						nbti.setString("WORLD", p.getWorld().getName());
						nbti.setDouble("X", (p.getLocation().getBlockX() + 0.5));
						nbti.setDouble("Y", (p.getLocation().getBlockY() + 0.5));
						nbti.setDouble("Z", (p.getLocation().getBlockZ() + 0.5));
						item = nbti.getItem();
						i.setItem(11, item);
					}
					plugin.getConfig().set("Portals.One.X", (p.getLocation().getBlockX() + 0.5));
					plugin.getConfig().set("Portals.One.Y", (p.getLocation().getBlockY() + 0.5));
					plugin.getConfig().set("Portals.One.Z", (p.getLocation().getBlockZ() + 0.5));
					plugin.getConfig().set("Portals.One.World", p.getWorld().getName());
					break;
				case "2":
					for (Inventory i : MenuManager.warpMenu.values()) {
						ItemStack item = i.getItem(13);
						NBTItem nbti = new NBTItem(item);
						nbti.setString("WORLD", p.getWorld().getName());
						nbti.setDouble("X", (p.getLocation().getBlockX() + 0.5));
						nbti.setDouble("Y", (p.getLocation().getBlockY() + 0.5));
						nbti.setDouble("Z", (p.getLocation().getBlockZ() + 0.5));
						item = nbti.getItem();
						i.setItem(13, item);
					}
					break;
				case "3":
					for (Inventory i : MenuManager.warpMenu.values()) {
						ItemStack item = i.getItem(15);
						NBTItem nbti = new NBTItem(item);
						nbti.setString("WORLD", p.getWorld().getName());
						nbti.setDouble("X", (p.getLocation().getBlockX() + 0.5));
						nbti.setDouble("Y", (p.getLocation().getBlockY() + 0.5));
						nbti.setDouble("Z", (p.getLocation().getBlockZ() + 0.5));
						item = nbti.getItem();
						i.setItem(15, item);
					}
					break;
				default:
					p.sendMessage(Utils.chat("Invalid Args!"));
					break;
			}
			p.sendMessage(Utils.chat("&aYou can edit the item properties in the plugin's config.yml"));
		} else {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
		}
		return false;
	}
	
}
