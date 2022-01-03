package me.oglass.hotslicerrpg.listeners;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.items.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    private Main plugin;
    private Integer TaskID;

    public InventoryCloseListener(Main plugin) {

        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void InventoryCloseListen(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player p = (Player) event.getPlayer();
            if (event.getInventory().equals(MenuManager.craftingMenu.get(p.getUniqueId()))) {
                Integer[] slots = {10, 11, 12, 19, 20, 21, 28, 29, 30};
                for (int i : slots) {
                    if (event.getInventory().getItem(i) != null) {
                        p.getInventory().addItem(event.getInventory().getItem(i));
                        event.getInventory().setItem(i, null);
                    }
                }
            }
        }
    }
}
