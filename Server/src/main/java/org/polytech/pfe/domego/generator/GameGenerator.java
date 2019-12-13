package org.polytech.pfe.domego.generator;

import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.List;

public interface GameGenerator {

    List<Activity> getAllActivitiesOfTheGame();

    int getNumberOfDaysWanted();

    int getCostWanted();

    int getNumberOfRisksDrawnWanted();
}
