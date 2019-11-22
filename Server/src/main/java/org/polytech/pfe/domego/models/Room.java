package org.polytech.pfe.domego.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.socket.WebSocketSession;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;
import java.util.UUID;

public class Room{

    private String id;
    private String roomName;
    private List<Player> playerList;
    private Game game;

    public Room(String roomName){
        this.roomName = roomName;
        this.playerList = new ArrayList<>();
        this.id = UUID.randomUUID().toString();

    }

    public Room(String roomName, int id){
        this.roomName = roomName;
        this.playerList = new ArrayList<>();
        this.id = String.valueOf(id);

    }

    public boolean addPlayer(Player player){
        if (this.isFull())
            return false;
        return this.playerList.add(player);
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayerList(){
        return playerList;
    }

    public boolean isFull(){
        return this.playerList.size() >= 6;
    }

    public String getID(){
        return id;
    }

    public void removePlayer(Player playerToRemove){
        this.playerList = this.playerList.stream().filter(player -> !player.equals(playerToRemove)).collect(Collectors.toList());
    }

    public String createResponseRequest(String userID) {

        JsonObject response = new JsonObject();
        response.addProperty("response", "UPDATE");
        response.addProperty("roomID", this.id);
        response.addProperty("userID", userID);

        JsonArray players = new JsonArray();
        for (Player player : playerList) {
            players.add(player.createResponseRequest());
        }
        response.addProperty("players", players.toString());

        return response.toString();

    }

    public String createUpdateResponse() {

        JsonObject response = new JsonObject();
        response.addProperty("response", "UPDATE");
        response.addProperty("roomID", this.id);

        JsonArray players = new JsonArray();
        for (Player player : playerList) {
            players.add(player.createResponseRequest());
        }
        response.addProperty("players", players.toString());

        return response.toString();

    }

    public Player getPlayerByID(String playerID){
        return playerList.stream().filter(player -> playerID.equals(player.getSocketID())).findAny().orElse(null);
    }

    public void createGame(List<Player> players){
        this.game = new Game(players,Integer.valueOf(this.id));
    }

    public Game getGame(){
        return this.game;
    }

    public int getNumberOfPlayer(){
        return this.playerList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
