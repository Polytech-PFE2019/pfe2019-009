package org.polytech.pfe.domego.models.risk;

import com.google.gson.JsonElement;
import org.polytech.pfe.domego.models.RoleType;

public interface Bonus {
    BonusType getBonusType();

    int getAmount();

    int getActivityID();

    RoleType getRoleType();

    JsonElement transformToJson();





}
