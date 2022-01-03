package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {
	
	private Main plugin;
	
	public ItemDropListener(Main plugin) {
		
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerDrop(PlayerDropItemEvent event){
	    Item i = event.getItemDrop();
	    NBTItem nbti = new NBTItem(i.getItemStack());
	    if(nbti.getString("CUSTOM_ID").equals("RPG_MENU")) {
	    	i.remove();
	    	event.getPlayer().getInventory().setItem(8, ItemManager.SlicerMenu);
	    }
	}
}
