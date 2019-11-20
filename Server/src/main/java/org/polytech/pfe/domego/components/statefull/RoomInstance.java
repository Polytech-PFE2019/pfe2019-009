package org.polytech.pfe.domego.components.statefull;



public class RoomInstance
{

    //private List<Room> rooms;

    private RoomInstance() {
    }

    private static RoomInstance INSTANCE = new RoomInstance();

    public static RoomInstance getInstance() {
        return INSTANCE;
    }
}