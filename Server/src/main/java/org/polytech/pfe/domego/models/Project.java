package org.polytech.pfe.domego.models;

public class Project {
    private int cost;
    private int delay;
    private int failure;

    public Project(){
        this.cost = 0;
        this.delay = 0;
        this.failure = 0;
    }

    public int getDelay() {
        return delay;
    }

    public int getCost() {
        return cost;
    }

    public int getFailure() {return failure; }

    public void addCost(int cost){
        this.cost += cost;
    }

    public void addDelay(int delay){
        this.delay += delay;
    }

    public void addFailure(int failure){
        this.failure += failure;
    }

}
