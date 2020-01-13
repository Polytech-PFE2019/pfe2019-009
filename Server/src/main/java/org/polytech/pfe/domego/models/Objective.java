package org.polytech.pfe.domego.models;

public class Objective {

    private int victoryPoints;
    private ObjectiveType type;
    private boolean validated;
    private boolean hasMultiplicity;
    private int amount;

    public Objective(ObjectiveType type,int amount, boolean hasMultiplicity){
        this.validated = false;
        this.type = type;
        this.victoryPoints = 0;
        this.amount = amount;
        this.hasMultiplicity = hasMultiplicity;
    }


    public boolean isValid(int amount){
        validated = amount > 0;
        return validated;
    }

    public boolean isValid(double amount){
        validated = amount > 0;
        return validated;
    }



    //for the doubles objectives
    public boolean isValid(int amount1, int amount2){
        validated = amount1 > 0 && amount2 > 0;
        return validated;
    }

    public void calculateVictoryPoints(int amount) {
        if(!isValid(amount)) {
            victoryPoints = 0;
            return;
        }
        if(hasMultiplicity){
            victoryPoints = this.amount*amount;
        }
        else{
            victoryPoints = this.amount;
        }
    }

    public void calculateVictoryPoints(double amount) {
        if(!isValid(amount)) {
            victoryPoints = 0;
            return;
        }
        if(hasMultiplicity){
            victoryPoints = this.amount*(int)amount;
        }
        else{
            victoryPoints = this.amount;
        }
    }

    // for the double objectives
    // they don't have multiplicity
    public void calculateVictoryPoints(int amount1, int amount2) {
        if(!isValid(amount1, amount2)) {
            victoryPoints = 0;
            return;
        }
        victoryPoints = this.amount;

    }

    public void validate(){
        this.validated = true;
    }


    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ObjectiveType getObjectiveType() {
        return type;
    }

    public boolean isValidated() {
        return validated;
    }

}
