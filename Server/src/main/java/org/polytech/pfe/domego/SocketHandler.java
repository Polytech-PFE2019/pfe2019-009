package org.polytech.pfe.domego;

import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;
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

    private List sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //Map value = new Gson().fromJson(message.getPayload(), Map.class);
        //session.sendMessage(new TextMessage());
		/*for(WebSocketSession webSocketSession : sessions) {
			Map value = new Gson().fromJson(message.getPayload(), Map.class);
			webSocketSession.sendMessage(new TextMessage("Hello " + value.get("name") + " !"));
		}*/
        session.sendMessage(new TextMessage("Hello " + "toto" + " !"));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        //the messages will be broadcasted to all users.
        sessions.add(session);
    }
}