package me.oglass.hotslicerrpg.files;

import me.oglass.hotslicerrpg.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class CustomItems extends AbstractFile {

    public CustomItems(Main main) {
        super(main, "CustomItems.yml");
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
