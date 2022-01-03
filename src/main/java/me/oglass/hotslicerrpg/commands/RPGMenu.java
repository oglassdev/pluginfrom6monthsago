package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RPGMenu implements CommandExecutor {
	
	private Main plugin;
	private Inventory inv;
	
	public RPGMenu(Main plugin) {
		
		this.plugin = plugin;
		plugin.getCommand("rpgmenu").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(Utils.chat(plugin.getConfig().getString("ConsoleWarning"))));
			return true;
		}
		Player p = (Player) sender;
		
		if (p.hasPermission("slicer.nomenu")) {
			p.openInventory(MenuManager.slicermenu.get(p.getUniqueId()));
		} else {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
		}
		return false;
	}
	
}
