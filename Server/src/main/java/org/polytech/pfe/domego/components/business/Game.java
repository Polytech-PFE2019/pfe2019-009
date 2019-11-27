package org.polytech.pfe.domego.components.business;

import org.polytech.pfe.domego.models.PayResourceType;
import org.polytech.pfe.domego.models.PayResources;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.BuyResources;
import org.polytech.pfe.domego.models.activity.BuyingResourcesActivity;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    String id;

    List<Player> players;

    List<Activity> activities = new ArrayList<>();

    int currentActivity;

    private Project project;

    public Game() {

        initActivitiesOfGame();


    }

    public Game(String id) {
        this.id = id;
    }

    public Game(String id, List<Player> players) {
        this.id = id;
        this.players = players;
        initActivitiesOfGame();

    }

    public boolean connectToTheGame(){
        return true;
    }

    public Optional<Player> getPlayerById(String playerID){
        return players.stream().filter(player -> player.getID().equals(playerID)).findFirst();
    }



    private void initActivitiesOfGame(){

        Map<Integer,Integer> priceAndBonusMap = new HashMap<>();

        priceAndBonusMap.put(1,0);

        PayResources payResources = new PayResources(1,priceAndBonusMap, PayResourceType.MANDATORY);


        Map<Integer,Integer> priceAndBonusMap2 = new HashMap<>();

        priceAndBonusMap2.put(1,1);

        priceAndBonusMap2.put(3,2);

        priceAndBonusMap2.put(5,3);

        PayResources payResources2 = new PayResources(1,priceAndBonusMap2, PayResourceType.DAYS);

        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(payResources);
        payResourcesList.add(payResources2);

        BuyResources buyResources = new BuyResources(1,1);
        List<BuyResources> buyResourcesList = new ArrayList<>();
        buyResourcesList.add(buyResources);

        List<Activity> activities = new ArrayList<>();
        Activity activity = new BuyingResourcesActivity(1,30,"description",payResourcesList,buyResourcesList);
        activities.add(activity);
        this.setActivities(activities);

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

    public void setCurrentActivity(int currentActivity) {
        this.currentActivity = currentActivity;
    }

    public List<Player> getPlayersPresent(){
        return players.stream().filter(player -> player.getSession() != null).collect(Collectors.toList());
    }

    public Activity getCurrentActivity(){
        return activities.get(currentActivity);
    }
}
