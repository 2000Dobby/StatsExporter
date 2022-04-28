package dev.liev.mcstats.plugin.api;

import com.google.gson.JsonObject;

public abstract class DummyPlayer {
    public abstract String getName();
    public abstract String getUniqueId();
    public abstract String getDisplayName();
    public abstract String getLastSeen();

    public abstract boolean isOnline();
    public abstract boolean isOp();
    public abstract boolean getAllowFlight();

    public JsonObject getJson() {
        JsonObject object = new JsonObject();

        object.addProperty("name", getName());
        object.addProperty("uuid", getUniqueId());
        object.addProperty("displayName", getDisplayName());
        object.addProperty("online", isOnline());
        object.addProperty("lastSeen", getLastSeen());
        object.addProperty("op", isOp());
        object.addProperty("canFly", getAllowFlight());

        return object;
    }
}
