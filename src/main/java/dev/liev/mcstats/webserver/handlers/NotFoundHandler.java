package dev.liev.mcstats.webserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.liev.mcstats.webserver.repsonses.ErrorResponse;

import java.io.IOException;
import java.io.OutputStream;

public class NotFoundHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String responseText = ErrorResponse.getJson("not found", "The requested path could not be found", exchange.getRequestURI().toString(), 404);
        RequestHandler.sendResponse(responseText, 404, exchange);
    }

}
