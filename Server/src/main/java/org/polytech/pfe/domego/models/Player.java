package org.polytech.pfe.domego.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Player {
    @JsonIgnore
    private WebSocketSession session;
    private String id;
    private String name;
    private Role role;
    private int resourcesAmount;
    private double money;
    private List<Objective> objectiveList;
    private int victoryPoints;

    public Player(WebSocketSession session, String name){
        this.session = session;
        this.id = UUID.randomUUID().toString();
        this.role = new Role();
        this.name = name;
        this.resourcesAmount = 0;
        this.money = 0;
        this.victoryPoints = 0;
    }

    public Player(Player player) {
        this.id = player.id;
        this.role = player.role;
        this.objectiveList = player.objectiveList;
        this.name = player.name;
        this.resourcesAmount = player.resourcesAmount;
        this.money = player.money;
        this.session = null;
    }

    public boolean isObjectiveValidated(ObjectiveType objectiveType, int delay, int risks, int budget){
        Optional<Objective> objectiveOptional = this.objectiveList.stream().filter(obj -> obj.getObjectiveType().equals(objectiveType)).findFirst();
        if(objectiveOptional.isEmpty()){
            return false;
        }
        Objective objective = objectiveOptional.get();

        switch (objectiveType){
            case MONEY:
                return objective.isValid(calculateBenefit());
            case DAYS:
                return objective.isValid(delay);
            case RISK:
                return objective.isValid(risks);
            case DAYS_AND_BUDGET:
                return objective.isValid(delay,budget);
            default: return false;
        }
    }

    public void calculateObjectivesVictoryPoints(int delay, int risks, int budget){
        objectiveList.forEach(objective -> {
            switch (objective.getObjectiveType()) {
                case MONEY:
                    objective.calculateVictoryPoints(calculateBenefit());
                    break;
                case DAYS:
                    objective.calculateVictoryPoints(delay);
                    break;
                case RISK:
                    objective.calculateVictoryPoints(risks);
                    break;
                case BUDGET:
                    objective.calculateVictoryPoints(budget);
                    break;
                case DAYS_AND_BUDGET:
                    objective.calculateVictoryPoints(delay,budget);
                    break;
                default:
                    break;
            }
        });


    }

    public void substractVictoryPoints(int amount){
        victoryPoints -= amount;
    }

    public void addObjectivesVictoryPoints(){
        objectiveList.forEach(objective -> victoryPoints += objective.getVictoryPoints());
    }

    public void addVictoryPoint(int victoryPoints){
        this.victoryPoints += victoryPoints;
    }



    public double calculateBenefit(){
        double initialMoney = this.role.getBudget();

        return - initialMoney + this.money;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public void setRole(Role role){
        this.role = role;
        this.money = role.getBudget();
        this.objectiveList = role.getObjectiveList();
    }

    public Role getRole(){
        return role;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public int getResourcesAmount() {
        return resourcesAmount;
    }


    public void addResources(int amount){
        resourcesAmount += amount;
    }

    public void subtractResources(int amount){
        resourcesAmount -= amount;
    }

    public double getMoney() {
        return money;
    }


    public void addMoney(double amount){
        money += amount;
    }

    public void subtractMoney(int amount){
        money -= amount;
    }

    public void subtractMoney(double amount){
        money -= amount;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public List<Objective> getObjectiveList() {
        return objectiveList;
    }

    public void setObjectiveList(List<Objective> objectiveList) {
        this.objectiveList = objectiveList;
    }
}
