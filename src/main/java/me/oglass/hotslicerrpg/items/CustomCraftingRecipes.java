package me.oglass.hotslicerrpg.items;

import me.oglass.hotslicerrpg.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomCraftingRecipes {
    private static HashMap<Character, Integer> CraftingMatrix = new HashMap<>();

    public static void init() {
        CraftingMatrix.put('a', 10); CraftingMatrix.put('b', 11); CraftingMatrix.put('c', 12);
        CraftingMatrix.put('d', 19); CraftingMatrix.put('e', 20); CraftingMatrix.put('f', 21);
        CraftingMatrix.put('g', 28); CraftingMatrix.put('h', 29); CraftingMatrix.put('i', 30);
    }

    public static void checkRecipe(Inventory inv, Player p) {
        InventoryCrafting workbench = createWorkbench(p);
        if (inv.getItem(10) != null && !inv.getItem(10).getType().equals(Material.AIR) && inv.getItem(10).getItemMeta() != null) workbench.setItem(0, CraftItemStack.asNMSCopy(inv.getItem(10)));
        if (inv.getItem(11) != null && !inv.getItem(11).getType().equals(Material.AIR) && inv.getItem(11).getItemMeta() != null) workbench.setItem(1, CraftItemStack.asNMSCopy(inv.getItem(11)));
        if (inv.getItem(12) != null && !inv.getItem(12).getType().equals(Material.AIR) && inv.getItem(12).getItemMeta() != null) workbench.setItem(2, CraftItemStack.asNMSCopy(inv.getItem(12)));

        if (inv.getItem(19) != null && !inv.getItem(19).getType().equals(Material.AIR) && inv.getItem(19).getItemMeta() != null) workbench.setItem(3, CraftItemStack.asNMSCopy(inv.getItem(19)));
        if (inv.getItem(20) != null && !inv.getItem(20).getType().equals(Material.AIR) && inv.getItem(20).getItemMeta() != null) workbench.setItem(4, CraftItemStack.asNMSCopy(inv.getItem(20)));
        if (inv.getItem(21) != null && !inv.getItem(21).getType().equals(Material.AIR) && inv.getItem(21).getItemMeta() != null) workbench.setItem(5, CraftItemStack.asNMSCopy(inv.getItem(21)));

        if (inv.getItem(28) != null && !inv.getItem(28).getType().equals(Material.AIR) && inv.getItem(28).getItemMeta() != null) workbench.setItem(6, CraftItemStack.asNMSCopy(inv.getItem(28)));
        if (inv.getItem(29) != null && !inv.getItem(29).getType().equals(Material.AIR) && inv.getItem(29).getItemMeta() != null) workbench.setItem(7, CraftItemStack.asNMSCopy(inv.getItem(29)));
        if (inv.getItem(30) != null && !inv.getItem(30).getType().equals(Material.AIR) && inv.getItem(30).getItemMeta() != null) workbench.setItem(8, CraftItemStack.asNMSCopy(inv.getItem(30)));
        // (((CraftPlayer) p).getHandle()).(workbench.resultInve    ntory);
        inv.setItem(25, CraftItemStack.asBukkitCopy(workbench.resultInventory.getContents()[0]));
    }

    private static EntityPlayer toNMS(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

    private static InventoryCrafting createWorkbench(Player player) {
        BlockPosition pos = new BlockPosition(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        ContainerWorkbench workbench = new ContainerWorkbench(toNMS(player).inventory, ((CraftWorld) player.getWorld()).getHandle(), pos);
        return new InventoryCrafting(workbench, 3, 3, ((CraftPlayer) player).getHandle());
    }
}