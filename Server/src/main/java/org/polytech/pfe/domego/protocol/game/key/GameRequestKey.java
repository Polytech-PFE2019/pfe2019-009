package org.polytech.pfe.domego.protocol.game.key;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;

public enum GameRequestKey implements RequestArgumentKey {

    REQUEST("request"),
    GAMEID("gameID"),
    USERID("userID"),
    AMOUNT("amount"),
    USERNAME("username"),
    ROLEID("roleID"),
    TYPE("type");

    private String key;

    GameRequestKey(String requestKey) {
        this.key = requestKey;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
