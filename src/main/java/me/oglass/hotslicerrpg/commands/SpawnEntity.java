package me.oglass.hotslicerrpg.commands;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.mobs.*;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnEntity implements CommandExecutor {
    private Main plugin;

    public SpawnEntity(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("spawnentity").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("ConsoleWarning")));
            return true;
        }
        Player p = (Player) sender;

        if (p.hasPermission("slicer.admin")) {
            for (String str : args) {
                switch (str.toLowerCase()) {
                    case "customzombie":
                    case "custom_zombie":
                        EntityTypes.spawnEntity(new CustomZombie(p.getWorld()), p.getLocation(), true);
                        break;
                    case "zombiesoldier":
                    case "zombie_soldier":
                        EntityTypes.spawnEntity(new ZombieSoldier(p.getWorld()), p.getLocation(), true);
                        break;
                    case "manticore":
                        EntityTypes.spawnEntity(new Manticore(p.getWorld()), p.getLocation(), true);
                        break;
                    case "phoenix":
                        EntityTypes.spawnEntity(new Phoenix(p.getWorld()), p.getLocation(), true);
                        break;
                }
            }
        }
        return false;
    }
}
