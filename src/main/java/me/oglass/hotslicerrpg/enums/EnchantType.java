package me.oglass.hotslicerrpg.enums;

import javax.annotation.Nullable;

public enum EnchantType {
    PROTECTION(0, "PROTECTION"),
    GROWTH(1, "GROWTH"),
    DEPTH_STRIDER(2, "DEPTH_STRIDER", 3),
    FORTUNE(3, "FORTUNE"),
    EFFICIENCY(4, "EFFICIENCY");

    private final Integer id;
    private final Integer maxLevel;
    private final String name;

    EnchantType(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.maxLevel = 0;
    }
    EnchantType(Integer id, String name, Integer maxLevel) {
        this.id = id;
        this.name = name;
        this.maxLevel = maxLevel;
    }

    @Nullable
    public static EnchantType getEnchant(String name) {
        for (EnchantType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) return type;
        }
        return null;
    }

    @Nullable
    public static EnchantType getEnchant(Integer id) {
        for (EnchantType type : values()) {
            if (type.getId().equals(id)) return type;
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
    public Integer getId() {
        return this.id;
    }
    public Integer getMaxLevel() {
        return this.maxLevel;
    }
}
