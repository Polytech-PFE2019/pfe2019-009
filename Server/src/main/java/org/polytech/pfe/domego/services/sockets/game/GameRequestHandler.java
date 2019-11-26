package org.polytech.pfe.domego.services.sockets.game;

import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.InvalidEvent;
import org.polytech.pfe.domego.protocol.game.BuyResourceEvent;
import org.polytech.pfe.domego.protocol.game.JoinGameEvent;
import org.polytech.pfe.domego.protocol.game.PayResourcesEvent;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Service
public class GameRequestHandler implements RequestHandler {

    @Override
    public void handleRequest(WebSocketSession session, Map<String, String> request) throws Exception {
        EventProtocol event;
        if(!request.containsKey("request")) {
            throw new Exception("bad request : must be of type {\"request\":\"REQUEST_NAME\'}");
        }
        String requestName = request.get("request");

        System.out.println("REQUETE : ");
        for (String key : request.keySet()) {
            System.out.println("KEY : " + key + " VALUE : " + request.get(key));
        }

        switch(GameEventKey.valueOf(requestName)){
            case BUY_RESOURCES :
                event = new BuyResourceEvent(session, request);
                break;
            case PAY_RESOURCES :
                event = new PayResourcesEvent(session,request);
                break;
            case JOIN_GAME:
                event = new JoinGameEvent(session,request);
                break;
            default:
                event = new InvalidEvent(session);
                break;
        }
        event.processEvent();
    }
}
