package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResources;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.BuyingAction;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BuyingResourcesActivity  extends Activity implements BuyingAction {


    public BuyingResourcesActivity(int id, int numbersOfDays, String description, List<PayResources> payResourcesList, List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, description, payResourcesList);
        super.buyResourcesList = buyResourcesList;
    }

    @Override
    public int getExchangeRateForRoleID(int roleID){
        BuyResources action = getByResourcesByRoleID(roleID);
        return action.getRate();
    }

    @Override
    public void buyResources(int roleID, int amount) {
        BuyResources action = getByResourcesByRoleID(roleID);
        action.buyResources(amount);
    }

    private BuyResources getByResourcesByRoleID(int roleID){
        return buyResourcesList.stream().filter(buyResources -> buyResources.getRoleID() == roleID).findAny().orElse(null);
    }

    public List<Integer> getBuyingRoleIDList(){
        return buyResourcesList.stream().map(BuyResources::getRoleID).collect(Collectors.toList());
    }
}
