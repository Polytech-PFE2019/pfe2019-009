package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResources;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.NegociationAction;

import java.util.List;

public class NegociationActivity extends Activity implements NegociationAction {

    public NegociationActivity(int id, int numbersOfDays, String description, List<PayResources> payResourcesList) {
        super(id, numbersOfDays, description, payResourcesList);
    }

    @Override
    public int getExchangeRateForRoleID(int roleID) {
        return 0;
    }

    @Override
    public void buyResources(int roleID, int amount) {

    }
}
