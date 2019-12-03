package org.polytech.pfe.domego.models.activity.buying;

import org.polytech.pfe.domego.models.Player;

public interface BuyingAction {
    int getExchangeRateForRoleID(int roleID);
    void buyResources(Player player, int amount);
}
