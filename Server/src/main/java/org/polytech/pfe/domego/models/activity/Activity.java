package org.polytech.pfe.domego.models.activity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Activity {
    private int id;
    private int numbersOfDays;
    private String title;
    @JsonIgnore
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus;
    private List<PayPlayer> payPlayerList;

    public Activity(int id, int numbersOfDays, String title ,String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.payPlayerList = new ArrayList<>();
        this.activityStatus = ActivityStatus.NOT_STARTED;
    }

    public boolean payResources(Player player, List<Payment> payments){
        int roleID = player.getRole().getId();
        int totalAmount = payments.stream().mapToInt(Payment::getAmount).sum();
        if(totalAmount > player.getResourcesAmount())
            return false;

        for (Payment payment: payments) {
            Optional<PayResources> payResources = getPayResourcesByRoleAndType(roleID, payment.getType());
            payResources.ifPresent(payResources1 -> payResources1.pay(payment.getAmount()));
        }
        if (allMandatoryResourcesHaveBeenPayed())
            this.finishActivity();

        player.subtractResources(totalAmount);
        return true;
    }

    private boolean allMandatoryResourcesHaveBeenPayed(){
        return payResourcesList.stream()
                .filter(payResources ->payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .noneMatch(payResources -> !payResources.hasPaid());
    }

    public void buyResources(Player player, int amount){
        int exchangeRate = this.getExchangeRateForRoleID(player.getRole().getId());
        player.addResources(amount);
        player.subtractMoney(amount * exchangeRate);
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

    public int getNumberOfDays(){
        return numbersOfDays;
    }

    public String getDescription() {
        return description;
    }

    public List<PayResources> getPayResourcesList() {
        return payResourcesList;
    }

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
