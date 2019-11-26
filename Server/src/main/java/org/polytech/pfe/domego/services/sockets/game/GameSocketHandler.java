package org.polytech.pfe.domego.services.sockets.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.DisconnectManager;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.models.*;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ClassicActivity;
import org.polytech.pfe.domego.protocol.game.UpdateGameEvent;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GameSocketHandler extends TextWebSocketHandler {

    private final RequestHandler requestHandler;

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
            e.printStackTrace();

            JsonObject response = new JsonObject();
            response.addProperty("request", "OK");
            response.addProperty("message", "bad value");

            session.sendMessage(new TextMessage(response.toString()));
        }


    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connexion");
        List<Player> playerList = new ArrayList<>();
        for(int i =1; i<7; i++){
            Player player = new Player(session,"ehffd");
            player.setRole(new Role(i, RoleType.MAITRE_D_OUVRAGE,"descri",20000,"skjd"));
            player.addResouces(2);
            player.addMoney(20000);
            playerList.add((player));
        }

        Game game = new Game("id",playerList);
        GameInstance.getInstance().addGame(game);

        UpdateGameEvent event = new UpdateGameEvent(game);
        event.processEvent();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    }
}