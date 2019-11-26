package org.polytech.pfe.domego.components.business;

import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.RoleType;

import java.util.*;
import java.util.stream.Collectors;

public class Room{

    private String id;
    private String roomName;
    private Map<Player,Boolean> playerList;

    public Room(String roomName){
        this.roomName = roomName;
        this.playerList = new LinkedHashMap<>();
        this.id = UUID.randomUUID().toString();

    }

    public Room(String roomName, int id){
        this.roomName = roomName;
        this.playerList = new LinkedHashMap<>();
        this.id = String.valueOf(id);

    }

    public boolean addPlayer(Player player){
        if (this.isFull())
            return false;
        int size = playerList.size();
        this.playerList.put(player,Boolean.FALSE);
        return size + 1 == playerList.size();
    }

    public String getRoomName() {
        return roomName;
    }

    public List<Player> getPlayerList(){
        return playerList.keySet().stream().collect(Collectors.toList());
    }

    public boolean isFull(){
        return this.playerList.size() >= 6;
    }

    public String getID(){
        return id;
    }

    public boolean everybodyIsReady(){
        return !playerList.containsValue(false);
    }

    public boolean everybodyGotARole(){
        return playerList.keySet().stream().noneMatch(player -> player.getRole().getName().getId() == RoleType.NON_DEFINI.getId());
    }

    public void removePlayer(Player playerToRemove){
        this.playerList.remove(playerToRemove);
    }


    public Optional<Player> getPlayerById(String playerID){
        return playerList.keySet().stream().filter(player -> player.getID().equals(playerID)).findAny();
    }

    public int getNumberOfPlayer(){
        return this.playerList.size();
    }

    public boolean changeStateOfPlayer(Player player){
        playerList.replace(player, !playerList.get(player));
        return playerList.get(player);
    }

    public String getHostID() {
        if (playerList.isEmpty())
            return "0";
        return playerList.keySet().iterator().next().getID();
    }

    public boolean playerIsReady(Player player){
        return playerList.get(player);
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
