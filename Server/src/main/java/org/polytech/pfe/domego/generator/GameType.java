package org.polytech.pfe.domego.generator;

public enum GameType {
    INITIAL("INITIAL"),
    INTERMEDIATE("INTERMEDIATE");

    private final String key;

    GameType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
