package org.polytech.pfe.domego;

import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.Room;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RoomRequestHandler {

    private RoomInstance roomInstance = RoomInstance.getInstance();

    public RoomRequestHandler(){
    }

    public void handleRequest(WebSocketSession session, Map<String, String> value) throws Exception {


        if(value.get("request")==null) {
            throw new Exception("bad request : must be of type {\"request\":\"REQUEST_NAME\'}");
        }
        String requestName = value.get("request");

        switch(requestName){
            case "CREATE_GAME" :
                if(value.get("username")==null) {
                    session.sendMessage(new TextMessage("{response : \"KO\", message : \"bad request\"}"));

                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',username:\"USERNAME\"}");
                }
                handleRoomCreation(session, value.get("username"));
                break;
            case "JOIN_GAME" :
                if(value.get("username")==null || value.get("roomID")==null) {
                    session.sendMessage(new TextMessage("{response : \"KO\", message : \"bad request\"}"));

                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',username:\"USERNAME\",roomID:ROOMID}");
                }
                handleJoiningRoom(session, Integer.parseInt(value.get("roomID")),value.get("username"));
                break;

            case "CHOOSE_ROLE" :
                if(value.get("roomID")==null || value.get("userID")==null || value.get("roleID")==null) {
                    session.sendMessage(new TextMessage("{response : \"KO\", message : \"bad request\"}"));

                    throw new Exception("bad request : must be of type {request:\"REQUEST_NAME\',userID:\"userID\",roomID:ROOMID,roleID:ROLEID}");
                }
                handleChoosingRole(session, Integer.parseInt(value.get("roomID")),value.get("userID"),Integer.parseInt(value.get("roleID")));
        }
    }

    private void handleRoomCreation(WebSocketSession session, String username) throws IOException {
        System.out.println(username);
        Room room = new Room("La Room",RoomInstance.getRoomList().size());

        Player player = new Player(session,username);
        room.addPlayer(player);
        RoomInstance.addRoom(room);
        System.out.println(RoomInstance.numberOfRooms()+" rooms");

        session.sendMessage(new TextMessage(room.createResponseRequest(player.getSocketID())));

    }

    private void handleJoiningRoom(WebSocketSession session,  int roomID, String username) throws IOException {
        Room room = RoomInstance.getRoomByID(roomID);

        Player player = new Player(session,username);
        room.addPlayer(player);
        System.out.println(RoomInstance.getRoomByID(roomID).getPlayerList().size()+" players in room of roomID"+roomID);

        session.sendMessage(new TextMessage(room.createResponseRequest(player.getSocketID())));

        updateRoomForAllPlayers(room,player);
    }

    //TODO Corriger cette partie
    private void handleChoosingRole(WebSocketSession session,  int roomID, String playerID, int roleID) throws IOException {
        Room room = RoomInstance.getRoomByID(roomID);

        Player player = room.getPlayerByID(playerID);

        RoleType roleType = RoleType.getRoleType(roleID);

        //Role role = new Role()

        //player.setRole(role);


        //Player updatedPlayer = RoomInstance.getRoomByID(roomID).getPlayerByID(playerID);
        //System.out.println(updatedPlayer.getName()+" has now the role : "+updatedPlayer.getRole().getName());

        session.sendMessage(new TextMessage(responseOK()));

        updateRoomForAllPlayers(room,player);


    }

    private void updateRoomForAllPlayers(Room room, Player playerAlreadyUpdated) throws IOException {

        for(Player player : room.getPlayerList()){
            if(!playerAlreadyUpdated.equals(player)) {
                player.getSession().sendMessage(new TextMessage(room.createUpdateResponse()));
            }
        }
    }

    private String responseOK(){
        JsonObject response = new JsonObject();
        response.addProperty("Request", "OK");
        return response.toString();
    }

    private String responseKO(){
        JsonObject response = new JsonObject();
        response.addProperty("Request", "KO");
        return response.toString();
    }
}

