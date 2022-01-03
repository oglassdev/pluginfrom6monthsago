package me.oglass.hotslicerrpg.listeners;

import me.oglass.hotslicerrpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityHealListener implements Listener {

    private Main plugin;

    public EntityHealListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onEntityHeal(EntityRegainHealthEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Player && Bukkit.getOnlinePlayers().contains(e)) {
            event.setCancelled(true);
        }
    }
}