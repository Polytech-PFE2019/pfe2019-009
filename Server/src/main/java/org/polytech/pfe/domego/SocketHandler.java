package org.polytech.pfe.domego;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.models.Room;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException, Exception {
        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);

		/*for(WebSocketSession webSocketSession : sessions) {
			webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
		}*/
       // session.sendMessage(new TextMessage("Hello 2"));

        try {
            new RoomRequestHandler().handleRequest(session,value);
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
        //the messages will be broadcasted to all users.
        System.out.println("NEW USER");
        sessions.add(session);
        //session.sendMessage(new TextMessage("Hello"));
    }

}