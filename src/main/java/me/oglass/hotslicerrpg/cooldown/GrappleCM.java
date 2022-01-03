package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GrappleCM {

    public static HashMap<UUID, Double> GrappleCM;

    public static void setupCooldown() {
        GrappleCM = new HashMap<>();
    }

    public static void setCooldown(Player p, double seconds) {
        double delay = System.currentTimeMillis() + (seconds*1000);
        GrappleCM.put(p.getUniqueId(), delay);
    }

    public static int getCooldown(Player p){
        return Math.toIntExact(Math.round(GrappleCM.get(p.getUniqueId()) - System.currentTimeMillis()/1000));
    }

    public static boolean checkCooldown(Player p){
        if(!GrappleCM.containsKey(p.getUniqueId()) || GrappleCM.get(p.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}
