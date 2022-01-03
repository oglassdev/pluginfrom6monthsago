package me.oglass.hotslicerrpg.utils;

import me.oglass.hotslicerrpg.CustomBlock;
import me.oglass.hotslicerrpg.CustomBlockData;
import me.oglass.hotslicerrpg.playerstats.Debug;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class BlockBox {
    public static HashMap<String, ArrayList<Location>> BlockHashMap = new HashMap<>();
    public static HashMap<Integer, Location> Locations = new HashMap<>();
    public static HashMap<UUID, Location> PlayerLoc1 = new HashMap<>();
    public static HashMap<UUID, Location> PlayerLoc2 = new HashMap<>();
    public static HashMap<UUID, Location> PlayerLoc3 = new HashMap<>();
    public static HashMap<UUID, Location> PlayerLoc4 = new HashMap<>();
    public static void Blocks(Location loc1, Location loc2, Player player, String type) {
        ArrayList<Location> locarray = new ArrayList<>();
        int X1 = loc1.getBlockX();
        int X2 = loc2.getBlockX();
        int Y1 = loc1.getBlockY();
        int Y2 = loc2.getBlockY();
        int Z1 = loc1.getBlockZ();
        int Z2 = loc2.getBlockZ();
        if (loc1.getWorld() == loc2.getWorld() && !loc1.equals(loc2)) {
            if (X1 <= X2) {
                for (int x = X1; x <= X2; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    }
                }
            } else {
                for (int x = X2; x <= X1; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    if (Debug.get(player)) {
                                        player.sendMessage(x + " " + y + " " + z);
                                    }
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    }
                }
            }
            BlockHashMap.put(type, locarray);
            player.sendMessage(Utils.chat("&aSelected " + locarray.size() + " blocks!"));
        } else if (loc1.equals(loc2)) {
            locarray.add(loc1);
            BlockHashMap.put(type, locarray);
            player.sendMessage(Utils.chat("&aSelected 1 block!"));
        } else {
            player.sendMessage(Utils.chat("&cAn error occurred!"));
            return;
        }
        if (type.equals("MiningBox")) {
            Locations.put(0, loc1);
            Locations.put(1, loc2);
        }
    }
    public static void Blocks(Location loc1, Location loc2, String type) {
        ArrayList<Location> locarray = new ArrayList<>();
        int X1 = loc1.getBlockX();
        int X2 = loc2.getBlockX();
        int Y1 = loc1.getBlockY();
        int Y2 = loc2.getBlockY();
        int Z1 = loc1.getBlockZ();
        int Z2 = loc2.getBlockZ();
        if (loc1.getWorld() == loc2.getWorld() && !loc1.equals(loc2)) {
            if (X1 <= X2) {
                for (int x = X1; x <= X2; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    }
                }
            } else {
                for (int x = X2; x <= X1; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    locarray.add(new Location(loc1.getWorld(), x, y, z));
                                }
                            }
                        }
                    }
                }
            }
            BlockHashMap.put(type, locarray);
        } else if (loc1.equals(loc2)) {
            locarray.add(loc1);
            BlockHashMap.put(type, locarray);
        } else {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("&cAn error occured with setting the mines box!"));
            return;
        }
            Locations.put(0, loc1);
            Locations.put(1, loc2);
    }
    public static void GlassConversion(Location loc1, Location loc2) {
        int X1 = loc1.getBlockX();
        int X2 = loc2.getBlockX();
        int Y1 = loc1.getBlockY();
        int Y2 = loc2.getBlockY();
        int Z1 = loc1.getBlockZ();
        int Z2 = loc2.getBlockZ();
        if (loc1.getWorld() == loc2.getWorld() && !loc1.equals(loc2)) {
            if (X1 <= X2) {
                for (int x = X1; x <= X2; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (int x = X2; x <= X1; x++) {
                    if (Y1 <= Y2) {
                        for (int y = Y1; y <= Y2; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int y = Y2; y <= Y1; y++) {
                            if (Z1 <= Z2) {
                                for (int z = Z1; z <= Z2; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (int z = Z2; z <= Z1; z++) {
                                    Location location = new Location(loc1.getWorld(), x, y, z);
                                    if (location.getBlock().getType().equals(Material.STAINED_GLASS)) {
                                        switch (location.getBlock().getData()) {
                                            case (byte) 4: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "GOLD");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 10: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "CORRUPT");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 11: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "BLUE");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                            case (byte) 14: {
                                                CustomBlock cblock = new CustomBlock(location.getBlock());
                                                CustomBlockData cblockdata = cblock.getData();
                                                cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                                                cblockdata.setString("VARIANT", "RED");
                                                cblock.setData(cblockdata);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (loc1.equals(loc2)) {
            if (loc1.getBlock().getType().equals(Material.STAINED_GLASS)) {
                switch (loc1.getBlock().getData()) {
                    case (byte) 4: {
                        CustomBlock cblock = new CustomBlock(loc1.getBlock());
                        CustomBlockData cblockdata = cblock.getData();
                        cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                        cblockdata.setString("VARIANT", "GOLD");
                        cblock.setData(cblockdata);
                        break;
                    }
                    case (byte) 10: {
                        CustomBlock cblock = new CustomBlock(loc1.getBlock());
                        CustomBlockData cblockdata = cblock.getData();
                        cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                        cblockdata.setString("VARIANT", "CORRUPT");
                        cblock.setData(cblockdata);
                        break;
                    }
                    case (byte) 11: {
                        CustomBlock cblock = new CustomBlock(loc1.getBlock());
                        CustomBlockData cblockdata = cblock.getData();
                        cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                        cblockdata.setString("VARIANT", "BLUE");
                        cblock.setData(cblockdata);
                        break;
                    }
                    case (byte) 14: {
                        CustomBlock cblock = new CustomBlock(loc1.getBlock());
                        CustomBlockData cblockdata = cblock.getData();
                        cblockdata.setString("CUSTOM_ID", "CRYSTAL_ORE");
                        cblockdata.setString("VARIANT", "RED");
                        cblock.setData(cblockdata);
                        break;
                    }
                }
            }
        } else {
            Bukkit.getLogger().log(Level.WARNING, Utils.chat("An error occured with converting the glass!"));
        }
    }
}
