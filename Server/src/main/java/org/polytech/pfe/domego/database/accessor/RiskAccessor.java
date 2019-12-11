package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.game.RiskCard;
import org.polytech.pfe.domego.generator.initial.InitialRiskGenerator;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiskAccessor {

    private List<RiskAction> riskActions;

    public RiskAccessor() {
        riskActions = new InitialRiskGenerator().getInitialRiskAction();
    }

    public List<RiskAction> getAllRisk(){
        return riskActions;
    }

    public List<RiskAction> getRiskByActivityID(int activityId){ return riskActions.stream().filter(riskAction -> riskAction.getRiskOfActivityId() == activityId).collect(Collectors.toList());}

    public List<RiskCard> getNRisksCardByActivityID(int n, int activityId){
        List<RiskCard> copy = riskActions.stream().filter(riskAction -> riskAction.getRiskOfActivityId() == activityId).map(riskAction -> new RiskCard(riskAction)).collect(Collectors.toList());
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }


    void initDBRole(){


    }
}
