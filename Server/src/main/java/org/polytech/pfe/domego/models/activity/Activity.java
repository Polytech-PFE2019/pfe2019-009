package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Activity implements BuyingAction, NegotiationAction, PayPlayerAction {
    private int id;
    private int numbersOfDays;
    private String title;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus;

    public Activity(int id, int numbersOfDays, String title ,String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numbersOfDays = numbersOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.activityStatus = ActivityStatus.NOT_STARTED;
    }

    //Common methods for all activities

    private boolean allMandatoryResourcesHaveBeenPayed(){
        return payResourcesList.stream()
                .filter(payResources ->payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .noneMatch(payResources -> !payResources.hasPaid());
    }

    public int getNumberOfDays(){
        return numbersOfDays;
    }

    public String getDescription() {
        return description;
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

    //Paying resources
    public List<PayResources> getPayResourcesList() {
        return payResourcesList;
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

    public Optional<PayResources> getPayResourcesByRoleAndType(int roleID, PayResourceType payResourceType){
        return payResourcesList.stream().filter(payResources -> ((payResources.getRoleID() == roleID) && payResources.getPayResourceType().equals(payResourceType))).findAny();

    }
    //Buy resources for any activity not of type BuyResources
    public void buyResources(Player player, int amount){
        int exchangeRate = this.getExchangeRateForRoleID(player.getRole().getId());
        player.addResources(amount);
        player.subtractMoney(amount * exchangeRate);
    }

    //BuyingAction abstract implementation

    public List<BuyResources> getBuyResourcesList() { return new ArrayList<>(); }

    public BuyResources getBuyResourcesByRoleID(int roleID){
        return null;
    }

    //PayPlayerAction abstract implementation

    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return Optional.empty();
    }

    public Optional<PayPlayer> getPayPlayerByID(String id){
        return Optional.empty();
    }

    public List<PayPlayer> getPayPlayerList(){
        return new ArrayList<>();
    }

    //NegotiationAction abstract implementation

    public List<Negotiation> getNegotiationList(){
        return new ArrayList<>();
    }

}
