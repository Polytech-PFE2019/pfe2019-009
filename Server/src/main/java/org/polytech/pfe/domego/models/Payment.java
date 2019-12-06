package org.polytech.pfe.domego.models;

import org.polytech.pfe.domego.models.activity.PayResourceType;

public class Payment {

    private int amount;

    private PayResourceType type;

    public Payment(int amount, PayResourceType type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PayResourceType getType() {
        return type;
    }

    public void setType(PayResourceType type) {
        this.type = type;
    }
}
