package org.polytech.pfe.domego.services.http.room;

import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity getAllSalons();

    ResponseEntity getSalonById(String id);

    ResponseEntity<String> getTotalOfRoom();
}
