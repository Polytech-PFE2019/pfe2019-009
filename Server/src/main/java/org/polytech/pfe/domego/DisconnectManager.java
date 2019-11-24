package org.polytech.pfe.domego;

import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.models.Player;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;

@Service
public class DisconnectManager {

    private RoomInstance roomInstance;

    public DisconnectManager() {
        roomInstance = RoomInstance.getInstance();
    }


    public void process(WebSocketSession session){
        roomInstance.getRoomList().stream().forEach(room ->
        {
            Optional<Player> optional = room.getPlayerList().stream().filter(player -> player.getSession().equals(session)).findFirst();
            if(optional.isPresent()){
                room.removePlayer(optional.get());
                System.out.println("Nous avons enlevé le joueur : " + optional.get().getName() + " de la partie : " + room.getID());
                return;
            }
        }
        );

    }
}
