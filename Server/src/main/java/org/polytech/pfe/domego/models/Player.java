package org.polytech.pfe.domego.models;

import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

public class Player {
    private WebSocketSession session;
    private String id;
    private String name;
    private Role role;
    private int resourcesAmount;
    private int money;

    public Player(WebSocketSession session, String name ){
        this.session = session;
        this.id = session.getId();
        this.id = UUID.randomUUID().toString();
        this.role = new Role();
        this.name = name;
        this.resourcesAmount = 0;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return role;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public int getResourcesAmount() {
        return resourcesAmount;
    }


    public void addResouces(int amount){
        resourcesAmount += amount;
    }

    public void substractResources(int amount){
        resourcesAmount -= amount;
    }

    public int getMoney() {
        return money;
    }


    public void addMoney(int amount){
        money += amount;
    }

    public void substractMoney(int amount){
        money -= amount;
    }


}
