package org.polytech.pfe.domego.models;

import java.util.List;

public class Game {
    private Project project;
    private List<Player> players;
    private int currentStep;
    private int id;

    public Game(List<Player> players, int roomID){
        this.id = roomID;
        this.players = players;
        this.project = new Project();
        this.currentStep = 0;
    }

    public void startNextStep(){
        this.currentStep++;
    }

    public Project getProject() {
        return project;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int getID() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
