package me.oglass.hotslicerrpg.mobs;

import me.oglass.hotslicerrpg.utils.Utils;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MobHealthManager {
    private static HashMap<Entity, Double> entityHashMap = new HashMap<>();
    private static HashMap<Entity, Double> entityHealthMap = new HashMap<>();
    private static HashMap<Entity, ArrayList<Entity>> armorStandMap = new HashMap<>();
    public static void addEntity(Entity entity, Double maxHealth) {
        Entity stand = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);

        ((ArmorStand) stand).setMarker(true);
        ((ArmorStand) stand).setSmall(true);
        ((ArmorStand) stand).setVisible(false);
        stand.setCustomName(Utils.chat("&c♥ " + maxHealth + "/" + maxHealth));
        stand.setCustomNameVisible(false);

        Entity armorStand = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setPassenger(stand);
        entity.setPassenger(armorStand);

        ((ArmorStand) armorStand).setVisible(false);
        ((ArmorStand) armorStand).setSmall(true);
        ((ArmorStand) stand).setMarker(true);
        armorStand.setCustomName(Utils.chat("&c♥ " + maxHealth + "/" + maxHealth));
        armorStand.setCustomNameVisible(true);

        entityHashMap.put(entity, maxHealth);
        entityHealthMap.put(entity, maxHealth);
        ArrayList<Entity> list = new ArrayList<>();
        list.add(armorStand);
        list.add(stand);
        armorStandMap.put(entity, list);
    }
    public static void addEntity(Entity entity, Double maxHealth, String name, Integer level) {
        Entity stand = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);

        ((ArmorStand) stand).setMarker(true);
        ((ArmorStand) stand).setSmall(true);
        ((ArmorStand) stand).setVisible(false);
        stand.setCustomName(Utils.chat("&6["+ level +"]  " + name));
        stand.setCustomNameVisible(true);

        Entity armorStand = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setPassenger(stand);
        entity.setPassenger(armorStand);

        ((ArmorStand) armorStand).setVisible(false);
        ((ArmorStand) armorStand).setSmall(true);
        ((ArmorStand) stand).setMarker(true);
        armorStand.setCustomName(Utils.chat("&c" + maxHealth + "/" + maxHealth + "❤"));
        armorStand.setCustomNameVisible(true);

        entityHashMap.put(entity, maxHealth);
        entityHealthMap.put(entity, maxHealth);
        ArrayList<Entity> list = new ArrayList<>();
        list.add(armorStand);
        list.add(stand);
        armorStandMap.put(entity, list);
    }
    public static void removeEntity(Entity entity) {

    }
    public static void setHealth(Entity entity, Double health) {
        ArmorStand armorStand;
        ArmorStand stand;
        if (entity.getPassenger() != null && entity.getPassenger().getPassenger() != null
        && entity.getPassenger() instanceof ArmorStand && entity.getPassenger().getPassenger() instanceof ArmorStand) {
            stand = (ArmorStand) armorStandMap.get(entity).get(0);
            Bukkit.getConsoleSender().sendMessage(stand.getCustomName());
            armorStand = (ArmorStand) armorStandMap.get(entity).get(1);
            Bukkit.getConsoleSender().sendMessage(armorStand.getCustomName());
        } else return;
        Double maxHealth = 0.0;
        if (entityHashMap.containsKey(entity)) {
            Bukkit.getConsoleSender().sendMessage("yeee");
            maxHealth = entityHashMap.get(entity);
        }
        if (health <= 0) {
            stand.remove();
            armorStand.remove();
            entityHashMap.remove(entity);
            entityHealthMap.remove(entity);
            ((LivingEntity) entity).setHealth(0);
            return;
        }

        stand.setCustomName(Utils.chat("&c♥ " + health + "/" + maxHealth));
        //armorStand.setCustomName(Utils.chat("&c♥ " + health + "/" + maxHealth));

        entityHashMap.put(entity, maxHealth);
        entityHealthMap.put(entity, health);
        ArrayList<Entity> list = new ArrayList<>();
        list.add(stand);
        list.add(armorStand);
        armorStandMap.put(entity, list);
    }
    public static boolean containsEntity(Entity entity) {
        return entityHashMap.containsKey(entity) && entityHealthMap.containsKey(entity);
    }
    public static double getMaxHealth(Entity entity) {
        return entityHashMap.get(entity);
    }
    public static double getHealth(Entity entity) {
        return entityHealthMap.get(entity);
    }
}
