package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionToggle implements CommandExecutor {
	
	private Main plugin;
	
	public NightVisionToggle(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("nvt").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
			return true;
		}
		Player p = (Player) sender;
		
		if (!p.hasPermission("slicer.togglenightvision")) {
			if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
				p.sendMessage(Utils.chat("You have toggled night vision on!"));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000000, 1, true, true));
			} else {
				p.sendMessage(Utils.chat("You have toggled night vision off!"));
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
			return true;
		} else {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("NoPerms")));
		}
		return false;
	}
	
}
