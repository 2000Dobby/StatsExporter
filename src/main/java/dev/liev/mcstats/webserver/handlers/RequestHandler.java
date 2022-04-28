package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.liev.mcstats.plugin.api.SpigotInterface;

import java.io.IOException;
import java.io.OutputStream;

public abstract class RequestHandler implements HttpHandler {
    private final String path;
    private final SpigotInterface spigotInterface;


    public RequestHandler(String path, SpigotInterface spigotInterface) {
        this.path = path;
        this.spigotInterface = spigotInterface;
    }

    static void sendResponse(String responseText, int code, HttpExchange exchange) throws IOException {
        OutputStream response = exchange.getResponseBody();
        exchange.sendResponseHeaders(code, responseText.length());
        response.write(responseText.getBytes());
        response.flush();
        response.close();
    }

    public String getPath() {
        return path;
    }

    public SpigotInterface getSpigotInterface() {
        return spigotInterface;
    }
}
