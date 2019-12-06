package org.polytech.pfe.domego.models;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayerTest {

    @Test
    void testResponse(){
        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special", new ArrayList());
        player.setRole(role);

        JsonObject response = new JsonObject();
        response.addProperty("username", "name");
        response.addProperty("roleID", 1);

        assertEquals(response, createResponseRequest(player));
    }

    public JsonObject createResponseRequest(Player player){
        JsonObject response = new JsonObject();
        response.addProperty("username", player.getName());
        response.addProperty("roleID", player.getRole().getId());
        return response;
    }

    @Test
    void testRole(){
        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special", new ArrayList<>());

        assertEquals(RoleType.NON_DEFINI, player.getRole().getName());
        player.setRole(role);
        assertEquals(RoleType.MAITRE_D_OUVRAGE, player.getRole().getName());

    }
}