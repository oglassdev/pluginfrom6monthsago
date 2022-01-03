package me.oglass.hotslicerrpg;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.oglass.hotslicerrpg.classes.ClassManager;
import me.oglass.hotslicerrpg.commands.*;
import me.oglass.hotslicerrpg.cooldown.*;
import me.oglass.hotslicerrpg.files.CustomBlockFile;
import me.oglass.hotslicerrpg.files.CustomItems;
import me.oglass.hotslicerrpg.files.PlayerData;
import me.oglass.hotslicerrpg.items.*;
import me.oglass.hotslicerrpg.listeners.*;
import me.oglass.hotslicerrpg.mobs.MobHealthManager;
import me.oglass.hotslicerrpg.packets.BreakAnimation;
import me.oglass.hotslicerrpg.packets.ProtocolLibPackets;
import me.oglass.hotslicerrpg.playerstats.*;
import me.oglass.hotslicerrpg.utils.BlockBox;
import me.oglass.hotslicerrpg.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {
    private static Main plugin;
    public CustomItems customItems;
    public CustomBlockFile customBlockFile;
    public me.oglass.hotslicerrpg.files.PlayerData playerData;
    public ProtocolManager protocolManager;
    public TablistManager tablistManager;

    @Override
    public void onEnable() {
        plugin = this;
        ItemManager.init();
        DropsManager.init();
        Admin.init();
        Debug.init();
        protocolManager = ProtocolLibrary.getProtocolManager();
        CustomCraftingRecipes.init();
        /*for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getPassenger() != null && entity.getPassenger() instanceof ArmorStand) {
                    if (entity.getPassenger().getPassenger() != null && entity.getPassenger().getPassenger() instanceof ArmorStand) {
                        if (entity instanceof LivingEntity) {
                            double mh = ((LivingEntity) entity).getMaxHealth();
                            String name = null;
                            try {
                                mh = Integer.parseInt(entity.getPassenger().getName().split("/")[1]);
                            } catch (Exception ignored) { }
                            try {
                                name = entity.getPassenger().getPassenger().getName().split("/")[1];
                            } catch (Exception ignored) { }
                            entity.getPassenger().getPassenger().remove();
                            entity.getPassenger().remove();
                            if (name != null) MobHealthManager.addEntity(entity, mh, name, 50);
                            else MobHealthManager.addEntity(entity, mh);
                        }
                    }
                }
            }
        }*/


        protocolManager.addPacketListener(new PacketAdapter(this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.BLOCK_DIG) {
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

        this.tablistManager = new TablistManager(this);

        tablistManager.addHeader("&6&lHotslicer RPG\n");
        tablistManager.addHeader("&e&lHotslicer RPG\n");
        // tablistManager.addFooter("&c&lowogus");

        tablistManager.showTab();

        // Boring Config Stuff
        saveDefaultConfig();
        this.customItems = new CustomItems(this);
        this.playerData = new PlayerData(this);
        this.customBlockFile = new CustomBlockFile(this);

        this.customBlockFile.getConfig().getKeys(false).forEach(key -> {
            // Bukkit.getLogger().log(Level.INFO, key); // debug
            String[] things = key.split("`");
            if (Bukkit.getWorld(things[3]) != null) {
                // Bukkit.getLogger().log(Level.INFO, Arrays.toString(things)); // debug
                Location loc = new Location(Bukkit.getWorld(things[3]), Integer.parseInt(things[0]), Integer.parseInt(things[1]), Integer.parseInt(things[2]));
                // Bukkit.getLogger().log(Level.INFO, loc.toString()); // debug
                CustomBlock customBlock = new CustomBlock(loc.getBlock());
                CustomBlockData customBlockData = customBlock.getData();
                this.customBlockFile.getConfig().getConfigurationSection(key + ".CustomBlockData").getKeys(false).forEach(yek -> {
                    // Bukkit.getLogger().log(Level.INFO, yek + ": " + this.customBlockFile.getConfig().getString(key + ".CustomBlockData." + yek)); // debug
                    if (this.customBlockFile.getConfig().get(key + ".CustomBlockData." + yek) instanceof String) {

                        customBlockData.setString(yek, this.customBlockFile.getConfig().getString(key + ".CustomBlockData." + yek));
                        // Bukkit.getLogger().log(Level.INFO, yek + this.customBlockFile.getConfig().getString(key + ".CustomBlockData." + yek));
                    } else if (this.customBlockFile.getConfig().get(key + ".CustomBlockData." + yek) instanceof Integer)
                        customBlockData.setInteger(yek, this.customBlockFile.getConfig().getInt(key + ".CustomBlockData." + yek));
                    else if (this.customBlockFile.getConfig().get(key + ".CustomBlockData." + yek) instanceof Double)
                        customBlockData.setDouble(yek, this.customBlockFile.getConfig().getDouble(key + ".CustomBlockData." + yek));
                    else if (this.customBlockFile.getConfig().get(key + ".CustomBlockData." + yek) instanceof Boolean)
                        customBlockData.setBoolean(yek, this.customBlockFile.getConfig().getBoolean(key + ".CustomBlockData." + yek));
                });
                customBlock.setData(customBlockData);
            }
        });
        if (this.playerData.getConfig().contains("MinesLocation1") && this.playerData.getConfig().contains("MinesLocation2")) {
            Location MinesLocation1 = (Location) this.playerData.getConfig().get("MinesLocation1");
            Location MinesLocation2 = (Location) this.playerData.getConfig().get("MinesLocation2");
            BlockBox.Blocks(MinesLocation1, MinesLocation2, "MiningBox");
            Bukkit.getLogger().log(Level.INFO, Utils.chat("&aLoaded Mines Location Data"));
        }
        ClassManager.setupClasses();

        // Listeners
        new JoinListener(this);
        new RightClickListener(this);
        new InventoryClickListener(this);
        new EntityDamageListener(this);
        new EntityHealListener(this);
        new PlayerFishListener(this);
        new ArmorStandListener(this);
        new ItemPickupListener(this);
        new ItemDropListener(this);
        new ItemMoveListener(this);
        new EntitySpawn(this);
        new InventoryCloseListener(this);
        new PlayerChatListener(this);
        new BlockBreakListener(this);
        new BlockPlaceListener(this);
        new EntityTeleportListener(this);
        Bukkit.getPluginManager().registerEvents(this, this);

        // Stats
        ActionBarCM.setupCooldown();
        ActionBar.setupActionBar();
        ArmorStatsManager.setupArmorStats();

        // Cooldowns
        WitherCM.setupCooldown();
        WaterCM.setupCooldown();
        BoomCM.setupCooldown();
        GrappleCM.setupCooldown();
        MoltenFuryCM.setupCooldown();

        // Commands
        new GiveCustomItems(this);
        new NightVisionToggle(this);
        new RPGMenu(this);
        new Settings(this);
        new AddXP(this);
        new GetLevel(this);
        new CustomCrafting(this);
        new SetClass(this);
        new AdminMenu(this);
        new DebugCMD(this);
        new giveChestItems(this);
        new MiningWand(this);
        new MiningToggle(this);
        new setWarpLocation(this);
        new CrystalWand(this);
        new itemc(this);
        new SpawnEntity(this);

        // Misc
        ItemAbilities.setupMaps();

        Bukkit.getLogger().log(Level.INFO, this.getConfig().getString("StartingMessage"));

    }
    @Override
    public void onDisable() {
        customItems.save();
        if (BlockBox.Locations.containsKey(0) && BlockBox.Locations.containsKey(1)) {
            this.playerData.getConfig().set("MinesLocation1", BlockBox.Locations.get(0));
            this.playerData.getConfig().set("MinesLocation2", BlockBox.Locations.get(1));
            Bukkit.getLogger().log(Level.INFO, "Saved Mines Location Data");
        } else Bukkit.getLogger().log(Level.INFO, "Could NOT Save Mines Location Data");
        for (Map.Entry<String, String> entry : ClassManager.Players.entrySet()) {
            this.playerData.getConfig().set("PlayerData." + entry.getKey() + ".Classes.Class", entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : ClassManager.KnightXP.entrySet()) {
            this.playerData.getConfig().set("PlayerData." + entry.getKey() + ".Classes.Knight.XP", entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : ClassManager.ArcherXP.entrySet()) {
            this.playerData.getConfig().set("PlayerData." + entry.getKey() + ".Classes.Archer.XP", entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : ClassManager.HealerXP.entrySet()) {
            this.playerData.getConfig().set("PlayerData." + entry.getKey() + ".Classes.Healer.XP", entry.getValue());
        }
        for (Map.Entry<UUID, Boolean> entry : Debug.Debug.entrySet()) {
            this.playerData.getConfig().set("PlayerData." + entry.getKey().toString() + ".Debug", entry.getValue());
        }
        for (CustomBlock customBlock : CustomBlock.getBlocks()) {
            String str = customBlock.getLocation().getBlockX() + "`" + customBlock.getLocation().getBlockY() + "`" + customBlock.getLocation().getBlockZ() + "`" + customBlock.getLocation().getWorld().getName();
            for (String string : customBlock.getData().getKeys()) {
                if (customBlock.getData().getDataMap().get(string) instanceof String) this.customBlockFile.getConfig().set(str + ".CustomBlockData." + string, customBlock.getData().getString(string));
                else if (customBlock.getData().getDataMap().get(string) instanceof Integer) this.customBlockFile.getConfig().set(str + ".CustomBlockData." + string, customBlock.getData().getInteger(string));
                else if (customBlock.getData().getDataMap().get(string) instanceof Double) this.customBlockFile.getConfig().set(str + ".CustomBlockData." + string, customBlock.getData().getDouble(string));
                else if (customBlock.getData().getDataMap().get(string) instanceof Boolean) this.customBlockFile.getConfig().set(str + ".CustomBlockData." + string, customBlock.getData().getBoolean(string));
            }
        }
        customBlockFile.save();
        playerData.save();
    }

    public static Main getPlugin() {
        return plugin;
    }
}
