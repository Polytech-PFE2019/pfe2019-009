package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassicActivity extends Activity {
    public ClassicActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList) {
        super(id, numbersOfDays, title ,description, payResourcesList);
    }


    @Override
    public int getExchangeRateForRoleID(int roleID) {
        return 0;
    }

    @Override
    public List<Integer> getBuyingRoleIDList() {
        return null;
    }

    @Override
    public Optional<Negotiation> getNegotiationByID(String id) {
        return Optional.empty();
    }

    @Override
    public void checkForMultiplicityForOneRole() {

    }
}
