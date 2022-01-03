package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.ItemAbilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.html.parser.Entity;

public class ArmorStandListener implements Listener {

    private Main plugin;

    public ArmorStandListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        LivingEntity interacted = (LivingEntity) event.getRightClicked();
        Player p = event.getPlayer();
        if (interacted instanceof ArmorStand) {
            event.setCancelled(true);
            ItemStack hand = p.getItemInHand();
            if (hand != null) {
                if (!hand.getType().equals(Material.AIR)) {
                    NBTItem n = new NBTItem(hand);
                    if (n.getString("CUSTOM_ID").equals("BOOMERANG")) {
                        ItemAbilities.Boomerang(p);
                    }
                }
            }
        }
    }
}
