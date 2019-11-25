package org.polytech.pfe.domego.protocol.room.key;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;
import org.polytech.pfe.domego.protocol.game.key.GameRequestKey;

public enum RoomRequestKey implements RequestArgumentKey {
    REQUEST("REQUEST"),
    ROOMID("roomID"),
    USERNAME("username");

    private String key;

    RoomRequestKey(String requestKey) {
        this.key = requestKey;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
