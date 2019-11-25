package org.polytech.pfe.domego.components.buisness;

import org.polytech.pfe.domego.models.Activity;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.models.RoleType;

import java.util.Iterator;
import java.util.List;

public class Game {

    String id;

    List<Player> players;

    List<Activity> activities;

    int currentActivity;

    private Project project;

    public Game() {



    }

    public Game(String id, List<Player> players) {
        this.id = id;
        this.players = players;

    }

    public boolean connectToTheGame(){
        return true;
    }




    private void initActivitiesOfGame(){

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
