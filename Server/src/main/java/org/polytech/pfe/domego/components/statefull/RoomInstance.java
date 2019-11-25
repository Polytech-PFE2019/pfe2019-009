package org.polytech.pfe.domego.components.statefull;


import org.polytech.pfe.domego.models.Room;

import java.util.ArrayList;
import java.util.List;

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

    private static RoomInstance INSTANCE = new RoomInstance();

    public static RoomInstance getInstance() {
        return INSTANCE;
    }
}