package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Settings implements CommandExecutor {
	
	private Main plugin;
	private final Inventory inv;
	
	public Settings(Main plugin) {
		
		inv = Bukkit.createInventory(null, 27, Utils.chat("&r&l&6SlicerSMP Settings"));
		initializeItems();
		
		this.plugin = plugin;
		plugin.getCommand("rpgsettings").setExecutor(this);
	}
	
	public void initializeItems() {

	}
	
	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		
		meta.setLore(Arrays.asList(lore));
		
		item.setItemMeta(meta);
		
		return item;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
			return true;
		}

		Player p = (Player) sender;
		
		if (p.hasPermission("slicer.nomenu")) {
			p.openInventory(MenuManager.settingsMenu.get(p.getUniqueId()));
		} else {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
		}
		return false;
	}
	
}
