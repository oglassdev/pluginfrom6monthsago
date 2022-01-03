package me.oglass.hotslicerrpg.playerstats;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Debug {
    public static HashMap<UUID, Boolean> Debug;
    public static void init() { Debug = new HashMap<>(); }
    public static void set(Player p, Boolean value) { Debug.put(p.getUniqueId(), value); }
    public static boolean get(Player p) { return Debug.containsKey(p.getUniqueId()) && Debug.get(p.getUniqueId()); }
}
