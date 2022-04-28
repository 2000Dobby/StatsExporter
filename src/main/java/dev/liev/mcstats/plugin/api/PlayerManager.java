package dev.liev.mcstats.plugin.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.liev.mcstats.plugin.StatsExporter;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private final HashMap<UUID, DummyPlayerOnline> onlinePlayers;
    private final HashMap<UUID, DummyPlayerOffline> offlinePlayers;
    private final StatsExporter main;


    public PlayerManager(StatsExporter main) {
        onlinePlayers = new HashMap<>();
        offlinePlayers = new HashMap<>();
        this.main = main;
    }


    public void onJoin(Player player) {
        offlinePlayers.remove(player.getUniqueId());
        onlinePlayers.put(player.getUniqueId(),  new DummyPlayerOnline(player));
    }

    public void onLeave(UUID uuid) {
        DummyPlayerOnline onlinePlayer = onlinePlayers.remove(uuid);
        offlinePlayers.put(uuid, onlinePlayer.getOfflinePlayer());
    }

    public DummyPlayer getPlayer(UUID uuid) {
        DummyPlayer player = onlinePlayers.get(uuid);

        if (player == null) {
            player = offlinePlayers.get(uuid);

            if (player == null) {
                player = loadPlayer(uuid);
            }
        }

        return player;
    }

    public DummyPlayerOffline loadPlayer(UUID uuid) {
        File file = new File("plugins/StatsExporter/players/" + uuid + ".json");
        if (!file.exists()) {
            return null;
        }

        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();

            DummyPlayerOffline offlinePlayer = new DummyPlayerOffline().fromJson(object);
            offlinePlayers.put(uuid, offlinePlayer);

            reader.close();
            return offlinePlayer;
        } catch (Exception e) {
            main.getLogger().warning("Could not load stats file for UUID " + uuid + ": " + e.getMessage());
            return null;
        }
    }

    public void savePlayers() {
        for (UUID uuid : onlinePlayers.keySet()) {
            onLeave(uuid);
        }

        for (DummyPlayerOffline player : offlinePlayers.values()) {
            savePlayer(player);
        }
    }

    public void savePlayer(DummyPlayerOffline player) {
        String filePath = "plugins/StatsExporter/players/" + player.getUniqueId() + ".json";
        File file = new File(filePath);

        try {
            if (file.exists()) {
                if (!file.delete()) {
                    main.getLogger().warning("Could not delete old stats file for UUID " + player.getUniqueId());
                    return;
                }
                file = new File(filePath);
            }

            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                main.getLogger().warning("Could not create stats file for UUID " + player.getUniqueId());
                return;
            }

            Gson gson = new Gson();
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            gson.toJson(player.getJson(), writer);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            main.getLogger().warning("Could not save stats file for UUID " + player.getUniqueId() + ": " + e.getMessage());
        }
    }
}
