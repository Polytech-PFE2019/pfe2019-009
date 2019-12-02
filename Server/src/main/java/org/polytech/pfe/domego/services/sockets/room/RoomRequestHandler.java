package org.polytech.pfe.domego.services.sockets.room;

import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.exceptions.InvalidRequestException;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.InvalidEvent;
import org.polytech.pfe.domego.protocol.room.*;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Logger;


@Service
public class RoomRequestHandler implements RequestHandler {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final RoleAccessor roleAccessor;


    @Autowired
    public RoomRequestHandler(RoleAccessor roleDB){
        this.roleAccessor = roleDB;
    }

    @Override
    public void handleRequest(WebSocketSession session, Map request) throws InvalidRequestException {
        EventProtocol event;
        if(!request.containsKey("request")) {
            throw new InvalidRequestException();
        }
        String requestName = String.valueOf(request.get("request"));

        switch(requestName){
            case "CREATE_GAME" :
                event = new CreateGameEvent(session, request);
                break;
            case "JOIN_GAME" :
                event = new JoinRoomEvent(session,request);
                break;
            case "CHOOSE_ROLE" :
                event = new ChooseRoleEvent(session,request,roleAccessor);
                break;
            case "CHANGE_STATUS" :
                event = new ChangeStatusEvent(session,request);
                break;
            case "START_GAME" :
                event = new StartGameEvent(session,request);
                break;
            case "LEAVE_ROOM" :
                event = new LeaveRoomEvent(session,request);
                break;
            default:
                event = new InvalidEvent(session);
                break;
        }
        logger.info("RoomRequestHandler : New Room request type : " + event.getClass());
        event.processEvent();
    }
}

