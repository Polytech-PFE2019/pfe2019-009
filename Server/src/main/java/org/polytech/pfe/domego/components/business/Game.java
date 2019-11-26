package org.polytech.pfe.domego.components.business;

import org.polytech.pfe.domego.models.*;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ClassicActivity;

import java.util.ArrayList;
import java.util.List;

public class Game {

    String id;

    List<Player> players;

    List<Activity> activities = new ArrayList<>();

    int currentActivity;

    private Project project;

    public Game() {

        initActivitiesOfGame();


    }

    public Game(String id, List<Player> players) {
        this.id = id;
        this.players = players;
        initActivitiesOfGame();

    }

    public boolean connectToTheGame(){
        return true;
    }

    public Player getPlayerByID(String playerID){
        return players.stream().filter(player -> player.getID().equals(playerID)).findAny().orElse(null);
    }



    private void initActivitiesOfGame(){

        PayResources payResources = new PayResources(1,1,0, PayResourceType.MANDATORY);
        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(payResources);

        List<Activity> activities = new ArrayList<>();
        Activity activity = new ClassicActivity(1,30,"description",payResourcesList);
        activities.add(activity);
        this.setActivities(activities);

    }

    public int getCurrentPriceOfResource(RoleType roleType){
        return 2;
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

    public int getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(int currentActivity) {
        this.currentActivity = currentActivity;
    }
}
