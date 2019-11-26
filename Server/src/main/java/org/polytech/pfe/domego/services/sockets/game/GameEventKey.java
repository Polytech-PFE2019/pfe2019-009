package org.polytech.pfe.domego.services.sockets.game;

public enum GameEventKey {

    JOIN_GAME("JOIN_GAME"),
    BUY_RESOURCES("BUY_RESOURCES"),
    PAY_RESOURCES("PAY_RESOURCES");

    public String key;

    GameEventKey(String key) {
        this.key = key;
    }
}
