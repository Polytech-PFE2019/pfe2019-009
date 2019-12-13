package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.generator.intermediate.IntermediateQualityGenerator;
import org.polytech.pfe.domego.models.QualityAction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualityAccessor {

    public QualityAccessor() {
    }

    public List<QualityAction> getAllQuality(GameType gameType){
        return getQualityActionsByGameType(gameType);
    }

    public List<QualityAction> getQualityByActivityID(GameType gameType, int activityId){
        List<QualityAction> qualityActions = getQualityActionsByGameType(gameType);
        return qualityActions.stream().filter(qualityAction -> qualityAction.getQualityOfActivityId() == activityId).collect(Collectors.toList());
    }

    public List<QualityCard> getNQualityCardsByActivityID(GameType gameType, int n, int activityId){
        List<QualityCard> copy = getQualityActionsByGameType(gameType).stream().filter(qualityAction -> qualityAction.getQualityOfActivityId() == activityId).map(QualityCard::new).collect(Collectors.toList());
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    private List<QualityAction> getQualityActionsByGameType(GameType gameType){
        if (gameType.equals(GameType.INTERMEDIATE))
            return new IntermediateQualityGenerator().getAllQualityAction();
        return new ArrayList<>();
    }

}