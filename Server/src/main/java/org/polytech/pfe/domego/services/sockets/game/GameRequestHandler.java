package org.polytech.pfe.domego.services.sockets.game;

import org.polytech.pfe.domego.exceptions.InvalidRequestException;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.InvalidEvent;
import org.polytech.pfe.domego.protocol.game.*;
import org.polytech.pfe.domego.protocol.game.negociation.*;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Service
public class GameRequestHandler implements RequestHandler {

    @Override
    public void handleRequest(WebSocketSession session, Map request) throws InvalidRequestException {
        EventProtocol event;
        if(!request.containsKey("request")) {
            throw new InvalidRequestException();
        }
        String requestName = String.valueOf(request.get("request"));

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
            case START_NEGOTIATE:
                event = new StartNegotiationEvent(session,request);
                break;
            case ESTABLISH_NEGOTIATE:
                event = new EstablishNegotiationEvent(session,request);
                break;
            case END_NEGOTIATE:
                event = new EndNegotiationEvent(session,request);
                break;
            case MSG_NEGOTIATE:
                event = new MessageNegotiationEvent(session,request);
                break;
            case PRICE_NEGOTIATE:
                event = new PriceNegotiationEvent(session,request);
                break;
            case DECLINE_NEGOTIATE:
                event = new DeclineNegotiationEvent(session,request);
                break;
            case BANKRUPTCY:
                event = new BankruptcyEvent(session, request);
                break;
            case MSG_GROUP_CHAT:
                event = new GroupChatMessageEvent(session, request);
                break;
            default:
                event = new InvalidEvent(session);
                break;
        }
        event.processEvent();
    }
}
