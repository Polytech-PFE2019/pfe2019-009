package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;

public class ClassicActivity extends Activity {

    public ClassicActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList, List<RiskCard> riskCards) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards);
    }

    public ClassicActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList, List<RiskCard> riskCards, List<QualityCard> qualityCards) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards, qualityCards);
    }


}
