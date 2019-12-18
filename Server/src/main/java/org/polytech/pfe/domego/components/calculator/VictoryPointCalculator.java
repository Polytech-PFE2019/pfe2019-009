package org.polytech.pfe.domego.components.calculator;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.RoleType;

public class VictoryPointCalculator {

    private Game game;

    public VictoryPointCalculator(Game game) {
        this.game = game;
    }

    public void calculateObjectivesVictoryPoints(Player player){
        int deltaDelay = this.calculateDelayDelta();
        int deltaRisks = this.calculateRiskDelta();
        int deltaCosts = this.calculateCostDelta();
        player.getObjectiveList().forEach(objective -> {
            switch (objective.getObjectiveType()) {
                case MONEY:
                    objective.calculateVictoryPoints(calculateBenefit(player));
                    break;
                case DAYS:
                    objective.calculateVictoryPoints(deltaDelay);
                    break;
                case RISK:
                    objective.calculateVictoryPoints(deltaRisks);
                    break;
                case BUDGET:
                    objective.calculateVictoryPoints(deltaCosts);
                    break;
                case DAYS_AND_BUDGET:
                    objective.calculateVictoryPoints(deltaDelay,deltaCosts);
                    break;
                default:
                    break;
            }

            player.addVictoryPoint(objective.getVictoryPoints());
        });
    }

    private double calculateBenefit(Player player){
        int initialMoney;
        if (player.getRole().getName().equals(RoleType.MAITRE_D_OUVRAGE))
            initialMoney = player.getRole().getBudget() - game.getProject().getCostWanted();
        else
            initialMoney = player.getRole().getBudget();

        return - initialMoney + player.getMoney();
    }

    private int calculateDelayDelta(){
        return this.game.getProject().getNumberOfDaysWanted() - this.game.getProject().getDays();
    }

    private int calculateCostDelta(){
        return this.game.getProject().getCostWanted() - this.game.getProject().getCost();
    }

    private int calculateRiskDelta(){
        return this.game.getProject().getNumberOfRisksDrawnWanted() - this.game.getProject().getRisks();
    }
}
