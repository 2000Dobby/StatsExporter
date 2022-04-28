package dev.liev.mcstats.plugin;

import dev.liev.mcstats.plugin.api.PlayerManager;
import dev.liev.mcstats.plugin.api.SpigotInterface;
import dev.liev.mcstats.plugin.api.SpigotInterfaceImpl;
import dev.liev.mcstats.plugin.config.Config;
import dev.liev.mcstats.plugin.event.StatsListener;
import dev.liev.mcstats.webserver.StatsServer;
import dev.liev.mcstats.webserver.StatsServerImpl;
import dev.liev.mcstats.webserver.handlers.PlayerStatsHandler;
import dev.liev.mcstats.webserver.handlers.RequestHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;

public class StatsExporter extends JavaPlugin {
    private final StatsServer statsServer;
    private final SpigotInterface spigotInterface;
    private final PlayerManager playerManager;


    public StatsExporter() {
        statsServer = new StatsServerImpl();
        playerManager = new PlayerManager(this);
        spigotInterface = new SpigotInterfaceImpl(playerManager);
    }


    @Override
    public void onEnable() {
        initConfig();
        if (initServer()) {
            getServer().getPluginManager().registerEvents(new StatsListener(playerManager), this);
            getLogger().info("StatsExporter v" + getDescription().getVersion() + " initialised successfully");
        }
    }

    @Override
    public void onDisable() {
        if (statsServer.isRunning()) {
            statsServer.stop();
            getLogger().info("Web API is no longer running");
        }

        getLogger().info("Saving stats, please wait...");
        playerManager.savePlayers();
        getLogger().info("Stats have been saved");
    }

    private void initConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        Config.load(getConfig());
    }

    private boolean initServer() {
        try {
            statsServer.bind(Config.HOSTNAME, Config.PORT, getRequestHandlers());
            if (Config.AUTOSTART) {
                statsServer.start();
                getLogger().info("Web API is now running and accessible under " + Config.HOSTNAME + ":" + Config.PORT);
            }

            return true;
        } catch (IOException e) {
            getLogger().severe("Could not initialise api webserver: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }

        return false;
    }

    private RequestHandler[] getRequestHandlers() {
        ArrayList<RequestHandler> handlers = new ArrayList<>();

        handlers.add(new PlayerStatsHandler("/player", spigotInterface));

        return handlers.toArray(new RequestHandler[0]);
    }
}
