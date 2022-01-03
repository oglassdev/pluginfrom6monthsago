package me.oglass.hotslicerrpg.playerstats;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.utils.Utils;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TablistManager {
    private List<ChatComponentText> headers = new ArrayList<>();
    private List<ChatComponentText> footers = new ArrayList<>();

    private Main plugin;
    public TablistManager(Main plugin) {
        this.plugin = plugin;
    }

    public void showTab() {
        if (headers.isEmpty() && footers.isEmpty()) return;
        new BukkitRunnable() {

            PacketContainer packet1 = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

            int count1 = 0;
            int count2 = 0;

            @Override
            public void run() {
                try {
                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    if (count1 >= headers.size()) {
                        count1 = 0;
                    }
                    /*if (count2 >= footers.size()) {
                        count2 = 0;
                    }*/
                    a.set(packet, headers.get(count1));

                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            b.set(packet, new ChatComponentText(Utils.chat(" \n&c&l     Strength: &r&c"
                                    + Math.round(PlayerStats.getPlayerStat(player, PlayerStat.Strength))
                                    + "     \n&r&d&l     Magic Damage: &r&d"
                                    + Math.round(PlayerStats.getPlayerStat(player, PlayerStat.MagicDamage))
                                    + "%     \n \n&r&a     Server IP: &r&a&lHOTSLICER.SKND.HOST     ")));
                            // player.sendMessage(packet1.getClass().getDeclaredFields().toString());
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                            // ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
                        }
                    }

                    count1++;
                    count2++;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(plugin, 10, 15);
    }

    public void addHeader(String header) {
        headers.add(new ChatComponentText(Utils.chat(header)));
    }

    public void addFooter(String footer) {
        footers.add(new ChatComponentText(Utils.chat(footer)));
    }
}
