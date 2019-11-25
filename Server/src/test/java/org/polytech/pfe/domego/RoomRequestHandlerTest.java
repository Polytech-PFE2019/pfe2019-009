package org.polytech.pfe.domego;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.services.sockets.room.RoomRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoomRequestHandlerTest {
    private final RoleAccessor roleAccessor;

    @Mock
    WebSocketSession sessionPlayerTest = mock(WebSocketSession.class) ;
    @Autowired
    RoomRequestHandlerTest(RoleAccessor roleAccessor) {
        this.roleAccessor = roleAccessor;
    }
    RoomRequestHandler handler;

    RoomInstance roomInstance = RoomInstance.getInstance();

    String playerTestID;


    //Create a new room for each test
    //Juste one before all tests doesn't work
    @BeforeEach
    public void setUp() throws Exception {
        handler = new RoomRequestHandler(roleAccessor);
        JsonObject request = new JsonObject();
        request.addProperty("request","CREATE_GAME");
        request.addProperty("username", "name");
        Map value = new Gson().fromJson(request, Map.class);

        when(sessionPlayerTest.isOpen()).thenReturn(true);

        handler.handleRequest(sessionPlayerTest, value);
    }

    @Test
    public void testRoomCreation() throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("request","CREATE_GAME");
        request.addProperty("username", "name");

        Map value = new Gson().fromJson(request, Map.class);


        int numberOfRooms =  roomInstance.getRoomList().size();

        handler.handleRequest(sessionPlayerTest, value);

        //Check a room was created
        assertEquals(numberOfRooms+1, roomInstance.getRoomList().size());

        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        playerTestID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(0).getID();
        // Verify that response was sent
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(roomInstance.getRoomByID(lastRoomCreatedID).createResponseRequest(playerTestID)));
    }

    //In this test the update response sent to all others players in room is tested
    @Test
    public void testJoiningRoomAndUpdateResponse() throws Exception {

        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        WebSocketSession sessionPlayerTest2 = mock(WebSocketSession.class) ;


        JsonObject request2 = new JsonObject();
        request2.addProperty("request","JOIN_GAME");
        request2.addProperty("username", "name2");
        request2.addProperty("roomID", ""+lastRoomCreatedID);
        Map value2 = new Gson().fromJson(request2, Map.class);

        when(sessionPlayerTest2.isOpen()).thenReturn(true);



        //assert the room has 1 player
        assertEquals(1, roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().size());
        handler.handleRequest(sessionPlayerTest2, value2);

        //Check the room has now 2 players
        assertEquals(2, roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().size());
        String player2ID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(1).getID();

        // Verify that response was sent
        verify(sessionPlayerTest2, times(1)).sendMessage(
                new TextMessage(roomInstance.getRoomByID(lastRoomCreatedID).createResponseRequest(player2ID)));

        // Verify response was sent to other player
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(roomInstance.getRoomByID(lastRoomCreatedID).createUpdateResponse()));
    }

    // can't mock the socketID so we changed for UUID.
    @Test
    public void testChangeStatus() throws Exception {


        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        String playerID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(0).getID();

        JsonObject request2 = new JsonObject();
        request2.addProperty("request","CHANGE_STATUS");
        request2.addProperty("userID", playerID);
        request2.addProperty("roomID", ""+lastRoomCreatedID);
        Map value2 = new Gson().fromJson(request2, Map.class);

        handler.handleRequest(sessionPlayerTest, value2);

        // check player status is now true
        assertTrue(roomInstance.getRoomByID(lastRoomCreatedID).getPlayerByID(playerID).isReady());

        // Verify that response was sent

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHANGE_STATUS");
        response.addProperty("ready", roomInstance.getRoomByID(lastRoomCreatedID).getPlayerByID(playerID).isReady());
        response.addProperty("userID", playerID);
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));

    }

    @Test
    public void testChoosingRole() throws Exception {


        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        String playerID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(0).getID();

        JsonObject request2 = new JsonObject();
        request2.addProperty("request","CHOOSE_ROLE");
        request2.addProperty("userID", playerID);
        request2.addProperty("roomID", ""+lastRoomCreatedID);
        request2.addProperty("roleID", "1");


        Map value2 = new Gson().fromJson(request2, Map.class);

        //asserts Role is none
        assertEquals(RoleType.NON_DEFINI,roomInstance.getRoomByID(lastRoomCreatedID).getPlayerByID(playerID).getRole().getName());

        handler.handleRequest(sessionPlayerTest, value2);

        //checks player role is now maitre d'ouvrage (role id = 1)
        assertEquals(RoleType.MAITRE_D_OUVRAGE,roomInstance.getRoomByID(lastRoomCreatedID).getPlayerByID(playerID).getRole().getName());


        // Verify that response was sent

        JsonObject response = new JsonObject();
        response.addProperty("response", "CHOOSE_ROLE");
        response.addProperty("roomID", lastRoomCreatedID);
        response.addProperty("userID", playerID);
        response.addProperty("roleID", 1);

        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));
    }

    //TODO Check creation game object in gameInstance
    @Test
    public void testStartingGame() throws Exception {
        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        String playerID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(0).getID();

        JsonObject request2 = new JsonObject();
        request2.addProperty("request","START_GAME");
        request2.addProperty("userID", ""+playerID );
        request2.addProperty("roomID", ""+lastRoomCreatedID);
        Map value2 = new Gson().fromJson(request2, Map.class);


        handler.handleRequest(sessionPlayerTest, value2);

        // Verify that response was sent

        JsonObject response = new JsonObject();
        response.addProperty("response", "START_GAME");

        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));


    }

    @Test
    public void testLeavingRoom() throws Exception {
        int lastRoomCreatedID = roomInstance.getRoomList().size()-1;

        String playerID =roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().get(0).getID();

        JsonObject request2 = new JsonObject();
        request2.addProperty("request","LEAVE_ROOM");
        request2.addProperty("userID", ""+playerID );
        request2.addProperty("roomID", ""+lastRoomCreatedID);
        Map value2 = new Gson().fromJson(request2, Map.class);




        int numberOfPlayers = roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().size();

        handler.handleRequest(sessionPlayerTest, value2);

        //Check the room has less 1 player
        assertEquals(numberOfPlayers-1, roomInstance.getRoomByID(lastRoomCreatedID).getPlayerList().size());

        JsonObject response = new JsonObject();
        response.addProperty("response", "LEAVE_ROOM");
        response.addProperty("userID", playerID);
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));
    }



}