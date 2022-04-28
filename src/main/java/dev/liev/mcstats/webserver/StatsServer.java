package dev.liev.mcstats.webserver;

import dev.liev.mcstats.webserver.handlers.RequestHandler;

import java.io.IOException;

public interface StatsServer {
    void bind(String hostname, int port, RequestHandler[] handlers);
    boolean start();
    boolean stop();

    boolean isRunning();
}
