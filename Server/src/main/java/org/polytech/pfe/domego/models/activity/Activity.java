package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResourceType;
import org.polytech.pfe.domego.models.PayResources;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Activity {
    private int id;
    private int numbersOfDays;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus = ActivityStatus.NOT_STARTED;
    //Map to know easily if the mandatory has been paid by the roles
    private Map<Integer, Boolean> roleHasPaid;

    protected List<BuyResources> buyResourcesList;

    Activity(int id, int numbersOfDays, String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.buyResourcesList = new ArrayList<>();
        List<PayResources> mandatoryPayResourcesList = payResourcesList.stream().filter(payResources ->
                payResources.getPayResourceType().equals(PayResourceType.MANDATORY)).collect(Collectors.toList());

        roleHasPaid = new HashMap<>();
        mandatoryPayResourcesList.forEach(payResources -> roleHasPaid.put(payResources.getRoleID(),false));
    }

    private Optional<PayResources> getPayResourcesByRoleAndType(int roleID, PayResourceType payResourceType){
        return payResourcesList.stream().filter(payResources -> ((payResources.getRoleID() == roleID) && payResources.getPayResourceType().equals(payResourceType))).findAny();

    }

    public boolean payResources(int roleID, PayResourceType payResourceType, int amount){
        Optional<PayResources> payResources = getPayResourcesByRoleAndType(roleID, payResourceType);

        if(payResources.isEmpty() || amount < payResources.get().getAmountNeeded()){
            return false;
        }
        payResources.get().pay(amount);
        return true;

    }

    public boolean hasRolePaidMandatory(int roleID){
        return roleHasPaid.get(roleID);
    }

    public void setRolePaidMandatory(int roleID){
        roleHasPaid.put(roleID,true);
    }

    public int getNumberOfDays(){
        return numbersOfDays;
    }

    public String getDescription() {
        return description;
    }

    public List<PayResources> getPayResourcesList() {
        return payResourcesList;
    }

    public List<BuyResources> getBuyResourcesList() { return buyResourcesList; }

    public void startActivity(){
        activityStatus = ActivityStatus.ONGOING;
    }

    public void finishActivity(){
        activityStatus = ActivityStatus.FINISHED;
    }

    public int getId() {
        return id;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public abstract int getExchangeRateForRoleID(int roleID);

    public abstract void buyResources(int roleID, int amount);

}
