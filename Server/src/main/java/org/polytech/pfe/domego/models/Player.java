package org.polytech.pfe.domego.models;

import com.google.gson.JsonObject;
import org.springframework.web.socket.WebSocketSession;

public class Player {
    private WebSocketSession session;
    private String socketID;
    private String name;
    private Role role;
    private boolean ready = false;
    private int points = 0;

    public Player(WebSocketSession session, String name ){
        this.session = session;
        this.socketID = session.getId();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSocketID() {
        return socketID;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return role;
    }

    public int getPoints(){
        return points;
    }

    public boolean isReady() {
        return ready;
    }

    public void changeReady(){
        if(ready){
            ready = false;
        }
        else{
            ready = true;
        }
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    public void addPoints(int points){
        this.points += points;
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
}
