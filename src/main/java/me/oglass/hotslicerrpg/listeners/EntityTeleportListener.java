package me.oglass.hotslicerrpg.listeners;

import me.oglass.hotslicerrpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntityTeleportListener implements Listener {
    public EntityTeleportListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void entityTeleportListener(EntitySpawnEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity e = ((LivingEntity) event.getEntity());
            if (e instanceof Enderman && e.getTicksLived() < 2) {
                event.setCancelled(true);
            }
        }
    }
}