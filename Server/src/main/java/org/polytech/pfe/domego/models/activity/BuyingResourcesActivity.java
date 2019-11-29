package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.PayResources;

import java.util.List;
import java.util.stream.Collectors;

public class BuyingResourcesActivity  extends Activity implements BuyingAction {

    public BuyingResourcesActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList, List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        this.buyResourcesList = buyResourcesList;
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
        return buyResourcesList.stream().filter(buyResources -> buyResources.getRoleID() == roleID).findAny().orElse(new BuyResources(roleID,2));
    }

    public List<Integer> getBuyingRoleIDList(){
        return buyResourcesList.stream().map(BuyResources::getRoleID).collect(Collectors.toList());
    }
}
