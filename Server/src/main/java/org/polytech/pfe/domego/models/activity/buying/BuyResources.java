package org.polytech.pfe.domego.models.activity.buying;

public class BuyResources {
    private int roleID;
    private int rate;
    private boolean hasPaid = false;
    private int amountPaid = 0;
    private int resourcesGiven = 0;

    public BuyResources(int roleID, int rate){
        this.roleID = roleID;
        this.rate = rate;
    }

    public void buyResources(int amount){
        this.amountPaid = amount * rate ;
        this.resourcesGiven = amount;
        this.setHasPaid(true);
    }

    public int getResourcesGiven(){
        return resourcesGiven;
    }

    private void setHasPaid(boolean hasPaid){
        this.hasPaid = hasPaid;
    }

    public int getRoleID() {
        return roleID;
    }

    public int getRate() {
        return rate;
    }

    public boolean hasPaid() {
        return hasPaid;
    }

    public int getAmountPaid() {
        return amountPaid;
    }
}
