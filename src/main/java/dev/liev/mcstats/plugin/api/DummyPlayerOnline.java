package dev.liev.mcstats.plugin.api;

import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class DummyPlayerOnline extends DummyPlayer {
    private final Player player;


    public DummyPlayerOnline(Player player) {
        this.player = player;
    }


    public DummyPlayerOffline getOfflinePlayer() {
        DummyPlayerOffline offlinePlayer = new DummyPlayerOffline();

        offlinePlayer.setName(getName());
        offlinePlayer.setUniqueId(getUniqueId());
        offlinePlayer.setDisplayName(getDisplayName());
        offlinePlayer.setLastSeen(getLastSeen());
        offlinePlayer.setOp(isOp());
        offlinePlayer.setAllowFlight(getAllowFlight());

        return offlinePlayer;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public String getUniqueId() {
        return player.getUniqueId().toString();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public String getLastSeen() {
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public boolean getAllowFlight() {
        return player.getAllowFlight();
    }
}
