package org.polytech.pfe.domego.models.activity;

import java.util.List;
import java.util.Set;

public interface BuyingAction {
    int getExchangeRateForRoleID(int roleID);
    List<Integer> getBuyingRoleIDList();
}
