package org.polytech.pfe.domego.models.activity;

import java.util.List;

public interface BuyingAction {
    int getExchangeRateForRoleID(int roleID);
    List<Integer> getBuyingRoleIDList();
}
