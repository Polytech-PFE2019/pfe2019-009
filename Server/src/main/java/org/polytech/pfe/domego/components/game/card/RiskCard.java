package org.polytech.pfe.domego.components.game.card;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.database.accessor.RiskAccessor;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.PayResourceType;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.models.risk.Bonus;
import org.polytech.pfe.domego.models.risk.BonusType;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.polytech.pfe.domego.protocol.game.UpdatePaymentGameEvent;

import java.util.*;
import java.util.stream.Collectors;

public class RiskCard extends Card {

    private RiskAction riskAction;

    public RiskCard(RiskAction riskAction) {
        super();
        this.riskAction = riskAction;
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

    private void doRiskBonusAction(Bonus bonus,Game game){
        List<RiskCard> riskCards = new RiskAccessor().getRiskByActivityID(game.getGameType(), bonus.getActivityID()).stream().map(RiskCard::new).collect(Collectors.toList());
        Collections.shuffle(riskCards);
        game.getActivities().stream().filter(activity -> activity.getId() == bonus.getActivityID()).findAny().
                ifPresent(activity -> {
                    if(activity.getRiskCardList().contains(riskCards.get(0)))
                        doRiskBonusAction(bonus, game);
                    else
                        activity.addRisk(riskCards.get(0));

                });
    }

    private void doDaysBonusAction(Bonus bonus,Game game){
        game.getActivities().stream().filter(activity -> activity.getId() == bonus.getActivityID()).findAny().ifPresent(activity -> activity.addDays(bonus.getAmount()));
    }

    private void doMoneyBonusAction(Bonus bonus,Game game){
        game.getPlayers().stream().filter(player -> player.getRole().getName().equals(bonus.getRoleType())).findAny().ifPresent(player -> player.addMoney(bonus.getAmount()));
    }

    private void doResourcesBonusAction(Bonus bonus,Game game){
        Map<Integer, Integer> newMandatoryPayment = new HashMap<>();
        newMandatoryPayment.put(bonus.getAmount(),0);
        game.getActivities().stream().filter(activity -> activity.getId() == bonus.getActivityID()).findAny().ifPresent(activity -> activity.addPayResources(new PayResources(bonus.getRoleType().getId(), newMandatoryPayment, PayResourceType.MANDATORY, true)));
        Activity currentActivity = game.getCurrentActivity();
        if (currentActivity.getId() == bonus.getActivityID()){
            Player player = game.getPlayerByRoleID(bonus.getRoleType().getId()).orElseThrow();
            currentActivity.payResources(player, Arrays.asList(new Payment(bonus.getAmount(), PayResourceType.MANDATORY)));
            new UpdatePaymentGameEvent(game, player).processEvent();
        }
    }


    public RiskAction getRiskAction() {
        return riskAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskCard riskCard = (RiskCard) o;
        return Objects.equals(riskAction, riskCard.riskAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskAction);
    }
}
