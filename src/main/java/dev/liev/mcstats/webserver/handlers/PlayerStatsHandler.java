package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import dev.liev.mcstats.plugin.api.DummyPlayer;
import dev.liev.mcstats.plugin.api.SpigotInterface;
import dev.liev.mcstats.webserver.repsonses.ErrorResponse;

import java.io.IOException;

public class PlayerStatsHandler extends RequestHandler {
    public PlayerStatsHandler(String path, SpigotInterface spigotInterface) {
        super(path, spigotInterface);
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().toString();
        String[] urlArguments = path.split("/");

        if (urlArguments.length > 2) {
            DummyPlayer player = getSpigotInterface().getPlayer(urlArguments[2]);

            if (player != null) {
                sendResponse(player.getJson().toString(), 200, exchange);
            }
        }

        sendResponse(ErrorResponse.getJson("not found", "There is no player matching the given uuid", path, 200), 200, exchange);
    }
}
