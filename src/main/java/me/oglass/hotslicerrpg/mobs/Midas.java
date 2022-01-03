package me.oglass.hotslicerrpg.mobs;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.NMS;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Midas {
    public static void spawnMidas(Location loc) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Baaaap__");
        npc.spawn(loc);
        npc.setName("&c&lKing Midas");
        for(Player p : loc.getWorld().getPlayers()){
            if (p.getLocation().distance(npc.getStoredLocation()) < 5) {
                NMS.attack((LivingEntity) npc.getEntity(), p);
                return;
            }
        }
    }
}
