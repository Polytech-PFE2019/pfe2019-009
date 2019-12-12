package org.polytech.pfe.domego.components.game.card;

import org.polytech.pfe.domego.components.business.Game;

public abstract class Card {

    protected boolean isDraw;

    public Card() {
        this.isDraw = false;
    }

    public abstract void doAction(Game game);


    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
