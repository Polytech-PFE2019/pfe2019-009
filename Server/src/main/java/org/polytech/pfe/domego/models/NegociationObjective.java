package org.polytech.pfe.domego.models;

public class NegociationObjective extends Objective {
    private int minNeeded;
    public NegociationObjective(String description, int amountNeeded, int minNeeded){
        super(description,amountNeeded);
        this.minNeeded = minNeeded;
    }

    @Override
    public boolean testRealised(int amount){
        if(amount>=minNeeded && amount <= this.getAmountNeeded()){
            this.setRealised(true);
        }
        return this.isRealised();
    }
}
