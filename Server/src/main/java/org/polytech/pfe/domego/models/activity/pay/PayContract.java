package org.polytech.pfe.domego.models.activity.pay;

import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.UUID;

public class PayContract {
    private Negotiation negotiation;
    private int percentage;
    private boolean status;
    private String id;

    public PayContract(Negotiation negotiation, int percentage){
        this.status = false;
        this.negotiation = negotiation;
        this.percentage = percentage;
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }
}
