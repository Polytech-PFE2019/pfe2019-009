package org.polytech.pfe.domego.models;

public class Objective {


    private String description;
    private int amountNeeded;
    private boolean realised = false;

    public Objective(String description, int amountNeeded){
        this.description = description;
        this.amountNeeded = amountNeeded;
    }

    public boolean testRealised(int currentAmount){
        if(currentAmount >= amountNeeded){
            this.realised = true;
        }
        return this.realised;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public boolean isRealised() {
        return realised;
    }

    public void setRealised(boolean realised){
        this.realised = realised;
    }
}
