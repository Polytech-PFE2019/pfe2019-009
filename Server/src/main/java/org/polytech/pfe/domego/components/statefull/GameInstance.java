package org.polytech.pfe.domego.components.statefull;

public class GameInstance {

    private GameInstance() {
    }

    private static GameInstance INSTANCE = new GameInstance();

    public static GameInstance getInstance() {
        return INSTANCE;
    }
}
