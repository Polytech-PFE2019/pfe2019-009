package org.polytech.pfe.domego.services.http.game;

import org.springframework.http.ResponseEntity;

public interface GameService {

    ResponseEntity getAllGames();

    ResponseEntity getGameById(String id);

    ResponseEntity<String> getTotalOfGame();
}
