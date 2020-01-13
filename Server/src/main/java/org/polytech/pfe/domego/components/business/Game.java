package org.polytech.pfe.domego.components.business;

import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Game {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String id;
    private List<Player> players;
    private List<Activity> activities;
    private int currentActivity;
    private Project project;
    private GameType gameType;


    public Game(String id, List<Player> players, List<Activity> activities, int costWanted, int numberOfDaysWanted,
                int numberOfRisksDrawnWanted, GameType gameType) {
        this.id = id;
        this.players = players;
        this.activities = activities;
        this.project = new Project(costWanted, numberOfDaysWanted, numberOfRisksDrawnWanted);
        this.currentActivity = 0;
        this.gameType = gameType;
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

    public void goToTheNextActivity(){
        this.updateProject();
        this.changeActivity();
    }

    private void changeActivity() {
        if (currentActivity + 1 != activities.size()) {
            Activity oldActivity = this.getCurrentActivity();
            oldActivity.finishActivity();
            currentActivity++;
            this.getCurrentActivity().startActivity();
        }
    }

    private void updateProject(){
        Activity activity = this.getCurrentActivity();
        this.project.addDays(activity.getNumberOfDays());
        int totalAmount = activity.getPayResourcesList().stream().mapToInt(PayResources::getAmountPaid).sum();
        this.project.addCost(totalAmount);
        int risks = activity.getRiskCardList().size();
        this.project.addRisks(risks);
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

    public GameType getGameType() {
        return gameType;
    }
}
