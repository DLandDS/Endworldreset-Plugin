package me.dlands.endworldreset.settings;

import me.dlands.endworldreset.Endworldreset;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

public class Config {
    private static File file;
    private static FileConfiguration config;
    private static Settings settings;

    public static void setup(){
        file = new File(Endworldreset.getPlugin().getDataFolder(), "config.yml");
        if(!file.exists()){
            try {
                InputStream in = Endworldreset.getPlugin().getResource("config.yml");
                file.mkdirs();
                file.createNewFile();
                Files.copy(in, Paths.get(file.getPath()), new StandardCopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reload();
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save(){
        try {
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            config.set("Save.nextReset", formater.format(settings.getNextReset().getTime()));
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
        settings = new Settings(config);
    }

    public static Settings getSettings() {
        return settings;
    }
}
