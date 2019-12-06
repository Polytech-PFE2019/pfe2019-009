package org.polytech.pfe.domego.models.activity.pay;

import org.polytech.pfe.domego.models.activity.PayResourceType;

import java.util.Map;

public class PayResources implements Comparable<PayResources> {
    private int roleID;
    private Map<Integer, Integer> priceAndBonusMap;
    private int amountPaid;
    private int bonusGiven;
    private PayResourceType payResourceType;
    private boolean hasPaid;
    private boolean isExtraPayment;

    public PayResources(int roleID, Map<Integer,Integer> priceAndBonusMap, PayResourceType payResourceType){
        this.amountPaid = 0;
        this.hasPaid = false;
        this.roleID = roleID;
        this.payResourceType = payResourceType;
        this.priceAndBonusMap = priceAndBonusMap;
        this.isExtraPayment = false;
    }

    public PayResources(int roleID, Map<Integer,Integer> priceAndBonusMap, PayResourceType payResourceType, boolean extra){
        this.amountPaid = 0;
        this.hasPaid = false;
        this.roleID = roleID;
        this.payResourceType = payResourceType;
        this.priceAndBonusMap = priceAndBonusMap;
        this.isExtraPayment = extra;
    }

    public PayResourceType getPayResourceType() {
        return payResourceType;
    }

    public boolean hasPaid(){
        return hasPaid;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid){
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
        setHasPaid(true);
        return true;
    }

    @Override
    public int compareTo(PayResources o) {
        Integer oID = o.getPayResourceType().getId();
        Integer thisID = this.getPayResourceType().getId();

        return thisID.compareTo(oID);
    }

    public boolean isExtraPayment() {
        return isExtraPayment;
    }
}
