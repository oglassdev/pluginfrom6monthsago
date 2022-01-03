package me.oglass.hotslicerrpg.files;

import me.oglass.hotslicerrpg.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerData extends AbstractFile {

    public PlayerData(Main main) {
        super(main, "PlayerData.yml");
    }

    public FileConfiguration getConfig() {
        return config;
    }

}
