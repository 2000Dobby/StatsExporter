package dev.liev.mcstats.plugin.event;

import dev.liev.mcstats.plugin.api.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatsListener implements Listener {
    private final PlayerManager playerManager;


    public StatsListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        playerManager.onJoin(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        playerManager.onLeave(e.getPlayer().getUniqueId());
    }
}
