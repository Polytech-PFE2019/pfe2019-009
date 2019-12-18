package org.polytech.pfe.domego.generator;

import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.activity.Activity;

import java.util.List;

public interface GameGenerator {

    List<Activity> getAllActivitiesOfTheGame();

    int getNumberOfDaysWanted();

    int getCostWanted();

    int getNumberOfRisksDrawnWanted();

    int getBudgetByRole(Role role);

}
