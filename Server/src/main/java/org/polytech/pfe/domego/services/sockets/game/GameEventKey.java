package org.polytech.pfe.domego.services.sockets.game;

public enum GameEventKey {

    JOIN_GAME("JOIN_GAME"),
    BUY_RESOURCES("BUY_RESOURCES"),
    PAY_RESOURCES("PAY_RESOURCES"),
    BANKRUPTCY("BANKRUPTCY"),
    START_NEGOTIATE("START_NEGOTIATE"),
    MSG_NEGOTIATE("MSG_NEGOTIATE"),
    DECLINE_NEGOTIATE("DECLINE_NEGOTIATE"),
    PRICE_NEGOTIATE("PRICE_NEGOTIATE"),
    ESTABLISH_NEGOTIATE("ESTABLISH_NEGOTIATE"),
    END_NEGOTIATE("END_NEGOTIATE"),
    MSG_GROUP_CHAT("MSG_GROUP_CHAT");

    public final String key;

    GameEventKey(String key) {
        this.key = key;
    }
}
