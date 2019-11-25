package org.polytech.pfe.domego.models;

import com.google.gson.JsonObject;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

public class Player {
    private WebSocketSession session;
    private String id;
    private String name;
    private Role role;
    private boolean ready = false;
    private int resourcesAmount;
    private int money;

    public Player(WebSocketSession session, String name ){
        this.session = session;
        this.id = session.getId();
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
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


    public boolean isReady() {
        return ready;
    }

    public void changeReady(){
        ready = !ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String createResponseRequest(){

        JsonObject response = new JsonObject();
        response.addProperty("username", name);
        response.addProperty("ready", ready);
        response.addProperty("roleID", (role != null ? role.getId() : 0));

        return response.toString();
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
