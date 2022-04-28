package dev.liev.mcstats.plugin.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotInterfaceImpl implements SpigotInterface {
    private final PlayerManager playerManager;


    public SpigotInterfaceImpl(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    @Override
    public DummyPlayer getPlayer(String uuid) {
        try {
            DummyPlayer player = playerManager.getPlayer(UUID.fromString(uuid));

            if (player != null) {
                return player;
            }
        } catch (IllegalArgumentException ignored) {}

        return null;
    }
}
