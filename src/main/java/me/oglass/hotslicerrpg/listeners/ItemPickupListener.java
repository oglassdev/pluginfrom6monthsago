package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickupListener implements Listener {

    private Main plugin;

    public ItemPickupListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerPickup(PlayerPickupItemEvent event){
        Item i = event.getItem();
        Player p = event.getPlayer();
        NBTItem nbti = new NBTItem(i.getItemStack());
        if(nbti.getString("CUSTOM_ID").equals("BOOMERANG_USED")) {
            event.setCancelled(true);
            i.remove();
            String UUID = nbti.getString("UUID");
            NBTItem inbt = new NBTItem(ItemManager.Boomerang);
            inbt.setString("UUID", UUID);
            p.getInventory().addItem(inbt.getItem());
            p.playSound(event.getPlayer().getLocation(), Sound.ITEM_PICKUP, 1.0f, 1.0f);
        }
    }
}
