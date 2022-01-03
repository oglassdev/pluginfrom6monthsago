package me.oglass.hotslicerrpg.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.oglass.hotslicerrpg.CustomBlock;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.utils.BlockBox;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ProtocolLibPackets {
    private Main plugin;
    public ProtocolLibPackets(Main plugin, ProtocolManager protocolManager) {
        this.plugin = plugin;
        packetListener(protocolManager);
    }

    private void packetListener(ProtocolManager protocolManager) {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.BLOCK_DIG) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                PacketContainer pck = event.getPacket();
                EnumWrappers.PlayerDigType status = event.getPacket().getPlayerDigTypes().read(0);
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                    Location loc = pck.getBlockPositionModifier().readSafely(0).toLocation(player.getWorld());
                    if (status == EnumWrappers.PlayerDigType.START_DESTROY_BLOCK && BlockBox.BlockHashMap.get("MiningBox").contains(loc)) {
                        Integer i;
                        ArrayList<Material> unbreakableBlocks = new ArrayList<>();
                        ArrayList<String> unbreakableCustomBlocks = new ArrayList<>();
                        switch (player.getItemInHand().getType()) {
                            case WOOD_PICKAXE:
                                i = 100;
                                unbreakableBlocks.add(Material.OBSIDIAN);
                                unbreakableBlocks.add(Material.DIAMOND_ORE);
                                unbreakableBlocks.add(Material.DIAMOND_BLOCK);
                                unbreakableBlocks.add(Material.EMERALD_ORE);
                                unbreakableBlocks.add(Material.EMERALD_BLOCK);
                                unbreakableBlocks.add(Material.REDSTONE_ORE);
                                unbreakableBlocks.add(Material.REDSTONE_BLOCK);
                                unbreakableBlocks.add(Material.IRON_ORE);
                                unbreakableBlocks.add(Material.IRON_BLOCK);
                                unbreakableCustomBlocks.add("CRYSTAL_ORE");
                                break;
                            case STONE_PICKAXE:
                                i = 70;
                                unbreakableBlocks.add(Material.OBSIDIAN);
                                unbreakableBlocks.add(Material.DIAMOND_ORE);
                                unbreakableBlocks.add(Material.DIAMOND_BLOCK);
                                unbreakableBlocks.add(Material.EMERALD_ORE);
                                unbreakableBlocks.add(Material.EMERALD_BLOCK);
                                unbreakableBlocks.add(Material.REDSTONE_ORE);
                                unbreakableBlocks.add(Material.REDSTONE_BLOCK);
                                unbreakableCustomBlocks.add("CRYSTAL_ORE");
                                break;
                            case IRON_PICKAXE:
                                unbreakableBlocks.add(Material.OBSIDIAN);
                                unbreakableCustomBlocks.add("CRYSTAL_ORE");
                                i = 55;
                                break;
                            case GOLD_PICKAXE:
                                unbreakableBlocks.add(Material.OBSIDIAN);
                                unbreakableBlocks.add(Material.DIAMOND_ORE);
                                unbreakableBlocks.add(Material.DIAMOND_BLOCK);
                                unbreakableBlocks.add(Material.EMERALD_ORE);
                                unbreakableBlocks.add(Material.EMERALD_BLOCK);
                                unbreakableBlocks.add(Material.REDSTONE_ORE);
                                unbreakableBlocks.add(Material.REDSTONE_BLOCK);
                                unbreakableCustomBlocks.add("CRYSTAL_ORE");
                                i = 30;
                                break;
                            case DIAMOND_PICKAXE:
                                i = 40;
                                break;
                            default:
                                i = 0;
                        }
                        switch (loc.getBlock().getType()) {
                            case STONE:
                                i = i / 2;
                                break;
                            case COBBLESTONE:
                                i = (int) (i / 1.5);
                                break;
                            case OBSIDIAN:
                                i = i*3;
                                break;

                            case GOLD_ORE:
                            case GOLD_BLOCK:
                                i = (int)( i * 0.75);
                                break;

                            case DIAMOND_BLOCK:
                            case EMERALD_BLOCK:
                            case LAPIS_BLOCK:
                                i = (int) (i*1.5);
                                break;

                            case DIAMOND_ORE:
                            case EMERALD_ORE:
                            case LAPIS_ORE:
                            case REDSTONE_BLOCK:
                                break;
                            default:
                                if (CustomBlock.getLocations().contains(loc) && CustomBlock.getBlock(loc).getData().getString("CUSTOM_ID").equals("CRYSTAL_ORE") && !unbreakableCustomBlocks.contains("CRYSTAL_ORE")) {
                                    i = i*2;
                                    break;
                                }
                                i = 0;
                                break;
                        }
                        if (!unbreakableBlocks.contains(loc.getBlock().getType())) {
                            player.sendMessage(i + "");
                            AtomicReference<Integer> weeeeeee = new AtomicReference<>(0);
                            CustomBlock.getCustomIDs().forEach(name -> {
                                if (CustomBlock.getLocations().contains(loc) && CustomBlock.getBlock(loc).getData().getString("CUSTOM_ID").equals("CRYSTAL_ORE") && unbreakableCustomBlocks.contains("CRYSTAL_ORE")) weeeeeee.set(1);
                            });
                            if (weeeeeee.get().equals(0) && i > 0) new BreakAnimation(player, loc, i, true).playAnimation();
                        }
                    }
                    else if (status == EnumWrappers.PlayerDigType.STOP_DESTROY_BLOCK || status == EnumWrappers.PlayerDigType.ABORT_DESTROY_BLOCK) {
                        if (BlockBox.BlockHashMap.get("MiningBox").contains(loc) && BreakAnimation.randMap.containsKey(loc)) {
                            BreakAnimation.boolMap.put(loc, false);
                            PacketContainer packetContainer = protocolManager.createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
                            packetContainer.getBlockPositionModifier().write(0, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
                            packetContainer.getIntegers().write(0, BreakAnimation.randMap.get(loc)).write(1, 10);
                            try {
                                protocolManager.sendServerPacket(player, packetContainer);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
}
