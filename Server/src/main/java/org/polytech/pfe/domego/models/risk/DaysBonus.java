package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

import java.util.Objects;

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
        jsonObject.addProperty(RiskResponseKey.TYPE.getKey(), this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.AMOUNT.getKey(), this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.getKey(), this.activityID);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaysBonus daysBonus = (DaysBonus) o;
        return activityID == daysBonus.activityID &&
                amount == daysBonus.amount &&
                bonusType == daysBonus.bonusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusType, activityID, amount);
    }

    @Override
    public String toString() {
        return "DaysBonus{" +
                "activityID=" + activityID +
                ", amount=" + amount +
                '}';
    }
}
