package dev.liev.mcstats.plugin.api;

import com.google.gson.JsonObject;

public class DummyPlayerOffline extends DummyPlayer {
    private String name;
    private String uniqueId;
    private String displayName;
    private String lastSeen;
    private boolean op;
    private boolean allowFlight;


    public DummyPlayerOffline fromJson(JsonObject object) {
        name = object.get("name").getAsString();
        uniqueId = object.get("uuid").getAsString();
        displayName = object.get("displayName").getAsString();
        lastSeen = object.get("lastSeen").getAsString();
        op = object.get("op").getAsBoolean();
        allowFlight = object.get("canFly").getAsBoolean();

        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getLastSeen() {
        return lastSeen;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public boolean isOp() {
        return op;
    }

    @Override
    public boolean getAllowFlight() {
        return allowFlight;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setOp(boolean op) {
        this.op = op;
    }

    public void setAllowFlight(boolean allowFlight) {
        this.allowFlight = allowFlight;
    }
}
