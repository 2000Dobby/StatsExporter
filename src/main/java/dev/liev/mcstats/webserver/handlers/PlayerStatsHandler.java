package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import dev.liev.mcstats.plugin.SpigotInterface;

import java.io.IOException;
import java.io.OutputStream;

public class PlayerStatsHandler extends RequestHandler {

    public PlayerStatsHandler(String path, SpigotInterface spigotInterface) {
        super(path, spigotInterface);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream response = exchange.getResponseBody();
        String responseString = "{2kDobby}";

        exchange.sendResponseHeaders(200, responseString.length());
        response.write(responseString.getBytes());
        response.flush();
        response.close();
    }

}
