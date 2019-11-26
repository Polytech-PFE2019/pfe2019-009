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

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special");

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

        JsonArray players = new JsonArray();
        players.add(player.createResponseRequest());
        players.add(player2.createResponseRequest());

        response.addProperty("players", players.toString());

        assertEquals(response.toString(),room.createResponseRequest(player.getID()));

    }

    @Test
    void testUpdateResponse(){
        Room room = new Room("test",0);

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special");

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

        JsonArray players = new JsonArray();
        players.add(player.createResponseRequest());
        players.add(player2.createResponseRequest());

        response.addProperty("players", players.toString());

        assertEquals(response.toString(),room.createUpdateResponse());

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
        assertEquals(player, room.getPlayerByID(player.getID()));
        assertEquals(player2, room.getPlayerByID(player2.getID()));
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
}