package me.oglass.hotslicerrpg.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.utils.BlockBox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

public class BreakAnimation {
    public static final HashMap<Location, BreakAnimation> blockMap = new HashMap<>();
    public static final HashMap<Location, Integer> randMap = new HashMap<>();
    public static final HashMap<Location, Boolean> boolMap = new HashMap<>();
    private final Player breaker;
    private final Location loc;
    private final Boolean nearby;
    private final Integer random;
    private final Integer delay;
    private Integer state = 0;

    public BreakAnimation(Player breaker, Location loc, Integer tickDelay, Boolean nearby) {
        this.breaker = breaker;
        this.loc = loc;
        this.delay = tickDelay;
        this.nearby = nearby;
        this.random = new Random().nextInt(40000) + 1;
        randMap.put(loc, random);
        blockMap.put(loc, this);
        boolMap.put(loc, true);
    }

    public void setState(Integer state) {
        if (state > 9 || state < 0) this.state = 10;
        else {
            PacketContainer packetContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
            packetContainer.getBlockPositionModifier().write(0, new com.comphenix.protocol.wrappers.BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
            packetContainer.getIntegers().write(0, BreakAnimation.randMap.get(loc)).write(1, state);
            if (nearby) {
                loc.getWorld().getPlayers().forEach(player -> {
                    try {
                        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);

                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                try {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(breaker, packetContainer);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        blockMap.put(loc, this);
    }
    public void playAnimation() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (state > 10 || !boolMap.get(loc)) {
                    blockMap.remove(loc);
                    cancel();
                } else if (state == 10) {
                    BlockBreakEvent event = new BlockBreakEvent(loc.getBlock(), breaker);
                    if (!BlockBox.BlockHashMap.get("MiningBox").contains(loc)) loc.getBlock().breakNaturally();
                    Bukkit.getPluginManager().callEvent(event);
                    // PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(2001, loc.getX(), loc.getY(), loc.getZ(), loc.getBlock().getTypeId(), false);
                    removeBlock();
                }
                if (!blockMap.containsKey(loc)) return;
                setState(state);
                state = state + 1;
            }
        }.runTaskTimer(Main.getPlugin(), 0, (long) (delay * 0.1));
        blockMap.put(loc, this);
    }
    public void removeBlock() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        if (blockMap.containsKey(loc)) {
            PacketContainer packetContainer = protocolManager.createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
            packetContainer.getBlockPositionModifier().write(0, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
            packetContainer.getIntegers().write(0, random).write(1, 10);
            try {
                protocolManager.sendServerPacket(breaker, packetContainer);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            setState(10);
            blockMap.remove(loc);
        }
    }
}
