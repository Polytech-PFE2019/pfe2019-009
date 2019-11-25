package org.polytech.pfe.domego.protocol;

import org.polytech.pfe.domego.components.buisness.Messenger;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.springframework.web.socket.WebSocketSession;

public class InvalidEvent implements EventProtocol {

    private Messenger messenger;

    public InvalidEvent(WebSocketSession user) {
        this.messenger = new Messenger(user);
    }

    @Override
    public void processEvent() {
        this.messenger.sendError("Unknown event");

    }
}
