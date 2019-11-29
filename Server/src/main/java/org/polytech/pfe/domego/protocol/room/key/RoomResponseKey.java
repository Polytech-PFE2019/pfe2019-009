package org.polytech.pfe.domego.protocol.room.key;

public enum RoomResponseKey {
    RESPONSE("response"),
    UPDATE("UPDATE"),
    USERNAME("username"),
    READY("ready"),
    ROLEID("roleID"),
    USERID("userID"),
    HOSTID("hostID"),
    ROOMID("roomID"),
    GAMEID("gameID"),
    PLAYERS("players");

    public final String key;

    RoomResponseKey(String key) {
        this.key = key;
    }
}
