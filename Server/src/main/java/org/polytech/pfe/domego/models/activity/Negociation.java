package org.polytech.pfe.domego.models.activity;

import java.util.UUID;

public class Negociation {
    private int giverRoleID;
    private int receiverRoleID;
    private int amountNegociated;
    private boolean status;
    private int lastPayment;
    private int amountLeftToPay;
    private String id;

    public Negociation(int giverRoleID, int receiverRoleID){
        this.id = UUID.randomUUID().toString();
        this.status = false;
        this.giverRoleID = giverRoleID;
        this.receiverRoleID = receiverRoleID;
    }

    public void negociate(int amount){
        this.amountNegociated = amount;
        this.amountLeftToPay = amountNegociated;
        this.status = true;
    }

    public void pay(int percentage){
        this.lastPayment = (amountNegociated*percentage) / 100;
        this.amountLeftToPay -= lastPayment;
    }

    public int getGiverRoleID() {
        return giverRoleID;
    }

    public int getReceiverRoleID() {
        return receiverRoleID;
    }

    public int getAmountNegociated() {
        return amountNegociated;
    }

    public boolean isStatus() {
        return status;
    }

    public int getLastPayment() {
        return lastPayment;
    }

    public int getAmountLeftToPay() {
        return amountLeftToPay;
    }

    public String getId() {
        return id;
    }
}
