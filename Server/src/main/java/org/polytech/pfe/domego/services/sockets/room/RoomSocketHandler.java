package org.polytech.pfe.domego.services.sockets.room;

import com.google.gson.Gson;
import org.polytech.pfe.domego.DisconnectManager;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.exceptions.InvalidRequestException;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RoomSocketHandler extends TextWebSocketHandler {

    private final RequestHandler requestHandler;
    private final Logger loggerGlobal = Logger.getGlobal();
    private final Logger logger = Logger.getLogger(RoomSocketHandler.class.getName());

    @Autowired
    public RoomSocketHandler(RoomRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Map request = new Gson().fromJson(message.getPayload(), Map.class);

        try {
            requestHandler.handleRequest(session,request);
        }catch(InvalidRequestException e){
            loggerGlobal.warning("RoomSocketHandler : Impossible to recognize an event : " + message.getPayload());
            new Messenger(session).sendErrorCuzMissingArgument("REQUEST");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        logger.log(Level.INFO,"RoomSocketHandler : New user on socket with ip : {0}", session.getRemoteAddress().toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        logger.log(Level.INFO,"RoomSocketHandler : Close session on socket with ip : {0}", session.getRemoteAddress().toString());
        new DisconnectManager().process(session);
    }
}