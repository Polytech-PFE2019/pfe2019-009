package org.polytech.pfe.domego.services.sockets.room;

import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.InvalidEvent;
import org.polytech.pfe.domego.protocol.room.*;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.services.sockets.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.logging.Logger;


@Service
public class RoomRequestHandler implements RequestHandler {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private RoomInstance roomInstance;
    private final RoleAccessor roleAccessor;


    @Autowired
    public RoomRequestHandler(RoleAccessor roleDB){
        roomInstance = RoomInstance.getInstance();
        this.roleAccessor = roleDB;
    }

    @Override
    public void handleRequest(WebSocketSession session, Map<String, String> request) throws Exception {
        EventProtocol event;
        if(!request.containsKey("request")) {
            throw new Exception("bad request : must be of type {\"request\":\"REQUEST_NAME\'}");
        }
        String requestName = request.get("request");

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
        logger.info("New Room request type : " + event.getClass());
        event.processEvent();
    }
}
