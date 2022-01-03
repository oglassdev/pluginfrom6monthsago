package me.oglass.hotslicerrpg.items;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import de.tr7zw.nbtapi.NBTItem;
import me.oglass.hotslicerrpg.Main;
import me.oglass.hotslicerrpg.cooldown.BoomCM;
import me.oglass.hotslicerrpg.cooldown.MoltenFuryCM;
import me.oglass.hotslicerrpg.cooldown.WaterCM;
import me.oglass.hotslicerrpg.cooldown.WitherCM;
import me.oglass.hotslicerrpg.playerstats.Debug;
import me.oglass.hotslicerrpg.enums.PlayerStat;
import me.oglass.hotslicerrpg.playerstats.PlayerStats;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;

public class ItemAbilities {
    public static HashMap<String, Long> BoomerangUses;
    public static void setupMaps() { BoomerangUses = new HashMap<>(); }

    public static void BoomBoomSword(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        NBTItem nbti = new NBTItem(event.getItem());
        if (BoomCM.checkCooldown(event.getPlayer()) && PlayerStats.getPlayerStat(p, PlayerStat.Energy) >= nbti.getInteger("ENERGY_COST")) {
            int blockrange;
            for (blockrange = 8; blockrange >= 0; blockrange--) {
                HashSet<Byte> NullHashSet = null;
                Material block = p.getTargetBlock(NullHashSet, blockrange).getType();
                Material blockup = p.getTargetBlock(NullHashSet, blockrange).getLocation().add(0, 1, 0).getBlock().getType();
                if ((block.equals(Material.AIR) || block.equals(Material.WATER) || block.equals(Material.LAVA)) &&
                        (blockup.equals(Material.AIR) || blockup.equals(Material.WATER) || blockup.equals(Material.LAVA))) {
                    Location blockloc = p.getTargetBlock(NullHashSet, blockrange).getLocation();
                    float pitch = p.getEyeLocation().getPitch();
                    float yaw = p.getEyeLocation().getYaw();
                    blockloc.add(0.5, 0, 0.5);
                    blockloc.setYaw(yaw);
                    blockloc.setPitch(pitch);
                    p.teleport(blockloc);
                    break;
                }
            }
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 0.4F, 1F);
            p.playSound(p.getLocation(), Sound.EXPLODE, 0.4F, 1F);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 1);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 1);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 1);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 1);
            PlayerStats.setPlayerStat(p, PlayerStat.Energy, PlayerStats.getPlayerStat(p, PlayerStat.Energy) - nbti.getInteger("ENERGY_COST") * -1);

            for (Entity entity : p.getNearbyEntities(8, 8, 8)) {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(30, p);
                }
            }
            BoomCM.setCooldown(p, 0.15);
        }
    }

    public static void WitherSword(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        NBTItem nbti = new NBTItem(event.getItem());
        if (WitherCM.checkCooldown(event.getPlayer()) && PlayerStats.getPlayerStat(p, PlayerStat.Energy) > (nbti.getInteger("ENERGY_COST") - 1)) {
            p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 0.4F, 1F);
            p.launchProjectile(WitherSkull.class);
            WitherCM.setCooldown(p, 0.3);
            PlayerStats.setPlayerStat(p, PlayerStat.Energy, PlayerStats.getPlayerStat(p, PlayerStat.Energy) - nbti.getInteger("ENERGY_COST") * -1);
        }
    }

    public static void WaterGun(PlayerInteractEvent event) {
        NBTItem nbti = new NBTItem(event.getItem());
        Player p = event.getPlayer();
        if (WaterCM.checkCooldown(p) && PlayerStats.getPlayerStat(p, PlayerStat.Energy) >= nbti.getInteger("ENERGY_COST")) {
            PlayerStats.setPlayerStat(p, PlayerStat.Energy, PlayerStats.getPlayerStat(p, PlayerStat.Energy) - 10);
            Location loc = p.getEyeLocation();
            World world = p.getWorld();
            double maxLength = 20;
            int t = 0;
            p.playSound(p.getLocation(), Sound.WATER, 0.2f, 1f);
            for (double d = 0; d <= maxLength; d += 0.5) {
                if (t == 1) break;
                loc.add(loc.getDirection().multiply(0.5));
                if (!loc.getBlock().getType().equals(Material.AIR)
                        && !loc.getBlock().getType().equals(Material.WATER)
                        && !loc.getBlock().getType().equals(Material.STATIONARY_WATER)
                        && !loc.getBlock().getType().equals(Material.STATIONARY_LAVA)
                        && !loc.getBlock().getType().equals(Material.LONG_GRASS)
                        && !loc.getBlock().getType().equals(Material.DEAD_BUSH)
                        && !loc.getBlock().getType().equals(Material.RED_ROSE)
                        && !loc.getBlock().getType().equals(Material.YELLOW_FLOWER)
                        && !loc.getBlock().getType().equals(Material.DOUBLE_PLANT)
                        && !loc.getBlock().getType().equals(Material.LAVA)) {
                    if (Debug.get(p)) p.sendMessage(Utils.chat("&aHit block, stopping loop"));
                    break;
                }
                Location newloc = new Location(world, loc.getX(), loc.getY() - 0.4, loc.getZ());
                WaterCM.setCooldown(p, 0.2);
                p.getWorld().playEffect(newloc, Effect.WATERDRIP, 1);
                for (Entity entity : p.getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5)) {
                    if (entity instanceof LivingEntity) {
                        if (!(entity instanceof Player && entity.getUniqueId().equals(p.getUniqueId()))) {
                            ((LivingEntity) entity).damage(5, p);
                            t = 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void MoltenFury(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (MoltenFuryCM.checkCooldown(p)) {
            Location loc = p.getEyeLocation();
            World world = p.getWorld();
            double maxLength = 20;
            int t = 0;
            p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 0.2f, 1f);
            MoltenFuryCM.setCooldown(p, 0.2);
            for (double d = 0; d <= maxLength; d += 0.5) {
                if (t == 1) break;
                loc.add(loc.getDirection().multiply(0.5));
                if (!loc.getBlock().getType().equals(Material.AIR)
                        && !loc.getBlock().getType().equals(Material.WATER)
                        && !loc.getBlock().getType().equals(Material.STATIONARY_WATER)
                        && !loc.getBlock().getType().equals(Material.STATIONARY_LAVA)
                        && !loc.getBlock().getType().equals(Material.LONG_GRASS)
                        && !loc.getBlock().getType().equals(Material.DEAD_BUSH)
                        && !loc.getBlock().getType().equals(Material.RED_ROSE)
                        && !loc.getBlock().getType().equals(Material.YELLOW_FLOWER)
                        && !loc.getBlock().getType().equals(Material.DOUBLE_PLANT)
                        && !loc.getBlock().getType().equals(Material.LAVA)) {
                    if (Debug.get(p)) p.sendMessage(Utils.chat("&aHit block, stopping loop"));
                    break;
                }
                Location newloc = new Location(world, loc.getX(), loc.getY() - 0.4, loc.getZ());
                PacketContainer packet = new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES);
                packet.getParticles().write(0, EnumWrappers.Particle.REDSTONE);
                packet.getFloat().write(0, (float)newloc.getX()).write(1, (float)newloc.getY())
                        .write(2, (float)newloc.getZ()).write(3, 2f)
                        .write(4, 1.0f).write(5, 0f).write(6, 0f);
                packet.getIntegers().write(0, 0);

                p.getWorld().getPlayers().forEach(player -> {
                try {
                    ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
                } catch(Exception e){
                    e.printStackTrace();
                }});
                ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
                for (Entity entity : p.getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5)) {
                    if (entity instanceof LivingEntity) {
                        if (!(entity instanceof Player && entity.getUniqueId().equals(p.getUniqueId()))) {
                            ((LivingEntity) entity).damage(8, p);
                            t = 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void Boomerang(Player player) {

        Plugin plugin = Main.getPlugin();

        ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

        Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(10));

        as.setArms(true);
        as.setGravity(false);
        as.setVisible(false);
        as.setItemInHand(ItemManager.Boomerang);
        as.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(120), Math.toRadians(0)));

        NBTItem itemnbt = new NBTItem(player.getItemInHand());

        NBTItem nbti = new NBTItem(ItemManager.BoomerangUsed);
        BoomerangUses.put(itemnbt.getString("UUID"), System.currentTimeMillis());
        // Bukkit.getLogger().log(Level.INFO, "Started w/ random number" + randnum);
        nbti.setString("UUID", itemnbt.getString("UUID"));
        ItemStack i = nbti.getItem();

        player.setItemInHand(i);

        Vector vector = destination.subtract(player.getLocation()).toVector();

        player.playSound(player.getLocation(), Sound.SKELETON_WALK,0.5f, 1.8f);

        new BukkitRunnable(){

            final int distance = 15;
            int i = 0;

            public void run(){
                EulerAngle rot = as.getRightArmPose();
                EulerAngle rotnew =  rot.add( 0, 20, 0);
                as.setRightArmPose(rotnew);

                if(i >= distance){
                    Vector v = destination.subtract(player.getLocation()).toVector();
                    as.teleport(as.getLocation().subtract(vector.normalize()));
                    Location location = as.getLocation().subtract(vector.normalize());
                    if(i >= distance * 2){
                        as.remove();
                        cancel();
                    }
                }
                else{
                    as.teleport(as.getLocation().add(vector.normalize()));
                }

                if (!as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.AIR)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.WATER)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.STATIONARY_WATER)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.STATIONARY_LAVA)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.LONG_GRASS)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.DEAD_BUSH)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.RED_ROSE)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.YELLOW_FLOWER)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.DOUBLE_PLANT)
                        && !as.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.LAVA)) {
                    as.remove();
                    cancel();
                    player.playSound(player.getLocation(), Sound.CLICK,0.5f, 1.5f);
                }

                i++;

                for(Entity entity : as.getLocation().getChunk().getEntities()){
                    if(as.getLocation().distanceSquared(entity.getLocation()) <1 ){
                        if(entity instanceof LivingEntity) {
                            if (entity != player) {
                                LivingEntity livingEntity = (LivingEntity) entity;
                                livingEntity.damage(10, player);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            for (int x = 0; x < player.getInventory().getSize(); x++) {
                ItemStack item = player.getInventory().getItem(x);
                if (item != null) {
                    NBTItem inbt = new NBTItem(item);
                    // Bukkit.getLogger().log(Level.INFO, "Checking w/ random number" + randnum);
                    if (!inbt.getString("UUID").equals("")) {
                        if (inbt.getString("UUID").equals(itemnbt.getString("UUID"))) {
                            NBTItem nbtitem = new NBTItem(ItemManager.Boomerang);
                            if (Debug.get(player)) player.sendMessage(Utils.chat("&aChecked slot &d" + x + "&a, had &d" + item.getItemMeta().getDisplayName() + "&a, UUID: &d" + inbt.getString("UUID")));
                            nbtitem.setString("UUID", itemnbt.getString("UUID"));
                            player.getInventory().remove(player.getInventory().getItem(x));
                            player.getInventory().setItem(x, nbtitem.getItem());
                            // Bukkit.getLogger().log(Level.INFO, "Finished w/ random number" + randnum);
                            break;
                        }
                    }
                }
            }
        }, 30L);
    }
}
