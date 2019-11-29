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

    public Project getProject() {
        return project;
    }

    public Activity getCurrentActivity(){
        return activities.get(currentActivity);
    }
}
