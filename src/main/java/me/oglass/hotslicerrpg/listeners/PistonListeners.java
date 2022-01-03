package me.oglass.hotslicerrpg.listeners;

import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.CustomBlock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class PistonListeners implements Listener {

	private Main plugin;

	public PistonListeners(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPush(BlockPistonExtendEvent event) {
		for (Block block : event.getBlocks()) {
			CustomBlock cblock = CustomBlock.getBlock(event.getBlock().getLocation());
			if (cblock != null && block.getType() == Material.STAINED_GLASS && block.getData() == (byte) 10) {
				cblock.setLocation(block.getRelative(event.getDirection(), 2).getLocation());
			}
		}
	}

	@EventHandler
	public void onRetract(BlockPistonRetractEvent event) {

	}
}
