package org.polytech.pfe.domego.services.http.room;

import org.polytech.pfe.domego.components.business.Room;
import org.polytech.pfe.domego.database.accessor.RoomAccessor;
import org.polytech.pfe.domego.exceptions.room.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RoomWebService implements RoomService {

    private final RoomAccessor roomAccessor;

    @Autowired
    public RoomWebService(RoomAccessor roomAccessor) {
        this.roomAccessor = roomAccessor;
    }

    @Override
    @GetMapping(value = "/Rooms")
    public ResponseEntity getAllSalons() {
        List<Map<String, Object>> response = new ArrayList<>();
        this.roomAccessor.getAllRooms().forEach(room -> response.add(createResponseForRoom(room)));
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping(value = "/Room/{id}")
    public ResponseEntity getSalonById(@PathVariable String id) {
        Optional<Room> room = this.roomAccessor.getRoomById(id);
        if(room.isPresent())
            return ResponseEntity.ok(createResponseForRoom(room.get()));

        throw new RoomNotFoundException(id);
    }

    @Override
    @GetMapping(value = "/NumberRooms")
    public ResponseEntity<String> getTotalOfRoom() {
        return ResponseEntity.ok(String.valueOf(roomAccessor.getNumberOfRoom()));
    }

    private Map<String, Object> createResponseForRoom(Room room){
        Map<String, Object> map = new HashMap<>();
        map.put("roomID", room.getID());
        map.put("creator", room.getRoomName());
        map.put("numberOfPlayer",room.getNumberOfPlayer());
        return map;
    }
}
