package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MoltenFuryCM {

    public static HashMap<UUID, Double> MoltenFuryCM;

    public static void setupCooldown() {
        MoltenFuryCM = new HashMap<>();
    }

    public static void setCooldown(Player p, double seconds) {
        double delay = System.currentTimeMillis() + (seconds*1000);
        MoltenFuryCM.put(p.getUniqueId(), delay);
    }

    public static int getCooldown(Player p){
        return Math.toIntExact(Math.round(MoltenFuryCM.get(p.getUniqueId()) - System.currentTimeMillis()/1000));
    }

    public static boolean checkCooldown(Player p){
        if(!MoltenFuryCM.containsKey(p.getUniqueId()) || MoltenFuryCM.get(p.getUniqueId()) <= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}
