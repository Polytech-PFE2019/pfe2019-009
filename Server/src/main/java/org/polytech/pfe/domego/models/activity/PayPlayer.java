package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

public class PayPlayer {
    private Negotiation negotiation;
    private int percentage;
    private boolean status;

    public PayPlayer(Negotiation negotiation, int percentage){
        this.status = false;
        this.negotiation = negotiation;
        this.percentage = percentage;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public int getPercentage() {
        return percentage;
    }

    public boolean isPaid() {
        return status;
    }

    public void setPaid(){
        this.status = true;
    }
}
