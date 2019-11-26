package org.polytech.pfe.domego.models;

import java.util.Map;

public class PayResources {
    private int roleID;
    //TODO SEVERAL PRICES FOR SEVERAL BONUSES
    private Map<Integer, Integer> toll;
    private int amountNeeded;
    private int bonusGiven;
    private PayResourceType payResourceType;
    private boolean hasPaid = false;

    public PayResources(int roleID, int amountNeeded, int bonusGiven, PayResourceType payResourceType){
        this.roleID = roleID;
        this.amountNeeded = amountNeeded;
        this.bonusGiven = bonusGiven;
        this.payResourceType = payResourceType;
    }

    public PayResourceType getPayResourceType() {
        return payResourceType;
    }

    public boolean hasPaid(){
        return hasPaid;
    }

    private void setHasPaid(boolean hasPaid){
        this.hasPaid = hasPaid;
    }

    public int getBonusGiven() {
        return bonusGiven;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public int getRoleID() {
        return roleID;
    }

    public void pay(int amount){
        // here you will set the bonus given according to the amount
        setHasPaid(true);
    }
}
