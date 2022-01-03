package me.oglass.hotslicerrpg.enums;

import javax.annotation.Nullable;

public enum PlayerStat {
    Health("HEALTH", 0),
    MaxHealth("MAX_HEALTH", 1),
    Defense("DEFENSE", 2),
    Energy("ENERGY", 3),
    MaxEnergy("MAX_ENERGY", 4),
    MagicDamage("MAGIC_DAMAGE", 5),
    Strength("STRENGTH", 6);


    private final String name;
    private final Integer id;
    PlayerStat(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public Integer getId() {
        return this.id;
    }

    @Nullable
    public static PlayerStat getPlayerStat(String name) {
        for (PlayerStat stat : values()) {
            if (stat.getName().equalsIgnoreCase(name)) return stat;
        }
        return null;
    }

    @Nullable
    public static PlayerStat getPlayerStat(Integer id) {
        for (PlayerStat stat : values()) {
            if (stat.getId().equals(id)) return stat;
        }
        return null;
    }
}
