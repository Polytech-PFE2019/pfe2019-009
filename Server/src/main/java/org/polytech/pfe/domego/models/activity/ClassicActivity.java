package org.polytech.pfe.domego.models.activity;

import java.util.List;

public class ClassicActivity extends Activity {
    public ClassicActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList) {
        super(id, numbersOfDays, title ,description, payResourcesList);
    }


    @Override
    public int getExchangeRateForRoleID(int roleID) {
        return 0;
    }

}
