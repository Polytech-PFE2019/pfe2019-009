package org.polytech.pfe.domego.generator;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.activity.Activity;

import java.util.List;

public interface GameGenerator {

    List<Activity> getAllActivitiesOfTheGame();

    Game generateGame();
}
