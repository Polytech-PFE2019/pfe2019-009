package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.buying.BuyingAction;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class Activity implements BuyingAction {
    private Logger logger = Logger.getGlobal();
    private int id;
    private int numberOfDays;
    private int initialNumberOfDays;
    private int initialNumberOfRisks;
    private String title;
    private String description;
    private List<PayResources> payResourcesList;
    private ActivityStatus activityStatus;
    private List<RiskCard> riskCardList;
    private List<QualityCard> qualityCards;

    public Activity(int id, int numberOfDays, String title , String description, List<PayResources> payResourcesList, List<RiskCard> riskCards){
        this.id = id;
        this.numberOfDays = numberOfDays;
        this.initialNumberOfDays = numberOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.activityStatus = ActivityStatus.NOT_STARTED;
        this.qualityCards = new ArrayList<>();
        this.riskCardList = riskCards;
        this.initialNumberOfRisks = riskCards.size();
    }

    public Activity(int id, int numberOfDays, String title , String description, List<PayResources> payResourcesList, List<RiskCard> riskCards, List<QualityCard> qualityCards){
        this.id = id;
        this.numberOfDays = numberOfDays;
        this.initialNumberOfDays = numberOfDays;
        this.title = title;
        this.description = description;
        this.payResourcesList = payResourcesList;
        this.activityStatus = ActivityStatus.NOT_STARTED;
        this.qualityCards = qualityCards;
        this.riskCardList = riskCards;
        this.initialNumberOfRisks = riskCards.size();
    }


    public boolean allMandatoryResourcesHaveBeenPayed(){
        return payResourcesList.stream()
                .filter(payResources ->payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .allMatch(PayResources::hasPaid);
    }

    public boolean allNegotiationsAreFinished(){
        return true;
    }

    public boolean isActivityDone(){
        return allMandatoryResourcesHaveBeenPayed() && allNegotiationsAreFinished();
    }


    public boolean payResources(Player player, List<Payment> payments){
        int roleID = player.getRole().getId();
        int totalAmount = payments.stream().mapToInt(Payment::getAmount).sum();
        if(totalAmount > player.getResourcesAmount()){
            int resourcesNeeded = totalAmount - player.getResourcesAmount();
            player.addResources(resourcesNeeded);
            player.subtractMoney(resourcesNeeded * getExchangeRateForRoleID(player.getRole().getId()));
        }

        for (Payment payment: payments) {
            if (payment.getType().equals(PayResourceType.MANDATORY)){
                if (payment.getAmount() >= payResourcesList.stream().filter(payResource -> (payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(PayResourceType.MANDATORY) && !payResource.hasPaid()).mapToInt(payResources -> Collections.max(payResources.getPriceAndBonusMap().keySet())).sum()){
                    for (PayResources payResources : payResourcesList.stream().filter(payResource -> (payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(PayResourceType.MANDATORY) && !payResource.hasPaid()).collect(Collectors.toList())) {
                        payResources.pay(payment.getAmount());
                    }
                    logger.log(Level.INFO, "The mandatory payment is done");
                }else if(payResourcesList.stream().anyMatch(payResource -> (payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(PayResourceType.MANDATORY) && !payResource.hasPaid() && payResource.getPriceAndBonusMap().containsKey(payment.getAmount()))){
                    payResourcesList.stream().filter(payResource -> (payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(PayResourceType.MANDATORY) && !payResource.hasPaid() && payResource.getPriceAndBonusMap().containsKey(payment.getAmount())).findAny().get().pay(payment.getAmount());
                    logger.log(Level.INFO, "The mandatory payment is done");
                }



            }
            else {
                Optional<PayResources> optionalPayResources = payResourcesList.stream().filter(payResource -> (payResource.getRoleID() == roleID) && payResource.getPayResourceType().equals(payment.getType())).findAny();
                if(optionalPayResources.isEmpty()) {
                    logger.log(Level.WARNING, "Payment Not found");
                    continue;
                }
                PayResources payResources = optionalPayResources.get();
                payResources.pay(payment.getAmount());
                logger.log(Level.INFO, "The {0} payment is done", payResources.getPayResourceType().getName());
                if (payResources.getPayResourceType().equals(PayResourceType.RISKS))
                    this.removeNRisk(payResources.getBonusGiven());
                else if(payResources.getPayResourceType().equals(PayResourceType.DAYS))
                    this.removeDays(payResources.getBonusGiven());

            }

        }

        payResourcesList.stream().filter(payResources -> payResources.getRoleID() == player.getRole().getId()).forEach(payResources -> payResources.setHasPaid(true));//When A player have paid, he can't pay for this activity again
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

    public void removeDays(int numberOfDays){
        this.numberOfDays -= numberOfDays;
    }

    public void removeNRisk(int n){
        int listIndex = this.riskCardList.size() -1 ;
        if(listIndex - n < 0){
            this.riskCardList.clear();
            return;
        }
        for (int i = 0; i < n; i++)
            this.riskCardList.remove(0);


    }

    public boolean addRisk(RiskCard riskCard){
        return this.riskCardList.add(riskCard);
    }

    public List<RiskCard> getRiskCardList() {
        return riskCardList;
    }

    public int getInitialNumberOfDays() {
        return initialNumberOfDays;
    }

    public List<QualityCard> getQualityCards() {
        return qualityCards;
    }

    public int getInitialNumberOfRisks() {
        return initialNumberOfRisks;
    }
}
