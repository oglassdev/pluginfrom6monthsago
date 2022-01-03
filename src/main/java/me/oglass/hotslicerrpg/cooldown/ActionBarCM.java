package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ActionBarCM {

    public static HashMap<UUID, Double> ActionBarCD;

    public static void setupCooldown() {
        ActionBarCD = new HashMap<>();
    }

    public static void setCooldown(Player p, double seconds) {
        double delay = System.currentTimeMillis() + (seconds * 1000);
        ActionBarCD.put(p.getUniqueId(), delay);
    }

    public static int getCooldown(Player p) {
        return Math.toIntExact(Math.round(ActionBarCD.get(p.getUniqueId()) - System.currentTimeMillis() / 1000));
    }

    public static boolean checkCooldown(Player p) {
        if (!ActionBarCD.containsKey(p.getUniqueId()) || ActionBarCD.get(p.getUniqueId()) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }
}