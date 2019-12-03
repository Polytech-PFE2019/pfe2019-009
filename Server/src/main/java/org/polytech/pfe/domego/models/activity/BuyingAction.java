package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.Player;

import java.util.List;

public interface BuyingAction {
    int getExchangeRateForRoleID(int roleID);
    List<Integer> getBuyingRoleIDList();
    void buyResources(Player player, int amount);
}
