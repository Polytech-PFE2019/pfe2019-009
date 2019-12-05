package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.components.game.RiskCard;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.buying.BuyingAction;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Activity implements BuyingAction {
    private int id;
    private int numberOfDays;
    private String title;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus;
    private List<RiskCard> riskCardList;

    public Activity(int id, int numberOfDays, String title , String description, List<PayResources> payResourcesList){
        this.id = id;
        this.numberOfDays = numberOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.activityStatus = ActivityStatus.NOT_STARTED;
        this.riskCardList = new ArrayList<>();
    }


    public boolean allMandatoryResourcesHaveBeenPayed(){
        return payResourcesList.stream()
                .filter(payResources ->payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .allMatch(payResources -> payResources.hasPaid());
    }

    public boolean allNegotiationsAreFinished(){
        return true;
    }

    public boolean isActivityDone(){
        return allNegotiationsAreFinished() && allNegotiationsAreFinished();
    }


    public boolean payResources(Player player, List<Payment> payments){
        int roleID = player.getRole().getId();
        int totalAmount = payments.stream().mapToInt(Payment::getAmount).sum();
        if(totalAmount > player.getResourcesAmount())
            return false;

        for (Payment payment: payments) {
            Optional<PayResources> payResources = payResourcesList.stream().filter(payResource -> ((payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(payment.getType()))).findAny();
            payResources.ifPresent(payResources1 -> payResources1.pay(payment.getAmount()));
        }

        player.subtractResources(totalAmount);
        return true;
    }



    @Override
    public void buyResources(Player player, int amount){
        int exchangeRate = this.getExchangeRateForRoleID(player.getRole().getId());
        player.addResources(amount);
        player.subtractMoney(amount * exchangeRate);
    }

    @Override
    public int getExchangeRateForRoleID(int roleID){
        return 2;
    }

    //BuyingAction abstract implementation

    public List<Negotiation> getNegotiationList(){
        return new ArrayList<>();
    }

    public List<PayResources> getPayResourcesList() {
        return payResourcesList;
    }

    public void addPayResources(PayResources payResources){
        this.payResourcesList.add(payResources);
    }

    public List<BuyResources> getBuyResourcesList() {
        return new ArrayList<>();
    }

    public List<PayContract> getPayContractList(){
        return new ArrayList<>();
    }

    public int getNumberOfDays(){
        return numberOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void startActivity(){
        activityStatus = ActivityStatus.ONGOING;
    }

    public void doneActivity(){
        activityStatus = ActivityStatus.DONE;
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

    public String getTitle() {
        return title;
    }

    public void addDays(int numberOfDays) {
        this.numberOfDays += numberOfDays;
    }

    public List<RiskCard> getRiskCardList() {
        return riskCardList;
    }
}
