package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpHandler;
import dev.liev.mcstats.plugin.SpigotInterface;

public abstract class RequestHandler implements HttpHandler {
    private final String path;
    private final SpigotInterface spigotInterface;


    public RequestHandler(String path, SpigotInterface spigotInterface) {
        this.path = path;
        this.spigotInterface = spigotInterface;
    }


    public String getPath() {
        return path;
    }

    public SpigotInterface getSpigotInterface() {
        return spigotInterface;
    }
}
