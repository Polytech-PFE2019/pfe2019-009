package org.polytech.pfe.domego;

import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DisconnectManager {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private RoomAccessor roomInstance;

    public DisconnectManager() {
        roomInstance = new RoomAccessor();
    }


    public void process(WebSocketSession session){
        roomInstance.getAllRooms().stream().forEach(room ->
        {
            Optional<Player> optional = room.getPlayerList().stream().filter(player -> player.getSession().equals(session)).findFirst();
            if(optional.isPresent()){
                room.removePlayer(optional.get());
                logger.info("DisconnectManager : Le joueur : " + optional.get().getName() + " s'est deconnecte de la partie du système, nous l'avons donc supprimer de la partie : " + room.getID() );
                return;
            }
        }
        );

    }
}
