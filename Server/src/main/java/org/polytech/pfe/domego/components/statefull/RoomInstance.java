package org.polytech.pfe.domego.components.statefull;


import org.polytech.pfe.domego.components.buisness.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomInstance
{

    private List<Room> roomList;

    private RoomInstance() {
        this.roomList = new ArrayList<>();

    }

    public void addRoom(Room room){
        roomList.add(room);
    }

    public int numberOfRooms(){
        return roomList.size();
    }

    public List<Room> getRoomList(){
        return roomList;
    }

    public Room getRoomByID(int roomID){
        return roomList.stream().filter(room -> room.getID().equals(String.valueOf(roomID))).findAny().get();
    }

    public Optional<Room> getRoomById(String roomId){
        return roomList.stream().filter(room -> room.getID().equals(roomId)).findAny();
    }

    private static RoomInstance INSTANCE = new RoomInstance();

    public static RoomInstance getInstance() {
        return INSTANCE;
    }
}