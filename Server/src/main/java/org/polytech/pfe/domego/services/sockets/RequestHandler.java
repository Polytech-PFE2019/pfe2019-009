package org.polytech.pfe.domego.services.sockets;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public interface RequestHandler {

    void handleRequest(WebSocketSession session, Map<String, String> request) throws Exception;
}
