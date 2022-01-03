package me.oglass.hotslicerrpg.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class WitherCM {
	
	public static HashMap<UUID, Double> WitherCM;
	
	public static void setupCooldown() {
		WitherCM = new HashMap<>();
	}
	
	public static void setCooldown(Player p, double seconds) {
		double delay = System.currentTimeMillis() + (seconds*1000);
		WitherCM.put(p.getUniqueId(), delay);
	}
	
	public static int getCooldown(Player p){
		return Math.toIntExact(Math.round(WitherCM.get(p.getUniqueId()) - System.currentTimeMillis()/1000));
	}
	
	public static boolean checkCooldown(Player p){
		if(!WitherCM.containsKey(p.getUniqueId()) || WitherCM.get(p.getUniqueId()) <= System.currentTimeMillis()){
			return true;
		}
		return false;
	}
}
