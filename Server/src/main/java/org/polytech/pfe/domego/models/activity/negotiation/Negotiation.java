package org.polytech.pfe.domego.models.activity.negotiation;

import java.util.UUID;

public class Negotiation {
    private int giverRoleID;
    private int receiverRoleID;
    private double amountNegociated;
    private double amountLeftToPay;
    private String id;
    private int time;     // in seconds
    private Contract contract;
    private NegotiationStatus negotiationStatus;

    public Negotiation(int giverRoleID, int receiverRoleID, Contract contract){
        this.time = 180;
        this.id = UUID.randomUUID().toString();
        this.giverRoleID = giverRoleID;
        this.receiverRoleID = receiverRoleID;
        this.contract = contract;
        this.negotiationStatus = NegotiationStatus.NOT_STARTED;
    }

    public void negotiate(double amount){
        this.amountNegociated = amount;
        this.amountLeftToPay = amountNegociated;
        this.negotiationStatus = NegotiationStatus.SUCCESS;
    }

    public double pay(int percentage){
        double lastPayment = (amountNegociated*percentage) / 100;
        this.amountLeftToPay -= lastPayment;
        return lastPayment;
    }

    public void multiplicateTime(int number){
        this.time *= number;
    }

    /**
     * set status to failure and tire au sort un contrat
     */
    public void fail(){
        this.negotiationStatus = NegotiationStatus.FAILURE;
        int random =  (int) Math.round(Math.random());
        switch (random){
            case 0 : this.amountNegociated = contract.getMin();
            break;
            case 1 : this.amountNegociated = contract.getMax();
            break;
        }
    }

    public int getGiverRoleID() {
        return giverRoleID;
    }

    public int getReceiverRoleID() {
        return receiverRoleID;
    }

    public double getAmountNegotiated() {
        return amountNegociated;
    }



    public double getAmountLeftToPay() {
        return amountLeftToPay;
    }

    public String getId() {
        return id;
    }

    public NegotiationStatus getNegotiationStatus() {
        return negotiationStatus;
    }

    public int getTime() {
        return time;
    }
}
