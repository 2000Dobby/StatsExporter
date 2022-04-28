package dev.liev.mcstats.webserver.repsonses;

import com.google.gson.JsonObject;

import java.sql.Timestamp;

public class ErrorResponse {
    public static String getJson(String error, String message, String path, int code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        JsonObject object = new JsonObject();
        object.addProperty("timestamp", timestamp.toString());
        object.addProperty("status", code);
        object.addProperty("error", error);
        object.addProperty("message", message);
        object.addProperty("path", path);

        return object.toString();
    }
}
