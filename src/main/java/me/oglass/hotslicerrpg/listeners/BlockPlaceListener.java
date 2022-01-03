package me.oglass.hotslicerrpg.listeners;

import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.CustomBlockData;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class BlockPlaceListener implements Listener {

    private Main plugin;

    public BlockPlaceListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        ItemStack itemStack = event.getItemInHand();
        NBTItem nbti = new NBTItem(itemStack);
        if (nbti.getString("CUSTOM_ID").equals("CRYSTAL_ORE")) {
            CustomBlock cblock = new CustomBlock(event.getBlockPlaced());
            CustomBlockData cblockdata = cblock.getData();
            cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
            cblockdata.setString("VARIANT", nbti.getString("VARIANT"));
            cblock.setData(cblockdata);
            p.sendMessage("Set Data: " + cblockdata.getString("CUSTOM_ID") + ", " + cblockdata.getString("VARIANT"));
        }
    }
}
