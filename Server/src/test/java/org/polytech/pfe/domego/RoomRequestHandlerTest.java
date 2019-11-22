package org.polytech.pfe.domego;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

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


    @Before
    @Test
    public void testRoomCreation() throws Exception {
        handler = new RoomRequestHandler(roleAccessor);
        JsonObject request = new JsonObject();
        request.addProperty("request","CREATE_GAME");
        request.addProperty("username", "name");
        when(sessionPlayerTest.isOpen()).thenReturn(true);

        Map value = new Gson().fromJson(request, Map.class);

        handler.handleRequest(sessionPlayerTest, value);

        //Check a room was created
        assertEquals(1, roomInstance.getRoomList().size());

        // Verify that response was sent
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(roomInstance.getRoomByID(0).createResponseRequest(sessionPlayerTest.getId())));
    }

    @Test
    public void testJoiningRoom() throws Exception {
        handler = new RoomRequestHandler(roleAccessor);

        WebSocketSession session = mock(WebSocketSession.class);
        JsonObject request = new JsonObject();
        request.addProperty("request","JOIN_GAME");
        request.addProperty("username", "name2");
        request.addProperty("roomID", "0");
        Map value = new Gson().fromJson(request, Map.class);

        when(session.isOpen()).thenReturn(true);

        //assert the room has 1 player
        assertEquals(1, roomInstance.getRoomList().get(0).getPlayerList().size());
        handler.handleRequest(session, value);

        //Check the room has now 2 players
        assertEquals(2, roomInstance.getRoomList().get(0).getPlayerList().size());

        // Verify that response was sent
        verify(session, times(1)).sendMessage(
                new TextMessage(roomInstance.getRoomByID(0).createResponseRequest(session.getId())));
    }

    // can't mock the socketID (returns null) so it doesn't work
//    @Test
//    @Ignore
//    public void testChangeStatus() throws Exception {
//
//        JsonObject request = new JsonObject();
//        request.addProperty("request","CHANGE_STATUS");
//        request.addProperty("userID", sessionPlayerTest.getId());
//        request.addProperty("roomID", "0");
//
//        Map value = new Gson().fromJson(request, Map.class);
//        handler.handleRequest(sessionPlayerTest, value);
//
//        // check player status is now true
//        assertTrue(RoomInstance.getRoomList().get(0).getPlayerList().get(0).isReady());
//
//    }



}