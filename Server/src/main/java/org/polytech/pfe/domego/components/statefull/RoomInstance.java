package org.polytech.pfe.domego.components.statefull;


import org.polytech.pfe.domego.models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomInstance
{

    private static List<Room> roomList = new ArrayList<>();

    private RoomInstance() {
    }

    public static void addRoom(Room room){
        roomList.add(room);
    }

    public static int numberOfRooms(){
        return roomList.size();
    }

    public static List<Room> getRoomList(){
        return roomList;
    }

    public static Room getRoomByID(int roomID){
        return roomList.get(roomID);
    }



    private static RoomInstance INSTANCE = new RoomInstance();

    public static RoomInstance getInstance() {
        return INSTANCE;
    }
}