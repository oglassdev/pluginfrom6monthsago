package me.oglass.hotslicerrpg.playerstats;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerStats {
    public static HashMap<UUID, HashMap<PlayerStat,Double>> stats = new HashMap<>();
    public static HashMap<UUID, HashMap<PlayerStat,Double>> armorStats = new HashMap<>();
    public static void init(Player player) {
        stats.put(player.getUniqueId(), new HashMap<>());
        armorStats.put(player.getUniqueId(), new HashMap<>());
    }
    public static void setPlayerStat(Player player, PlayerStat stat, Double amount){
        HashMap<PlayerStat,Double> map;
        if (!stats.containsKey(player.getUniqueId())) map = new HashMap<>();
        else map = stats.get(player.getUniqueId());
        map.put(stat, amount);
        stats.put(player.getUniqueId(), map);
    }
    public static void setArmorStat(Player player, PlayerStat stat, Double amount){
        HashMap<PlayerStat,Double> map;
        if (!armorStats.containsKey(player.getUniqueId())) map = new HashMap<>();
        else map = armorStats.get(player.getUniqueId());
        map.put(stat, amount);
        armorStats.put(player.getUniqueId(), map);
    }
    public static void addToStat(Player player, PlayerStat stat, PlayerStat stat2, Double amount) {
        if (getPlayerStat(player, stat) <= (PlayerStats.getPlayerStat(player, stat2) - (PlayerStats.getPlayerStat(player, stat2) * amount))) {
            PlayerStats.setPlayerStat(player, stat, PlayerStats.getPlayerStat(player, stat) + PlayerStats.getPlayerStat(player, stat2) * amount);
        } else if (PlayerStats.getPlayerStat(player, stat) > (PlayerStats.getPlayerStat(player, stat2) - (PlayerStats.getPlayerStat(player, stat2) * amount)) && PlayerStats.getPlayerStat(player, stat) < (PlayerStats.getPlayerStat(player, stat2))) {
            PlayerStats.setPlayerStat(player, stat, PlayerStats.getPlayerStat(player, stat2));
        }
    }
    public static void removePlayerStat(Player player, PlayerStat stat){
        HashMap<PlayerStat,Double> map;
        if (!stats.containsKey(player.getUniqueId())) map = new HashMap<>();
        else map = stats.get(player.getUniqueId());
        map.remove(stat);
        stats.put(player.getUniqueId(), map);
    }
    public static void changePlayerStat(Player player, PlayerStat stat){
        HashMap<PlayerStat,Double> map;
        if (!stats.containsKey(player.getUniqueId())) map = new HashMap<>();
        else map = stats.get(player.getUniqueId());
        map.remove(stat);
        stats.put(player.getUniqueId(), map);
    }
    @Nonnull
    public static Double getPlayerStat(Player player, PlayerStat stat){
        return stats.get(player.getUniqueId()).getOrDefault(stat, 0.0) + armorStats.get(player.getUniqueId()).getOrDefault(stat, 0.0);
    }
    public static void killPlayer(Player player, Boolean deathMessage, Boolean loseCoins) {
        player.setHealth(player.getMaxHealth());
        setPlayerStat(player, PlayerStat.Health, getPlayerStat(player, PlayerStat.MaxHealth));
        player.setVelocity(new Vector(0, 0, 0));
        player.setFireTicks(0);
        player.setFallDistance(0);
        player.teleport(player.getWorld().getSpawnLocation().add(0.5,0.5,0.5));
        if (deathMessage) {
            for (Player p : player.getWorld().getPlayers()) {
                if (!p.equals(player)) p.sendMessage(Utils.chat("&c☠ &7" + player.getDisplayName() + " died!"));
                else player.sendMessage(Utils.chat("&c☠ &7You died!"));
            }
        }
        if (loseCoins) {

        }
    }
    public static void killPlayer(Player player, Boolean loseCoins, Boolean deathMessage, EntityDamageEvent.DamageCause cause) {
        player.setHealth(player.getMaxHealth());
        setPlayerStat(player, PlayerStat.Health, getPlayerStat(player, PlayerStat.MaxHealth));
        player.setVelocity(new Vector(0, 0, 0));
        player.setFireTicks(0);
        player.setFallDistance(0);
        player.teleport(player.getWorld().getSpawnLocation().add(0.5,0.5,0.5));
        if (deathMessage) {
            for (Player p : player.getWorld().getPlayers()) {
                switch (cause) {
                    case FALL:
                    case SUFFOCATION:
                        if (player.equals(p)) {
                            player.sendMessage(Utils.chat("&c☠ You suffocated"));
                        } else {
                            p.sendMessage(Utils.chat("&c☠ " + player.getDisplayName() + " decided not to breathe"));
                        }
                    case FIRE:
                    case FIRE_TICK:
                    case LAVA:
                        if (player.equals(p)) {
                            player.sendMessage(Utils.chat("&c☠ You burnt to death"));
                        } else {
                            p.sendMessage(Utils.chat("&c☠ " + player.getDisplayName() + " became a chicken nugget"));
                        }
                    case VOID:
                        if (player.equals(p)) {
                            player.sendMessage(Utils.chat("&c☠ You fell into the void"));
                        } else {
                            p.sendMessage(Utils.chat("&c☠ " + player.getDisplayName() + " fell into the void"));
                        }
                    default:
                        if (player.equals(p)) {
                            player.sendMessage(Utils.chat("&c☠ You were killed by " + cause.name().toLowerCase()));
                        } else {
                            p.sendMessage(Utils.chat("&c☠ " + player.getDisplayName() + " was killed by " + cause.name().toLowerCase()));
                        }
                }
            }
        }
        if (loseCoins) {

        }
    }
    public static ArrayList<ItemStack> getArmorContents(Player player) {
        return new ArrayList<>(Arrays.asList(player.getEquipment().getArmorContents()));
    }
    public static void updateEquipmentStats(Player player) {
        AtomicReference<Double> maxhealth = new AtomicReference<>(0.0);
        AtomicReference<Double> defense = new AtomicReference<>(0.0);
        AtomicReference<Double> maxenergy = new AtomicReference<>(0.0);
        AtomicReference<Double> magicdamage = new AtomicReference<>(0.0);
        AtomicReference<Double> strength = new AtomicReference<>(0.0);
        getArmorContents(player).forEach(itemStack -> {
            if (itemStack.getType() != Material.AIR && itemStack.getItemMeta() != null) {
                NBTItem nbti = new NBTItem(itemStack);
                NBTCompound nbtc = nbti.getCompound("STATS");
                if (nbtc != null) {
                    if (nbtc.getDouble("HEALTH") != null) maxhealth.set(maxhealth.get() + nbtc.getDouble("HEALTH"));
                    if (nbtc.getDouble("DEFENSE") != null) defense.set(defense.get() + nbtc.getDouble("DEFENSE"));
                    if (nbtc.getDouble("ENERGY") != null)
                        maxenergy.set(maxenergy.get() + nbtc.getDouble("ENERGY"));
                    if (nbtc.getDouble("MAGIC_DAMAGE") != null)
                        magicdamage.set(magicdamage.get() + nbtc.getDouble("MAGIC_DAMAGE"));
                    if (nbtc.getDouble("STRENGTH") != null)
                        strength.set(strength.get() + nbtc.getDouble("STRENGTH"));
                }
            }
        });
        setArmorStat(player, PlayerStat.MaxHealth, maxhealth.get());
        setArmorStat(player, PlayerStat.Defense, defense.get());
        setArmorStat(player, PlayerStat.MaxEnergy, maxenergy.get());
        setArmorStat(player, PlayerStat.MagicDamage, magicdamage.get());
        setArmorStat(player, PlayerStat.Strength, strength.get());
    }
}