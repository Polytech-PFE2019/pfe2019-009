package org.polytech.pfe.domego.models.activity;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Activity {
    private int id;
    private int numbersOfDays;
    private String title;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus = ActivityStatus.NOT_STARTED;
    //Map to know easily if the mandatory has been paid by the roles
    private Map<Integer, Boolean> roleHasPaid;

    protected List<BuyResources> buyResourcesList;

    protected List<Negociation> negociationList;

    private List<PayPlayer> payPlayerList;

    Activity(int id, int numbersOfDays, String title ,String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.buyResourcesList = new ArrayList<>();
        this.negociationList = new ArrayList<>();
        this.payPlayerList = new ArrayList<>();
        List<PayResources> mandatoryPayResourcesList = payResourcesList.stream().filter(payResources ->
                payResources.getPayResourceType().equals(PayResourceType.MANDATORY)).collect(Collectors.toList());

        roleHasPaid = new HashMap<>();
        mandatoryPayResourcesList.forEach(payResources -> roleHasPaid.put(payResources.getRoleID(),false));
    }

    public Optional<PayResources> getPayResourcesByRoleAndType(int roleID, PayResourceType payResourceType){
        return payResourcesList.stream().filter(payResources -> ((payResources.getRoleID() == roleID) && payResources.getPayResourceType().equals(payResourceType))).findAny();

    }

    public boolean payResources(int roleID, PayResourceType payResourceType, int amount){
        Optional<PayResources> payResources = getPayResourcesByRoleAndType(roleID, payResourceType);

        if(payResources.isEmpty()){
            return false;
        }

        if(!payResources.get().pay(amount)){
            return false;
        }
        return true;

    }

    public void setPayPlayerList(List<PayPlayer> payPlayerList) {
        this.payPlayerList = payPlayerList;
    }

    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getNegociation().getGiverRoleID() == giverID
                && payPlayer.getNegociation().getReceiverRoleID() == receiverID ).findAny();
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
        return payResourcesList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public List<BuyResources> getBuyResourcesList() { return buyResourcesList; }

    public List<Negociation> getNegociationList() { return negociationList; }


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

    public int getExchangeRateForRoleID(int roleID){
        return 2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void buyResources(int roleID, int amount){
        BuyResources newPaiement = new BuyResources(roleID,this.getExchangeRateForRoleID(roleID));
        newPaiement.buyResources(amount);
        this.buyResourcesList.add(newPaiement);
    }


}
