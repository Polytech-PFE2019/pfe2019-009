package org.polytech.pfe.domego.services.sockets.game;

public enum GameEventKey {

    BUY_RESOURCES("BUY_RESOURCES"),
    PAY_RESOURCES("PAY_RESOURCES");

    public String key;

    GameEventKey(String key) {
        this.key = key;
    }
}
