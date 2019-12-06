package org.polytech.pfe.domego.models.activity.negotiation;

public class Contract {
    private int min;
    private int max;

    public Contract(int min,int max){
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
