package org.polytech.pfe.domego.services.http.game;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.database.accessor.GameAccessor;
import org.polytech.pfe.domego.exceptions.game.GameNotFoundException;
import org.polytech.pfe.domego.models.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GameWebService implements GameService {

    private final GameAccessor gameAccessor;

    @Autowired
    public GameWebService(GameAccessor gameAccessor) {
        this.gameAccessor = gameAccessor;
    }

    @Override
    @GetMapping(value = "/Games")
    public ResponseEntity getAllGames() {
        return ResponseEntity.ok(gameAccessor.getAllOfGame());
    }

    @Override
    @GetMapping(value = "/Game/{id}")
    public ResponseEntity getGameById(@PathVariable String id) {
        Optional<Game> game = this.gameAccessor.getGameById(id);
        if(game.isPresent())
            return ResponseEntity.ok(game.get());

        throw new GameNotFoundException(id);
    }



    @GetMapping(value = "/Game/{id}/{activityId}")
    public ResponseEntity getActivityOfGame(@PathVariable String id, @PathVariable String activityId) {
        Optional<Game> game = this.gameAccessor.getGameById(id);
        if(game.isPresent()){
            Optional<Activity> activity = game.get().getActivities().stream().filter(activity1 -> activity1.getId() == Integer.valueOf(activityId)).findAny();
            if (activity.isPresent())
                return ResponseEntity.ok(activity.get());
        }


        throw new GameNotFoundException(id);
    }

    @GetMapping(value = "/Game/{id}/current")
    public ResponseEntity getCurrentActivityOfGame(@PathVariable String id) {
        Optional<Game> game = this.gameAccessor.getGameById(id);
        if(game.isPresent())
            return ResponseEntity.ok(game.get().getCurrentActivity());

        throw new GameNotFoundException(id);
    }

    @Override
    @GetMapping(value = "/NumberGames")
    public ResponseEntity<String> getTotalOfGame() {
        return ResponseEntity.ok(String.valueOf(gameAccessor.numberOfGame()));
    }
}
