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
            this.session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            logger.warning("CANT sendErrorCuzMissingArgument : " + message + " at session :" + session.getRemoteAddress());
            e.printStackTrace();
        }
    }


    public void sendErrorCuzMissingArgument(String missingArgument){
        JsonObject response = new JsonObject();
        response.addProperty("response","KO");
        response.addProperty("reason","MISSING ARGUMENT : " + missingArgument);

        try {
            this.session.sendMessage(new TextMessage(response.toString()));
        } catch (IOException e) {
            logger.warning("CANT sendErrorCuzMissingArgument : " + response.toString());
            e.printStackTrace();
        }
    }

    public void sendError(String error){
        JsonObject response = new JsonObject();
        response.addProperty("response","KO");
        response.addProperty("reason",error);


        try {
            this.session.sendMessage(new TextMessage(response.toString()));
        } catch (IOException e) {
            logger.warning("CANT sendError : " + response.toString());
            e.printStackTrace();
        }
    }
}