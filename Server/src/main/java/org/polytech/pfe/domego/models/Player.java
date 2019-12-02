package org.polytech.pfe.domego.models;

import org.polytech.pfe.domego.models.activity.Negociation;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private WebSocketSession session;
    private String id;
    private String name;
    private Role role;
    private int resourcesAmount;
    private int money;
    private List<Negociation> negociationList;

    public Player(WebSocketSession session, String name ){
        this.session = session;
        this.id = UUID.randomUUID().toString();
        this.role = new Role();
        this.name = name;
        this.resourcesAmount = 0;
        this.money = 0;
        this.negociationList = new ArrayList<>();
    }

    public Player(Player player) {
        this.id = player.id;
        this.role = player.role;
        this.name = player.name;
        this.resourcesAmount = player.resourcesAmount;
        this.money = player.money;
        this.session = null;
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
    }

    public void addNegociation(Negociation negociation){
        this.negociationList.add(negociation);
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
