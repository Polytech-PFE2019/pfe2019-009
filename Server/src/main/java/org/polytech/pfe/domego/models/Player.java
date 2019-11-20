package org.polytech.pfe.domego.models;

public class Player {
    private String socketID;
    private String name;
    private Role role;
    private int points = 0;

    public Player(String socketID, String name){
        this.socketID = socketID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSocketID() {
        return socketID;
    }

    public void addPoints(int points){
        this.points += points;
    }
}
