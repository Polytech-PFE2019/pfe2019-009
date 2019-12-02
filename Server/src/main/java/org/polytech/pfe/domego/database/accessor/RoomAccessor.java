package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.components.statefull.RoomInstance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomAccessor {

    private RoomInstance roomInstance;

    public RoomAccessor() {
        this.roomInstance = RoomInstance.getInstance();
    }

    public boolean addRoom(Room room){ return this.roomInstance.addRoom(room);}

    public List<Room> getAllRooms(){
        return this.roomInstance.getRoomList();
    }

    public Optional<Room> getRoomById(String id){return this.roomInstance.getRoomById(id);}

    public boolean removeRoom(Room room){ return this.roomInstance.removeRoom(room);}

    public int getNumberOfRoom(){
        return roomInstance.numberOfRooms();
    }
}
