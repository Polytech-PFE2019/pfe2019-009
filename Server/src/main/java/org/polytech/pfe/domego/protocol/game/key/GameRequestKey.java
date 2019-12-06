package org.polytech.pfe.domego.protocol.game.key;

import org.polytech.pfe.domego.protocol.RequestArgumentKey;

public enum GameRequestKey implements RequestArgumentKey {

    REQUEST("request"),
    GAMEID("gameID"),
    USERID("userID"),
    AMOUNT("amount"),
    USERNAME("username"),
    PAYMENTS("payments"),
    ROLEID("roleID"),
    TYPE("type"),
    RECEIVERID("receiverID"),
    GIVERID("giverID"),
    MESSAGE("message"),
    DECLINETYPE("declineType"),
    NEGOTIATIONID("negotiationID");


    private String key;

    GameRequestKey(String requestKey) {
        this.key = requestKey;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
