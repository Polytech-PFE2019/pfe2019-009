package org.polytech.pfe.domego.components.business;

import org.polytech.pfe.domego.models.Payment;
import org.polytech.pfe.domego.models.PaymentStatus;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.activity.BuyResources;
import org.polytech.pfe.domego.protocol.game.ChangeActivityEvent;
import org.polytech.pfe.domego.protocol.game.FinishGameEvent;
import org.polytech.pfe.domego.protocol.game.UpdatePaymentGameEvent;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Game {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String id;
    private List<Player> players;
    private List<Activity> activities;
    private int currentActivity;
    private Project project;


    public Game(String id, List<Player> players, List<Activity> activities) {
        this.id = id;
        this.players = players;
        this.activities = activities;
        this.project = new Project();
        this.currentActivity = 0;
    }

    public Optional<Player> getPlayerById(String playerID){
        return players.stream().filter(player -> player.getID().equals(playerID)).findFirst();
    }

    public Optional<Player> getPlayerByRoleID(int roleID){
        return this.players.stream().filter(player -> player.getRole().getId() == roleID).findFirst();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaymentStatus payForCurrentActivity(Player player, List<Payment> payments){
        Activity activity = this.getCurrentActivity();
        int roleID = player.getRole().getId();
        int totalAmount = payments.stream().mapToInt(Payment::getAmount).sum();
        if(totalAmount > player.getResourcesAmount()){
            logger.log(Level.INFO,
                    "PaymentResourcesEvent : In the game {0}, the player named {1} has not enough resources, he has {2} and he need {3} to pay",
                    new Object[]{this.getId(), player.getID(),player.getResourcesAmount(), totalAmount});
            return PaymentStatus.NOT_ENOUGH_RESOURCES;
        }

        for (Payment payment: payments) {
            if(!activity.payResources(roleID,payment.getType(), payment.getAmount())){
                logger.log(Level.INFO,"PaymentResourcesEvent : Error not paid");
                return PaymentStatus.NOT_PAID;
            }

        }
        logger.log(Level.INFO,
                "PaymentResourcesEvent : In the game {0}, the player named {1} has realize {2} payment for the activity : {3}",
                new Object[]{this.getId(), player.getID(),payments.size(), activity.getId()});
        player.subtractResources(totalAmount);
        new UpdatePaymentGameEvent(this,activity,players,activity.getPayResourcesList(), roleID).processEvent();
        if (this.getCurrentActivity().getActivityStatus().equals(ActivityStatus.FINISHED)){
            this.updateProject();
            this.drawRiskCard();
            this.updateGame();
        }
        return PaymentStatus.GOOD;
    }

    private void updateGame() {
        if (currentActivity + 1 == activities.size()){
            new FinishGameEvent(this).processEvent();
        }
        else{
            getCurrentActivity().getPayResourcesList().forEach(payResources -> payResources.setHasPaid(true));
            currentActivity++;
            this.getCurrentActivity().startActivity();
            new ChangeActivityEvent(players,getCurrentActivity(),project).processEvent();
        }
    }

    private void drawRiskCard() {
    }

    private void updateProject(){
        Activity activity = this.getCurrentActivity();
        this.project.addDelay(activity.getNumberOfDays());
        int totalAmount = activity.getBuyResourcesList().stream().filter(BuyResources::hasPaid).mapToInt(BuyResources::getAmountPaid).sum();
        this.project.addCost(totalAmount);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Player> getPlayersPresent(){
        return players.stream().filter(player -> player.getSession() != null).collect(Collectors.toList());
    }

    public Project getProject() {
        return project;
    }

    public Activity getCurrentActivity(){
        return activities.get(currentActivity);
    }
}
