package org.polytech.pfe.domego.models;

import org.springframework.web.socket.WebSocketSession;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private String roomName;
    private List<Player> playerList;

    public Room(String roomName, int id){
        this.roomName = roomName;
        this.playerList = new ArrayList<>();
        this.id = id;
    }

    public void addPlayer(Player player){
        this.playerList.add(player);
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayerList(){
        return playerList;
    }

    public int getID(){
        return id;
    }

    public String createResponseRequest(String userID) {
        String response = "{response : \"OK\", roomID :" + this.id + ", userID :\"" + userID + "\"";
        response += "players : [";
        for (Player player : playerList) {
            response += player.createResponseRequest() + ",";
        }
        response += "]}";
        return response;

    }

    public String createUpdateResponse() {
        String response = "{response : \"UPDATE\", roomID :" + this.id+",";
        response += "players : [";
        for (Player player : playerList) {
            response += player.createResponseRequest() + ",";
        }
        response += "]}";
        return response;

    }

    public Player getPlayerByID(String playerID){
        return playerList.stream().filter(player -> playerID.equals(player.getSocketID())).findAny().orElse(null);
    }

}
