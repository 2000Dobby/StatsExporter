package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class NotFoundHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream response = exchange.getResponseBody();
        String responseString = "{Not found}";

        exchange.sendResponseHeaders(404, responseString.length());
        response.write(responseString.getBytes());
        response.flush();
        response.close();
    }

}
