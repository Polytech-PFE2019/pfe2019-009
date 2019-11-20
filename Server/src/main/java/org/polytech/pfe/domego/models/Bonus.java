package org.polytech.pfe.domego.models;

import java.util.List;

public class Bonus {
    private List<RoleType> roleTypeList;
    private BonusType bonusType;
    private int amount;

    public Bonus(List<RoleType> roleTypeList, BonusType bonusType, int amount){
        this.roleTypeList = roleTypeList;
        this.bonusType = bonusType;
        this.amount = amount;
    }

    public List<RoleType> getRoleTypeList() {
        return roleTypeList;
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public int getAmount() {
        return amount;
    }
}
