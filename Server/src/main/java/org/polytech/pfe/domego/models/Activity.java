package org.polytech.pfe.domego.models;

import java.util.List;

public class Activity {


    private String description;
    private int numberOfDays;
    private List<RiskCard> riskCardList;
    private int stepNumber;

    public Activity(int stepNumber, String description, int numberOfDays, List<RiskCard> riskCardList){
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.riskCardList = riskCardList;
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public List<RiskCard> getRiskCardList() {
        return riskCardList;
    }

    public int getStepNumber() {
        return stepNumber;
    }

}
