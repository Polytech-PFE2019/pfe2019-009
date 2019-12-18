package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

import java.util.Objects;

public class ResourcesBonus implements Bonus{

    private BonusType bonusType = BonusType.RESOURCES;

    private RoleType roleType;

    private int activityID;

    private int amount;

    public ResourcesBonus(RoleType roleType, int activityID, int amount) {
        this.roleType = roleType;
        this.activityID = activityID;
        this.amount = amount;
    }

    @Override
    public JsonElement transformToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RiskResponseKey.TYPE.getKey(), this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.ROLE_ID.getKey(), this.roleType.getId());
        jsonObject.addProperty(RiskResponseKey.AMOUNT.getKey(), this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.getKey(), this.activityID);

        return jsonObject;
    }

    @Override
    public BonusType getBonusType() {
        return this.bonusType;
    }

    @Override
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourcesBonus that = (ResourcesBonus) o;
        return activityID == that.activityID &&
                amount == that.amount &&
                bonusType == that.bonusType &&
                roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusType, roleType, activityID, amount);
    }

    @Override
    public String toString() {
        return "ResourcesBonus{" +
                "roleType=" + roleType +
                ", activityID=" + activityID +
                ", amount=" + amount +
                '}';
    }
}
