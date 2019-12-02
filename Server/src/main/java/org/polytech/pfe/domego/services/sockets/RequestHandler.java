package org.polytech.pfe.domego.services.sockets;

import org.polytech.pfe.domego.exceptions.InvalidRequestException;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public interface RequestHandler {

    void handleRequest(WebSocketSession session, Map request) throws InvalidRequestException;
}
