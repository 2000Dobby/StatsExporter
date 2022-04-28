package dev.liev.mcstats.webserver;

import com.sun.net.httpserver.HttpServer;
import dev.liev.mcstats.webserver.handlers.NotFoundHandler;
import dev.liev.mcstats.webserver.handlers.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class StatsServerImpl implements StatsServer {
    private HttpServer server;


    @Override
    public void bind(String hostname, int port, RequestHandler[] handlers) throws IOException {
        server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        setupPaths(handlers);
    }

    private void setupPaths(RequestHandler[] handlers) {
        for (RequestHandler handler : handlers) {
            server.createContext(handler.getPath(), handler);
        }

        server.createContext("/", new NotFoundHandler());
    }

    @Override
    public void start() {
        if (server != null) {
            server.start();
        }
    }

    @Override
    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }
}
