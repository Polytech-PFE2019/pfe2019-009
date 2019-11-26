package org.polytech.pfe.domego.services.http.game;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.game.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.logging.Logger;

@RestController
public class GameWebService implements GameService {

    private final GameAccessor gameAccessor;

    @Autowired
    public GameWebService(GameAccessor gameAccessor) {
        this.gameAccessor = gameAccessor;
    }

    @Override
    @RequestMapping(value = "/Games", method = RequestMethod.GET)
    public ResponseEntity getAllGames() {
        return ResponseEntity.ok(gameAccessor.getAllOfGame());
    }

    @Override
    @RequestMapping(value = "/Game/{id}", method = RequestMethod.GET)
    public ResponseEntity getGameById(@PathVariable String id) {
        Logger log = Logger.getLogger("MyLog");
        log.info("TOTO");
        Optional<Game> game = this.gameAccessor.getGameById(id);
        if(game.isPresent())
            return ResponseEntity.ok(game.get());

        throw new GameNotFoundException(id);
    }

    @Override
    @RequestMapping(value = "/NumberGames", method = RequestMethod.GET)
    public ResponseEntity<String> getTotalOfGame() {
        return ResponseEntity.ok(String.valueOf(gameAccessor.numberOfGame()));
    }
}
