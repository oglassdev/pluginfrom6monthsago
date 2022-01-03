package me.oglass.hotslicerrpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomBlock {
    private static HashMap<Location, CustomBlock> blockMap = new HashMap<>();

    public static ArrayList<CustomBlock> getBlocks() {
        ArrayList<CustomBlock> blocks = new ArrayList<>();
        blockMap.keySet().forEach(location -> blocks.add(blockMap.get(location)));
        return blocks;
    }
    public static ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>(blockMap.keySet());
        return locations;
    }
    public static ArrayList<String> getCustomIDs() {
        ArrayList<String> names = new ArrayList<>();
        blockMap.keySet().forEach(key -> {
            if (!names.contains(blockMap.get(key).getData().getString("CUSTOM_ID"))) names.add(blockMap.get(key).getData().getString("CUSTOM_ID"));
        });
        return names;
    }

    public static CustomBlock getBlock(Location location) {
        return blockMap.getOrDefault(location, null);
    }

    private Location Loc;
    private Material Type;
    private CustomBlockData customBlockData;

    public CustomBlock(Block block) {
        Loc = block.getLocation();
        Type = block.getType();
        blockMap.put(block.getLocation(), this);
        customBlockData = new CustomBlockData();
    }

    public Material getType() {
        return Type;
    }

    public CustomBlockData getData() {
        return customBlockData;
    }

    public void setData(CustomBlockData data) {
        customBlockData = data;
        blockMap.put(Loc, this);
    }

    public Location getLocation() {
        return Loc;
    }

    public void setType(Material material) {
        Type = material;
        blockMap.put(Loc, this);
    }

    public void removeBlock() {
        blockMap.remove(Loc);
        Loc = null;
        Type = null;
    }

    public void setLocation(Location location) {
        blockMap.remove(Loc, this);
        Loc = location;
        blockMap.put(Loc, this);
    }
}
