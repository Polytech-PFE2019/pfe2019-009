package org.polytech.pfe.domego.components.game;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.activity.PayResourceType;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.models.risk.Bonus;
import org.polytech.pfe.domego.models.risk.BonusType;
import org.polytech.pfe.domego.models.risk.RiskAction;

import java.util.HashMap;
import java.util.Map;

public class RiskCard {

    private RiskAction riskAction;

    private boolean isDraw;

    public RiskCard(RiskAction riskAction) {
        this.riskAction = riskAction;
        this.isDraw = false;
    }

    public void doAction(Game game){
        for (Bonus bonus : riskAction.getBonusList()) {
            if(bonus.getBonusType().equals(BonusType.RISK))
                doRiskBonusAction(bonus, game);
            else if (bonus.getBonusType().equals(BonusType.DAYS))
                doDaysBonusAction(bonus, game);
            else if (bonus.getBonusType().equals(BonusType.MONEY))
                doMoneyBonusAction(bonus,game);
            else if (bonus.getBonusType().equals(BonusType.RESOURCES))
                doResourcesBonusAction(bonus,game);
        }
        this.isDraw = true;

    }

    public void doRiskBonusAction(Bonus bonus,Game game){

    }

    public void doDaysBonusAction(Bonus bonus,Game game){
        game.getActivities().stream().filter(activity -> activity.getId() == bonus.getActivityID()).findAny().ifPresent(activity -> activity.addDays(bonus.getAmount()));
    }

    public void doMoneyBonusAction(Bonus bonus,Game game){
        game.getPlayers().stream().filter(player -> player.getRole().getName().equals(bonus.getRoleType())).findAny().ifPresent(player -> player.addMoney(bonus.getAmount()));
    }

    public void doResourcesBonusAction(Bonus bonus,Game game){
        Map<Integer, Integer> newMandatoryPayment = new HashMap<>();
        newMandatoryPayment.put(bonus.getAmount(),0);
        game.getActivities().stream().filter(activity -> activity.getId() == bonus.getActivityID()).findAny().ifPresent(activity -> activity.addPayResources(new PayResources(bonus.getRoleType().getId(), newMandatoryPayment, PayResourceType.MANDATORY)));
    }

    public boolean isDraw() {
        return isDraw;
    }

    public RiskAction getRiskAction() {
        return riskAction;
    }
}
