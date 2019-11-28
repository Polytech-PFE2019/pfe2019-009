package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResources;

import java.util.List;

public class NegociationActivity extends Activity implements NegociationAction {

    public NegociationActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList);
    }

    @Override
    public int getExchangeRateForRoleID(int roleID) {
        return 0;
    }

    @Override
    public void buyResources(int roleID, int amount) {

    }
}
