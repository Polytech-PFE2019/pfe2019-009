package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

public class DaysBonus implements Bonus{

    private BonusType bonusType = BonusType.DAYS;

    private int activityID;

    private int amount;

    public DaysBonus(int activityID, int days) {
        this.activityID = activityID;
        this.amount = days;
    }

    @Override
    public BonusType getBonusType() {
        return this.bonusType;
    }

    public int getActivityID() {
        return activityID;
    }

    @Override
    public RoleType getRoleType() {
        return RoleType.NON_DEFINI;
    }

    @Override
    public JsonElement transformToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RiskResponseKey.TYPE.key, this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.AMOUNT.key, this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.key, this.activityID);
        return jsonObject;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int days) {
        this.amount = days;
    }
}
