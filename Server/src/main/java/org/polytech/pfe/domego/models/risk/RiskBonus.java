package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

import java.util.Objects;

public class RiskBonus implements Bonus {

    private BonusType bonusType = BonusType.RISK;

    private int activityID;

    private int amount;

    public RiskBonus(int activityID, int amount) {
        this.activityID = activityID;
        this.amount = amount;
    }

    @Override
    public BonusType getBonusType() {
        return this.bonusType;
    }

    @Override
    public int getActivityID() {
        return activityID;
    }

    @Override
    public RoleType getRoleType() {
        return RoleType.NON_DEFINI;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public JsonElement transformToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RiskResponseKey.TYPE.getKey(), this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.AMOUNT.getKey(), this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.getKey(), this.activityID);
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskBonus riskBonus = (RiskBonus) o;
        return activityID == riskBonus.activityID &&
                amount == riskBonus.amount &&
                bonusType == riskBonus.bonusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusType, activityID, amount);
    }

    @Override
    public String toString() {
        return "RiskBonus{" +
                "activityID=" + activityID +
                ", amount=" + amount +
                '}';
    }
}
