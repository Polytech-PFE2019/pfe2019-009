package org.polytech.pfe.domego.models;

public class Project {
    private int cost;
    private int days;
    private int risks;
    private int costWanted;
    private int numberOfDaysWanted;
    private int numberOfRisksDrawnWanted;

    public Project(int costWanted, int numberOfDaysWanted, int numberOfRisksDrawnWanted){
        this.cost = 0;
        this.days = 0;
        this.risks = 0;
        this.costWanted = costWanted;
        this.numberOfDaysWanted = numberOfDaysWanted;
        this.numberOfRisksDrawnWanted = numberOfRisksDrawnWanted;
    }

    public int getDelayDelta(){
        return numberOfDaysWanted - days;
    }

    public int getBudgetDelta(){
        return costWanted - cost;
    }

    public int getRisksDelta(){
        return numberOfRisksDrawnWanted - risks;
    }

    public int getDays() {
        return days;
    }

    public int getCost() {
        return cost;
    }

    public int getRisks() {return risks; }

    public void addCost(int cost){
        this.cost += cost;
    }

    public void addDays(int days){
        this.days += days;
    }

    public void addRisks(int risks){
        this.risks += risks;
    }

    public int getCostWanted() {
        return costWanted;
    }

    public int getNumberOfDaysWanted() {
        return numberOfDaysWanted;
    }

    public int getNumberOfRisksDrawnWanted() {
        return numberOfRisksDrawnWanted;
    }
}
