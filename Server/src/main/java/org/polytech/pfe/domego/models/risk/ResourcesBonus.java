package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

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
        jsonObject.addProperty(RiskResponseKey.TYPE.key, this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.ROLE_ID.key, this.roleType.getId());
        jsonObject.addProperty(RiskResponseKey.AMOUNT.key, this.amount);
        jsonObject.addProperty(RiskResponseKey.ACTIVITY_ID_ASSOCIATE.key, this.activityID);

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
}
