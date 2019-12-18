package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.protocol.game.key.RiskResponseKey;

import java.util.Objects;

public class MoneyBonus implements Bonus {

    private BonusType bonusType = BonusType.MONEY;

    private RoleType roleType;

    private int amount;

    public MoneyBonus(RoleType roleType, int amount) {
        this.roleType = roleType;
        this.amount = amount;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public JsonElement transformToJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(RiskResponseKey.TYPE.getKey(), this.bonusType.key);
        jsonObject.addProperty(RiskResponseKey.AMOUNT.getKey(), this.amount);
        jsonObject.addProperty(RiskResponseKey.ROLE_ID.getKey(), this.roleType.getId());
        return jsonObject;
    }

    @Override
    public BonusType getBonusType() {
        return this.bonusType;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getActivityID() {
        return 0;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyBonus that = (MoneyBonus) o;
        return amount == that.amount &&
                bonusType == that.bonusType &&
                roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusType, roleType, amount);
    }

    @Override
    public String toString() {
        return "MoneyBonus{" +
                "roleType=" + roleType +
                ", amount=" + amount +
                '}';
    }
}
