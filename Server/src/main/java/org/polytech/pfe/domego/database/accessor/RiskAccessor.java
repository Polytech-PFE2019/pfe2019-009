package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.generator.initial.InitialRiskGenerator;
import org.polytech.pfe.domego.generator.intermediate.IntermediateRiskGenerator;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiskAccessor {


    public RiskAccessor() {
    }

    public List<RiskAction> getAllRisk(GameType gameType){
        return getRiskActionsByGameType(gameType);
    }

    public List<RiskAction> getRiskByActivityID(GameType gameType, int activityId){
        List<RiskAction> riskActions = getRiskActionsByGameType(gameType);
        return riskActions.stream().filter(riskAction -> riskAction.getRiskOfActivityId() == activityId).collect(Collectors.toList());
    }

    public List<RiskCard> getNRisksCardByActivityID(GameType gameType, int n, int activityId){
        List<RiskCard> copy = getRiskActionsByGameType(gameType).stream().filter(riskAction -> riskAction.getRiskOfActivityId() == activityId).map(riskAction -> new RiskCard(riskAction)).collect(Collectors.toList());
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    private List<RiskAction> getRiskActionsByGameType(GameType gameType){
        if (gameType.equals(GameType.INITIAL))
            return new InitialRiskGenerator().getInitialRiskAction();
        else if (gameType.equals(GameType.INTERMEDIATE))
            return new IntermediateRiskGenerator().getInitialRiskAction();
        return new ArrayList<>();
    }

}
