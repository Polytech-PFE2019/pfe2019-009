package org.polytech.pfe.domego.protocol.game;

import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;


public class JoinGameEvent implements EventProtocol {

    private Messenger messenger;
    private Map<String, String> request;

    public JoinGameEvent(WebSocketSession session,Map<String, String> request) {
        this.request = request;
        this.messenger = new Messenger(session);
    }

    @Override
    public void processEvent() {


    }

}
