package org.polytech.pfe.domego.services.http.room;

import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public interface RoomService {

    ResponseEntity getAllSalons();

    ResponseEntity getSalonById(String id);

    ResponseEntity<String> getTotalOfRoom();
}
