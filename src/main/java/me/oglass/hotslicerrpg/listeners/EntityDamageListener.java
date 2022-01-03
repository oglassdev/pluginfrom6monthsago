package me.oglass.hotslicerrpg.listeners;


import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.mobs.MobHealthManager;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.playerstats.PlayerStats;
import me.oglass.hotslicerrpg.utils.Utils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class EntityDamageListener implements Listener {

    private Main plugin;

    private HashMap<Entity, Double> entityMap = new HashMap<>();

    public EntityDamageListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Player && Bukkit.getOnlinePlayers().contains(e)) {
            Player p = ((Player) e).getPlayer();
            double damage = event.getDamage();
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                PlayerStats.killPlayer(p, true, true, event.getCause());
            } else {
                if (PlayerStats.getPlayerStat(p, PlayerStat.Health) < (damage * 10)) {
                    PlayerStats.killPlayer(p, true, true, event.getCause());
                } else {
                    double dmg = (PlayerStats.getPlayerStat(p, PlayerStat.Health) - damage * 10) / (PlayerStats.getPlayerStat(p, PlayerStat.Defense) / 100 + 1);
                    PlayerStats.setPlayerStat(p, PlayerStat.Health, dmg);
                }
            }
            event.setDamage(0.01);
        }
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event1 = (EntityDamageByEntityEvent) event;
            Entity damager = event1.getDamager();
            if (damager instanceof Player && Bukkit.getOnlinePlayers().contains(damager)) {
                if (e instanceof ArmorStand && ((ArmorStand) e).isMarker()) return;
                Location loc = e.getLocation();
                loc.add((Math.random() * 1.5) - 0.75, Math.random() + 0.5, (Math.random() * 1.5) - 0.75);
                EntityArmorStand stand = new EntityArmorStand(((CraftWorld) (e.getLocation().getWorld())).getHandle(), loc.getX(), loc.getY(), loc.getZ());
                stand.setCustomNameVisible(true);
                stand.setInvisible(true);
                stand.setGravity(false);
                stand.setCustomName(Utils.chat("&c☣ " + Math.round(event.getDamage())));
                stand.n(true);
                PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(stand);
                PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(stand.getId(), stand.getDataWatcher(), true);
                ((CraftPlayer) damager).getHandle().playerConnection.sendPacket(spawnPacket);
                ((CraftPlayer) damager).getHandle().playerConnection.sendPacket(metadataPacket);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        ((CraftPlayer) damager).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(stand.getId()));
                    }
                }.runTaskLater(plugin, 20);
                if (!e.isDead()) {
                    entityMap.put(e, event.getDamage());
                }
            }
        }
        if (MobHealthManager.containsEntity(e)) {
            MobHealthManager.setHealth(e, MobHealthManager.getHealth(e) - event.getDamage());
            event.setDamage(0.001);
        }
    }
}