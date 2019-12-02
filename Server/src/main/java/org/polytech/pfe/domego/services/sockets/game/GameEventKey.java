package org.polytech.pfe.domego.services.sockets.game;

public enum GameEventKey {

    JOIN_GAME("JOIN_GAME"),
    BUY_RESOURCES("BUY_RESOURCES"),
    PAY_RESOURCES("PAY_RESOURCES"),
    START_NEGOCIATION("START_NEGOCIATION"),
    END_NEGOCIATION("END_NEGOCIATION");

    public final String key;

    GameEventKey(String key) {
        this.key = key;
    }
}
