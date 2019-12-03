package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;

public class ClassicActivity extends Activity {

    public ClassicActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList) {
        super(id, numbersOfDays, title ,description, payResourcesList);
    }


}
