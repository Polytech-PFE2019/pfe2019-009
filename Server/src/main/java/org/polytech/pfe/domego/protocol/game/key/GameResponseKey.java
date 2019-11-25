package org.polytech.pfe.domego.protocol.game.key;

public enum GameResponseKey {
    RESPONSE("response"),
    RESOURCES("resources"),
    MONEY("money");

    public String key;

    GameResponseKey(String key) {
        this.key = key;
    }
}
