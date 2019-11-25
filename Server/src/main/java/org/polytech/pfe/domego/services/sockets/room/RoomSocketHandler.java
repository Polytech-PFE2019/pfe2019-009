package org.polytech.pfe.domego.services.sockets.room;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.DisconnectManager;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

@Component
public class RoomSocketHandler extends TextWebSocketHandler {

    private final RequestHandler requestHandler;

    @Autowired
    public RoomSocketHandler(RoomRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map request = new Gson().fromJson(message.getPayload(), Map.class);

        try {
            requestHandler.handleRequest(session,request);
        }catch(Exception e){
            e.printStackTrace();

            JsonObject response = new JsonObject();
            response.addProperty("request", "OK");
            response.addProperty("message", "bad value");

            session.sendMessage(new TextMessage(response.toString()));
        }


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("NEW USER");

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("UN USER s'est déconnecté");
        new DisconnectManager().process(session);
    }
}