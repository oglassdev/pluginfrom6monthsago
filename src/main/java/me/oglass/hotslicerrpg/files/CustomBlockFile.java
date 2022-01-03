package me.oglass.hotslicerrpg.files;

import me.oglass.hotslicerrpg.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class CustomBlockFile extends AbstractFile {

    public CustomBlockFile(Main main) {
        super(main, "BlockData.yml");
    }

    public FileConfiguration getConfig() {
        return config;
    }

}
