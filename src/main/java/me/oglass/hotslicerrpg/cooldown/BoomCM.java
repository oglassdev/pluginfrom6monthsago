package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BoomCM {

    public static HashMap<UUID, Double> BoomCD;

    public static void setupCooldown() {
        BoomCD = new HashMap<>();
    }

    public static void setCooldown(Player p, double seconds) {
        double delay = System.currentTimeMillis() + (seconds*1000);
        BoomCD.put(p.getUniqueId(), delay);
    }

    public static int getCooldown(Player p){
        return Math.toIntExact(Math.round(BoomCD.get(p.getUniqueId()) - System.currentTimeMillis()/1000));
    }

    public static boolean checkCooldown(Player p){
        if(!BoomCD.containsKey(p.getUniqueId()) || BoomCD.get(p.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}
