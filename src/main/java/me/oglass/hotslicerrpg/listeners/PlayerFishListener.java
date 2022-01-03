package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.cooldown.GrappleCM;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFishListener implements Listener {

    private Main plugin;

    public PlayerFishListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getEquipment().getItemInHand();
        if (item != null) {
            NBTItem inbt = new NBTItem(item);
            if (inbt.getString("CUSTOM_ID").equals("GRAPPLING_HOOK")) {
                if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH) || event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)) {
                    if (GrappleCM.checkCooldown(player)) {
                        Location loc = player.getLocation();
                        Location hookloc = event.getHook().getLocation();
                        Location change = hookloc.subtract(loc);
                        player.setVelocity(change.toVector().multiply(0.5));
                        GrappleCM.setCooldown(player, 2);
                    } else player.sendMessage(Utils.chat("&aThis is on cooldown!"));
                }
            }
        }
    }
}
