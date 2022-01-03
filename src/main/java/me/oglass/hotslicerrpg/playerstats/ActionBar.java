package me.oglass.hotslicerrpg.playerstats;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.utils.playerActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class ActionBar {
    public static void setupActionBar() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.setFoodLevel(20);
                p.setSaturation(10);
                PlayerStats.addToStat(p, PlayerStat.Health, PlayerStat.MaxHealth, 0.025);
                PlayerStats.addToStat(p, PlayerStat.Energy, PlayerStat.MaxEnergy, 0.025);
            }
        }, 0L, 20L);
        scheduler.scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                // p.sendMessage("" + p.getInventory().getBoots().getType() + p.getInventory().getLeggings().getType() + p.getInventory().getChestplate().getType() + p.getInventory().getHelmet().getType());
                PlayerStats.updateEquipmentStats(p);
                changeActionBar(p);
            }
        }, 0L, 10L);
    }
    public static void changeActionBar(Player p) {
        String message ="§c"
                + Math.round(PlayerStats.getPlayerStat(p, PlayerStat.Health)) + "/" + Math.round(PlayerStats.getPlayerStat(p, PlayerStat.MaxHealth)) + "❤     §a"
                + Math.round(PlayerStats.getPlayerStat(p, PlayerStat.Defense)) + "❈ Defense" + "     §6"
                + Math.round(PlayerStats.getPlayerStat(p, PlayerStat.Energy)) + "/" + Math.round(PlayerStats.getPlayerStat(p, PlayerStat.MaxEnergy)) + "⚡ Energy    §c";
        playerActionBar.sendActionBar(p, message);
    }
}
