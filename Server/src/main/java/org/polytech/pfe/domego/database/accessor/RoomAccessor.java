package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.polytech.pfe.domego.models.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomAccessor {

    private RoomInstance roomInstance;

    public RoomAccessor() {
        this.roomInstance = RoomInstance.getInstance();
    }

    public List<Room> getAllRooms(){
        return this.roomInstance.getRoomList();
    }

    public Optional<Room> getRoomById(String id){
        return this.roomInstance.getRoomList().stream().filter(room -> room.getID().equals(id)).findAny();
    }

    public int getNumberOfRoom(){
        return roomInstance.getRoomList().size();
    }
}
