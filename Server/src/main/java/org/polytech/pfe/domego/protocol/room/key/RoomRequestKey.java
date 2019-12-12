package org.polytech.pfe.domego.protocol.room.key;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;

public enum RoomRequestKey implements RequestArgumentKey {
    REQUEST("REQUEST"),
    ROOMID("roomID"),
    USERID("userID"),
    GAME_TYPE("gameType"),
    DAYS("days"),
    COST("cost"),
    ROLEID("roleID"),
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
