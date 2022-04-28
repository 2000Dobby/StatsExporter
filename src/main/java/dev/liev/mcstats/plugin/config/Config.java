package dev.liev.mcstats.plugin.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static String HOSTNAME;
    public static int PORT;
    public static boolean AUTOSTART = true;


    public static void load(FileConfiguration fileConfig) {
        HOSTNAME = fileConfig.getString("hostname", "localhost");
        PORT = fileConfig.getInt("port", 8000);
        AUTOSTART = fileConfig.getBoolean("autostart", true);
    }
}
