package org.polytech.pfe.domego.models;

import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayerTest {

    //TODO
    @Test
    void testResponse(){
        WebSocketSession session = mock(WebSocketSession.class) ;
        Player player = new Player(session, "name");

        Role role = new Role(1,RoleType.MAITRE_D_OUVRAGE,"description",2030,"special");

        player.setRole(role);
    }
}