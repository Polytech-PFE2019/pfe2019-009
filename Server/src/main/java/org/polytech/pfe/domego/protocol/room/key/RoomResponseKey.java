package org.polytech.pfe.domego.protocol.room.key;

public enum RoomResponseKey {
    RESPONSE("response"),
    UPDATE("update"),
    USERNAME("username"),
    READY("ready"),
    ROLEID("roleID"),
    USERID("userID"),
    ROOMID("roomID"),
    PLAYERS("players");

    public String key;

    RoomResponseKey(String key) {
        this.key = key;
    }
}
