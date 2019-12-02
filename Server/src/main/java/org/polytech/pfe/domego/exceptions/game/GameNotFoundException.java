package org.polytech.pfe.domego.exceptions.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String id) {
        super("La partie avec l'id " + id + " est INTROUVABLE.");
    }
}