package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.items.ItemManager;
import me.oglass.hotslicerrpg.items.MenuManager;
import me.oglass.hotslicerrpg.playerstats.Debug;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.playerstats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

	private Main plugin;
	
	public JoinListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setVelocity(p.getVelocity().multiply(0));
		p.setFallDistance(0);
		Location loc = p.getWorld().getSpawnLocation().add(0.5, 0, 0.5);
		loc.setYaw(90f);
		p.teleport(loc);
		PlayerStats.init(p);
		MenuManager.createSlicerMenu(p);
		MenuManager.createGiveMenu(p);
		MenuManager.createSettingsMenu(p);
		MenuManager.createWarpsMenu(p);
		MenuManager.createClassMenu(p);
		MenuManager.createCraftingMenu(p);
		MenuManager.createAdminMenu(p);
		MenuManager.createAdminStats(p);
		MenuManager.createCustomItemMenu(p);
		MenuManager.createCustomLoreMenu(p);
		MenuManager.createCustomNBTC(p);
		PlayerStats.updateEquipmentStats(p);
		PlayerStats.setPlayerStat(p, PlayerStat.MaxHealth, 200.0);
		PlayerStats.setPlayerStat(p, PlayerStat.MaxEnergy, 100.0);
		PlayerStats.setPlayerStat(p, PlayerStat.Health, PlayerStats.getPlayerStat(p, PlayerStat.MaxHealth));
		PlayerStats.setPlayerStat(p, PlayerStat.Energy, PlayerStats.getPlayerStat(p, PlayerStat.MaxEnergy));
		Bukkit.getLogger().info("Created Menus for Player " + p.getDisplayName());
		if (p.getInventory() != null) {
			for (int x = 0; x < p.getInventory().getSize(); x++) {
				ItemStack item = p.getInventory().getItem(x);
				if (item != null) {
					NBTItem inbt = new NBTItem(item);
					// Bukkit.getLogger().log(Level.INFO, "Checking w/ random number" + randnum);
					if (inbt.getString("CUSTOM_ID").equals("BOOMERANG_USED")) {
						String UUID = inbt.getString("UUID");
						NBTItem nbti = new NBTItem(ItemManager.Boomerang);
						nbti.setString("UUID", UUID);

						p.getInventory().remove(p.getInventory().getItem(x));
						p.getInventory().setItem(x, nbti.getItem());
					}
				}
			}
		}
		if (!p.hasPlayedBefore()) {
			p.getInventory().setItem(8, ItemManager.SlicerMenu);
		} else {
//			e.setJoinMessage(Utils.chat(plugin.getConfig().getString("joinMessage").replace("<player>", p.getName())));
			if (Main.getPlugin().playerData.getConfig().contains("PlayerData")) {
				Main.getPlugin().playerData.getConfig().getConfigurationSection("PlayerData").getKeys(false).forEach(key -> {
					if (key.equals(p.getUniqueId().toString())) {
						String PClass = Main.getPlugin().playerData.getConfig().getString("PlayerData." + key + ".Classes.Class");
						ClassManager.Players.put(key, PClass);

						Integer KClassXP = Main.getPlugin().playerData.getConfig().getInt("PlayerData." + key + ".Classes.Knight.XP");
						ClassManager.KnightXP.put(key, KClassXP);
						// ClassManager.addXP(Bukkit.getPlayer(key), KClassXP, RPGClasses.Knight);
						Integer AClassXP = Main.getPlugin().playerData.getConfig().getInt("PlayerData." + key + ".Classes.Archer.XP");
						ClassManager.ArcherXP.put(key, AClassXP);
						// ClassManager.addXP(Bukkit.getPlayer(key), AClassXP, RPGClasses.Archer);
						Integer HClassXP = Main.getPlugin().playerData.getConfig().getInt("PlayerData." + key + ".Classes.Healer.XP");
						ClassManager.HealerXP.put(key, HClassXP);

						Boolean DebugT = Main.getPlugin().playerData.getConfig().getBoolean("PlayerData." + key + ".Debug");
						Debug.set(p, DebugT);
					}
				});
			}
			p.sendMessage("Loaded Data");
			p.getInventory().setItem(8, ItemManager.SlicerMenu);
		}
	}
}
