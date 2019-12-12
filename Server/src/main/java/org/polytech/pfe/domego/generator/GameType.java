package org.polytech.pfe.domego.generator;

public enum GameType {
    INITIAL("INITIAL"),
    INTERMEDIATE("INTERMEDIATE");

    public String key;

    GameType(String key) {
        this.key = key;
    }
}
