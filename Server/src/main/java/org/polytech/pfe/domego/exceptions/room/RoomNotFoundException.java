package org.polytech.pfe.domego.exceptions.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String id) {
        super("Le lobby avec l'id " + id + " est INTROUVABLE.");
    }
}