package org.polytech.pfe.domego.protocol.room;

public enum RoomRequestKey {
    REQUEST("REQUEST"),
    USERNAME("username");

    public String key;

    RoomRequestKey(String requestKey) {
        this.key = requestKey;
    }
}
