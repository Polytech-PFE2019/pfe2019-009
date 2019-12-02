package org.polytech.pfe.domego.models.activity;

import java.util.Map;

public class PayResources implements Comparable<PayResources> {
    private int roleID;
    private Map<Integer, Integer> priceAndBonusMap;
    private int amountPaid;
    private int bonusGiven;
    private PayResourceType payResourceType;
    private boolean hasPaid;
    private int amountLeft;

    public PayResources(int roleID, Map<Integer,Integer> priceAndBonusMap, PayResourceType payResourceType){
        this.amountPaid = 0;
        this.hasPaid = false;
        this.roleID = roleID;
        this.payResourceType = payResourceType;
        this.priceAndBonusMap = priceAndBonusMap;
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

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getRoleID() {
        return roleID;
    }

    public Map<Integer,Integer> getPriceAndBonusMap(){
        return  priceAndBonusMap;
    }

    public boolean pay(int amount){

        priceAndBonusMap.forEach((price,bonus)-> {
            if(price <= amount){
                this.amountPaid = price;
                this.bonusGiven = bonus;
            }
        });
        if(amountPaid == 0){
            return false;
        }
        if(this.amountPaid > amount){
            this.amountLeft = amountPaid-amount;
        }
        setHasPaid(true);
        return true;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    @Override
    public int compareTo(PayResources o) {
        Integer oID = o.getPayResourceType().getId();
        Integer thisID = this.getPayResourceType().getId();

        return thisID.compareTo(oID);
    }
}
