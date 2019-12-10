package org.polytech.pfe.domego.components.calculator;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.Player;

public class VictoryPointCalculator {

    private Game game;

    public VictoryPointCalculator(Game game) {
        this.game = game;
    }

    public void calculateObjectivesVictoryPoints(Player player){
        System.out.println("DELTA DELAY : " + game.getDelayDelta());
        System.out.println("DELTA RISK : " + game.getRisksDelta());
        System.out.println("DELTA BUDGET : " + game.getBudgetDelta());
        player.getObjectiveList().forEach(objective -> {
            switch (objective.getObjectiveType()) {
                case MONEY:
                    objective.calculateVictoryPoints(calculateBenefit(player));
                    break;
                case DAYS:
                    objective.calculateVictoryPoints(game.getDelayDelta());
                    break;
                case RISK:
                    objective.calculateVictoryPoints(game.getRisksDelta());
                    break;
                case BUDGET:
                    objective.calculateVictoryPoints(game.getBudgetDelta());
                    break;
                case DAYS_AND_BUDGET:
                    objective.calculateVictoryPoints(game.getDelayDelta(),game.getBudgetDelta());
                    break;
                default:
                    break;
            }

            player.addVictoryPoint(objective.getVictoryPoints());
        });
    }

    private double calculateBenefit(Player player){
        double initialMoney = player.getRole().getBudget();

        return - initialMoney + player.getMoney();
    }
}
