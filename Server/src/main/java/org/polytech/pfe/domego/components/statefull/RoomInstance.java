package org.polytech.pfe.domego.components.statefull;


import org.polytech.pfe.domego.components.business.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomInstance
{

    private List<Room> roomList;

    private RoomInstance() {
        this.roomList = new ArrayList<>();

    }

    public boolean addRoom(Room room){
        return roomList.add(room);
    }

    public int numberOfRooms(){
        return roomList.size();
    }

    public List<Room> getRoomList(){
        return roomList;
    }

    public boolean removeRoom(Room room){return roomList.remove(room);}

    public Optional<Room> getRoomById(String roomId){
        return roomList.stream().filter(room -> room.getID().equals(roomId)).findAny();
    }

    private static RoomInstance INSTANCE = new RoomInstance();

    public static RoomInstance getInstance() {
        return INSTANCE;
    }
}