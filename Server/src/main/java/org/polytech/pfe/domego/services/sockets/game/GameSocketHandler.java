package org.polytech.pfe.domego.services.sockets.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class GameSocketHandler extends TextWebSocketHandler {

    private final RequestHandler requestHandler;
    private final Logger loggerGlobal = Logger.getGlobal();
    private final Logger logger = Logger.getLogger(GameSocketHandler.class.getName());

    @Autowired
    public GameSocketHandler(GameRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map value = new Gson().fromJson(message.getPayload(), Map.class);

        try {
            requestHandler.handleRequest(session,value);
        }catch(Exception e){
            loggerGlobal.warning("GameSocketHandler : Impossible to recognize an event : " + message.getPayload());

            JsonObject response = new JsonObject();
            response.addProperty("request", "OK");
            response.addProperty("message", "bad value");

            session.sendMessage(new TextMessage(response.toString()));
        }


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("GameSocketHandler : New user on socket with ip : " + session.getRemoteAddress().toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        logger.info("GameSocketHandler : Close session on socket with ip : " + session.getRemoteAddress().toString());
    }
}