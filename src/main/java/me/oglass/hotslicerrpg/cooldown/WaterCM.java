package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class WaterCM {
	
	public static HashMap<UUID, Double> WaterCM;
	
	public static void setupCooldown() {
		WaterCM = new HashMap<>();
	}
	
	public static void setCooldown(Player p, double seconds) {
		double delay = System.currentTimeMillis() + (seconds*1000);
		WaterCM.put(p.getUniqueId(), delay);
	}
	
	public static int getCooldown(Player p){
		return Math.toIntExact(Math.round(WaterCM.get(p.getUniqueId()) - System.currentTimeMillis()/1000));
	}
	
	public static boolean checkCooldown(Player p){
		if(!WaterCM.containsKey(p.getUniqueId()) || WaterCM.get(p.getUniqueId()) <= System.currentTimeMillis()){
			return true;
		}
		return false;
	}
}
