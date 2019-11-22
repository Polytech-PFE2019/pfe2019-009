package org.polytech.pfe.domego.models;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testEachRoomAsDifferentID() {
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < 50000; i++) {
            rooms.add(new Room(UUID.randomUUID().toString()));
        }

        List<String> idList = rooms.stream().map(room -> room.getID()).collect(Collectors.toList());
        Set<Room> uniqueRooms = rooms.stream().collect(Collectors.toSet());
        assertEquals(uniqueRooms.size(),rooms.size());
        assertTrue(verifyAllAreDifferent(idList));


    }


    public boolean verifyAllAreDifferent(List<String> list) {
        return new HashSet<>(list).size() == list.size();
    }
}