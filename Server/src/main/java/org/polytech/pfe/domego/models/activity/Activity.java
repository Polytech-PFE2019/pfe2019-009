package org.polytech.pfe.domego.models.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Activity {
    private int id;
    private int numbersOfDays;
    private String title;
    @JsonIgnore
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus;
    protected List<BuyResources> buyResourcesList;
    private List<PayPlayer> payPlayerList;

    public Activity(int id, int numbersOfDays, String title ,String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.buyResourcesList = new ArrayList<>();
        this.payPlayerList = new ArrayList<>();
        this.activityStatus = ActivityStatus.NOT_STARTED;
    }

    public boolean payResources(int roleID, PayResourceType payResourceType, int amount){
        Optional<PayResources> payResources = getPayResourcesByRoleAndType(roleID, payResourceType);
        if(payResources.isEmpty())
            return false;

        if(!payResources.get().pay(amount))
            return false;

        if (allMandatoryResourcesHaveBeenPayed())
            this.finishActivity();

        return true;
    }

    private boolean allMandatoryResourcesHaveBeenPayed(){
        return payResourcesList.stream()
                .filter(payResources ->payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .noneMatch(payResources -> !payResources.hasPaid());
    }

    public void buyResources(int roleID, int amount){
        BuyResources newPaiement = new BuyResources(roleID,this.getExchangeRateForRoleID(roleID));
        newPaiement.buyResources(amount);
        this.buyResourcesList.add(newPaiement);
    }

    public void setPayPlayerList(List<PayPlayer> payPlayerList) {
        this.payPlayerList = payPlayerList;
    }

    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giverID
                && payPlayer.getNegotiation().getReceiverRoleID() == receiverID ).findAny();
    }

    public Optional<PayResources> getPayResourcesByRoleAndType(int roleID, PayResourceType payResourceType){
        return payResourcesList.stream().filter(payResources -> ((payResources.getRoleID() == roleID) && payResources.getPayResourceType().equals(payResourceType))).findAny();

    }

    public Set<Integer> getRoleIdHasToPlayDuringThisActivity(){
        return payResourcesList.stream().map(PayResources::getRoleID).collect(Collectors.toSet());
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

    public List<Negotiation> getNegotiationList(){
        return new ArrayList<>();
    }


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

}
