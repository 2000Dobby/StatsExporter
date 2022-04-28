package dev.liev.mcstats.webserver;

import com.sun.net.httpserver.HttpServer;
import dev.liev.mcstats.plugin.StatsExporter;
import dev.liev.mcstats.webserver.handlers.NotFoundHandler;
import dev.liev.mcstats.webserver.handlers.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class StatsServerImpl implements StatsServer {
    private HttpServer server;
    private boolean running;
    private InetSocketAddress socketAddress;
    private RequestHandler[] handlers;
    private final StatsExporter main;


    public StatsServerImpl(StatsExporter main) {
        this.main = main;
    }


    @Override
    public void bind(String hostname, int port, RequestHandler[] handlers) {
        socketAddress = new InetSocketAddress(hostname, port);
        this.handlers = handlers;
    }

    @Override
    public boolean start() {
        if (!running) {
            try {
                server = HttpServer.create(socketAddress, 0);
                server.setExecutor(Executors.newFixedThreadPool(10));
                setupPaths(handlers);

                server.start();
            } catch (IOException e) {
                main.getLogger().severe("Could not initialise api webserver: " + e.getMessage());
                return false;
            }

            running = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean stop() {
        if (server != null) {
            server.stop(0);

            running = false;
            return true;
        }

        return false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private void setupPaths(RequestHandler[] handlers) {
        for (RequestHandler handler : handlers) {
            server.createContext(handler.getPath(), handler);
        }

        server.createContext("/", new NotFoundHandler());
    }
}
