package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResourceType;
import org.polytech.pfe.domego.models.PayResources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Activity {
    private int id;
    private int numbersOfDays;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus = ActivityStatus.NOT_STARTED;
    //Map to know easily if the mandatory has been paid by the roles
    private Map<Integer, Boolean> roleHasPaid;

    protected List<BuyResources> buyResourcesList = new ArrayList<>();

    Activity(int id, int numbersOfDays, String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.description = description;
        this.payResourcesList = payResourcesList;
        List<PayResources> mandatoryPayResourcesList = payResourcesList.stream().filter(payResources ->
                payResources.getPayResourceType().equals(PayResourceType.MANDATORY)).collect(Collectors.toList());

        roleHasPaid = new HashMap<>();
        mandatoryPayResourcesList.forEach(payResources -> roleHasPaid.put(payResources.getRoleID(),false));
    }

    private PayResources getPayResourcesByRoleAndType(int roleID, PayResourceType payResourceType){
        return payResourcesList.stream().filter(payResources -> ((payResources.getRoleID() == roleID) && payResources.getPayResourceType().equals(payResourceType))).findAny().orElse(null);

    }

    public void payResources(int roleID, PayResourceType payResourceType, int amount){
        PayResources payResources = getPayResourcesByRoleAndType(roleID, payResourceType);

        payResources.pay(amount);
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
