package org.polytech.pfe.domego.components.game.card;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.QualityAction;

import java.util.Objects;

public class QualityCard extends Card {


    private QualityAction qualityAction;

    public QualityCard(QualityAction qualityAction) {
        super();
        this.qualityAction = qualityAction;
    }


    @Override
    public void doAction(Game game) {
        game.getProject().addQuality(qualityAction.getBonus().getAmount());
    }


    public QualityAction getQualityAction() {
        return qualityAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityCard that = (QualityCard) o;
        return Objects.equals(qualityAction, that.qualityAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualityAction);
    }
}
