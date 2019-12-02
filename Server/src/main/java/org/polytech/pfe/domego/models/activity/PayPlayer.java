package org.polytech.pfe.domego.models.activity;

import com.google.gson.internal.$Gson$Types;

public class PayPlayer {
    private Negociation negociation;
    private int percentage;
    private boolean status;

    public PayPlayer(Negociation negociation, int percentage){
        this.status = false;
        this.negociation = negociation;
        this.percentage = percentage;
    }

    public Negociation getNegociation() {
        return negociation;
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
