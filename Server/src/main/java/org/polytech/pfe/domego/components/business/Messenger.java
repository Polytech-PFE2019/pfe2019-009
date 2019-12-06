package org.polytech.pfe.domego.components.business;

import com.google.gson.JsonObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.logging.Logger;

public class Messenger {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private WebSocketSession session;

    public Messenger(WebSocketSession session) {
        this.session = session;
    }


    public void sendSpecificMessageToAUser(String message){
        try {
            synchronized(session) {
                this.session.sendMessage(new TextMessage(message));
            }
        } catch (IOException e) {
            logger.warning("Messenger : can't sendErrorCuzMissingArgument : " + message + " at session :" + session.getRemoteAddress());
        }
    }


    public void sendErrorCuzMissingArgument(String missingArgument){
        JsonObject response = new JsonObject();
        response.addProperty("response","KO");
        response.addProperty("reason","MISSING ARGUMENT : " + missingArgument);
        try {
            synchronized(session) {
                this.session.sendMessage(new TextMessage(response.toString()));
            }
        } catch (IOException e) {
            logger.warning("Messenger : can't sendErrorCuzMissingArgument : " + response.toString());
        }
    }

    public void sendError(String error){
        JsonObject response = new JsonObject();
        response.addProperty("response","KO");
        response.addProperty("reason",error);
        try {
            synchronized(session) {
                this.session.sendMessage(new TextMessage(response.toString()));
            }
        } catch (IOException e) {
            logger.warning("Messenger : can't sendError : " + response.toString());
        }
    }
}
