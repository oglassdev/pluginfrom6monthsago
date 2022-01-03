package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTList;
import de.tr7zw.nbtapi.NBTListCompound;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.mobs.MobHealthManager;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {

    private Main plugin;

    public EntitySpawn(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity e = ((LivingEntity) event.getEntity());
            e.setMaximumNoDamageTicks(5);
            NBTEntity nbtent = new NBTEntity(e);
            nbtent.setByte("CanPickUpLoot", (byte) 1);
            NBTList<NBTListCompound> list = nbtent.getCompoundList("Attributes");
            if (!(event.getEntity() instanceof ArmorStand)) {
                Bukkit.getConsoleSender().sendMessage(Utils.chat("&c" + e.getName()));
                if (e.getName() != null && e.getName().equals("CUSTOM_MOB")) e.setCustomName("");
                else MobHealthManager.addEntity(e, e.getMaxHealth());
            }
            for (NBTListCompound lc : list) {
                if (lc.getString("Name").equals("generic.knockbackResistance")) {
                    lc.setDouble("Base", 0.5d);
                }
            }
        }
    }
}
