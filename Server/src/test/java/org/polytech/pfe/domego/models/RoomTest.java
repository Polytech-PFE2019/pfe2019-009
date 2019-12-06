package org.polytech.pfe.domego.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.polytech.pfe.domego.components.business.Room;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoomTest {

    @Test
    void testFull(){
        Room room = new Room("test");

        for(int i =0; i<6; i++){
            WebSocketSession session = mock(WebSocketSession.class) ;
            room.addPlayer(new Player(session, "name"));
        }

        assertEquals(6, room.getPlayerList().size());
        assertTrue(room.isFull());
        WebSocketSession session = mock(WebSocketSession.class) ;
        room.addPlayer(new Player(session, "name"));
        assertEquals(6, room.getPlayerList().size());

    }

    @Test
    void testResponse(){
        Room room = new Room("test",0);

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special", new ArrayList<>());

        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");
        room.addPlayer(player);
        Player player2 = new Player(session, "name2");
        room.addPlayer(player2);

        player.setRole(role);
        assertEquals(1,player.getRole().getId());

        JsonObject response = new JsonObject();
        response.addProperty("response", "UPDATE");
        response.addProperty("roomID", room.getID());
        response.addProperty("userID", player.getID());
        response.addProperty("hostID", room.getHostID());

        JsonArray players = new JsonArray();
        players.add(createResponseRequest(player, room));
        players.add(createResponseRequest(player2, room));

        response.add("players", players);

        assertEquals(response,createUpdateResponse(player,room));

    }

    @Test
    void testUpdateResponse(){
        Room room = new Room("test",0);

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special", new ArrayList<>());

        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");
        room.addPlayer(player);
        Player player2 = new Player(session, "name2");
        room.addPlayer(player2);

        player.setRole(role);
        assertEquals(1,player.getRole().getId());

        JsonObject response = new JsonObject();
        response.addProperty("response", "UPDATE");
        response.addProperty("roomID", "0");
        response.addProperty("userID", player.getID());
        response.addProperty("hostID", room.getHostID());

        JsonArray players = new JsonArray();
        players.add(createResponseRequest(player, room));
        players.add(createResponseRequest(player2,room));

        response.add("players", players);

        assertEquals(response,this.createUpdateResponse(player,room));

    }



    @Test
    void testGetPlayerByID(){
        Room room = new Room("test",0);

        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");
        room.addPlayer(player);
        Player player2 = new Player(session, "name2");
        room.addPlayer(player2);
        assertEquals(2, room.getPlayerList().size());

        assertTrue(room.getPlayerById(player.getID()).isPresent());
        assertTrue(room.getPlayerById(player2.getID()).isPresent());
        assertEquals(player, room.getPlayerById(player.getID()).get());
        assertEquals(player2, room.getPlayerById(player2.getID()).get());
    }

    @Test
    void testEquals(){
        Room room = new Room("test",0);
        Room room2 = new Room("test",0);

        assertFalse(room.equals(room2));

        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");
        room.addPlayer(player);
        assertFalse(room.equals(room2));







    }

    @Test
    void testEachRoomAsDifferentID() {
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < 50000; i++) {
            rooms.add(new Room(UUID.randomUUID().toString()));
        }

        List<String> idList = rooms.stream().map(room -> room.getID()).collect(Collectors.toList());
        Set<Room> uniqueRooms = rooms.stream().collect(Collectors.toSet());
        assertEquals(uniqueRooms.size(),rooms.size());
        assertTrue(verifyAllAreDifferent(idList));


    }


    public boolean verifyAllAreDifferent(List<String> list) {
        return new HashSet<>(list).size() == list.size();
    }

    public JsonObject createResponseRequest(Player player, Room room){

        JsonObject response = new JsonObject();
        response.addProperty("username", player.getName());
        response.addProperty("ready", room.playerIsReady(player));
        response.addProperty("roleID", player.getRole().getId());

        return response;
    }

    public JsonObject createUpdateResponse(Player playerID, Room room) {

        JsonObject response = new JsonObject();
        response.addProperty("response", "UPDATE");
        response.addProperty("roomID", room.getID());
        response.addProperty("userID", playerID.getID());
        response.addProperty("hostID", room.getHostID());


        JsonArray players = new JsonArray();
        for (Player player : room.getPlayerList()) {
            players.add(createResponseRequest(player,room));
        }
        response.add("players", players);

        return response;

    }
}