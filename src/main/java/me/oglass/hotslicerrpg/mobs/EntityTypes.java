package me.oglass.hotslicerrpg.mobs;

import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;

import java.util.Map;

import static me.oglass.hotslicerrpg.mobs.CustomZombie.getPrivateField;

public enum EntityTypes
{
    //NAME("Entity name", Entity ID, yourcustomclass.class);
    CUSTOM_ZOMBIE("Custom_Zombie", 54, CustomZombie.class), //You can add as many as you want.
    MANTICORE("Manticore", 58, Manticore.class), //You can add as many as you want.
    PHOENIX("Phoenix", 61, Phoenix.class), //You can add as many as you want.
    ZOMBIE_SOLDIER("Zombie_Soldier", 54, ZombieSoldier.class); //You can add as many as you want.

    private EntityTypes(String name, int id, Class<? extends Entity> custom)
    {
        addToMaps(custom, name, id);
    }

    public static void spawnEntity(Entity entity, Location loc, Boolean useCustomHealth)
    {
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
        ((CraftWorld)loc.getWorld()).getHandle().addEntity(entity);
        if (useCustomHealth) {
            if (entity instanceof CustomZombie) MobHealthManager.addEntity(CraftEntity.getEntity((CraftServer) Bukkit.getServer(), entity), 100.0, "Custom Zombie", 10);
            else if (entity instanceof ZombieSoldier) MobHealthManager.addEntity(CraftEntity.getEntity((CraftServer) Bukkit.getServer(), entity), 200.0, "Zombie Soldier", 15);
            else if (entity instanceof Manticore) MobHealthManager.addEntity(CraftEntity.getEntity((CraftServer) Bukkit.getServer(), entity), 900.0, "Manticore", 30);
            else if (entity instanceof Phoenix) MobHealthManager.addEntity(CraftEntity.getEntity((CraftServer) Bukkit.getServer(), entity), 300.0, "Phoenix", 10);
        }
    }

    private static void addToMaps(Class clazz, String name, int id)
    {
        //getPrivateField is the method from above.
        //Remove the lines with // in front of them if you want to override default entities (You'd have to remove the default entity from the map first though).
        ((Map)getPrivateField("c", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(name, clazz);
        ((Map)getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);
        //((Map)getPrivateField("e", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(Integer.valueOf(id), clazz);
        ((Map)getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
        //((Map)getPrivateField("g", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(name, Integer.valueOf(id));
    }
}
