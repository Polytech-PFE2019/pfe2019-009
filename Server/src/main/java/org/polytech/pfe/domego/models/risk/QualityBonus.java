package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

public class QualityBonus implements Bonus {

    private final static BonusType bonusType = BonusType.QUALITY;
    private int amount;
    private int activityIdAssociate;

    public QualityBonus(int activityIdAssociate, int amount) {
        this.amount = amount;
        this.activityIdAssociate = activityIdAssociate;
    }

    @Override
    public BonusType getBonusType() {
        return bonusType;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getActivityID() {
        return activityIdAssociate;
    }

    @Override
    public RoleType getRoleType() {
        return RoleType.NON_DEFINI;
    }

    @Override
    public JsonElement transformToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RiskResponseKey.TYPE.getKey(), this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.AMOUNT.getKey(), this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.getKey(), this.activityIdAssociate);
        return jsonObject;
    }
}
