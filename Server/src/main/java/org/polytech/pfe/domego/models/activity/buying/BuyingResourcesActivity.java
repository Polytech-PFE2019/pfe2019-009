package org.polytech.pfe.domego.models.activity.buying;

import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;
import java.util.stream.Collectors;

public class BuyingResourcesActivity  extends Activity implements BuyingAction {

    private List<BuyResources> buyResourcesList;

    public BuyingResourcesActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList, List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        this.buyResourcesList = buyResourcesList;
    }


    @Override
    public int getExchangeRateForRoleID(int roleID){
        BuyResources action = getBuyResourcesByRoleID(roleID);
        return action.getRate();
    }

    public void buyResources(Player player, int amount) {
        BuyResources action = getBuyResourcesByRoleID(player.getRole().getId());
        action.buyResources(amount);
        player.addResources(amount);
        player.subtractMoney(amount * action.getRate());
    }

    public BuyResources getBuyResourcesByRoleID(int roleID){
        return buyResourcesList.stream().filter(buyResources -> buyResources.getRoleID() == roleID).findAny().orElse(new BuyResources(roleID,2));
    }

    public List<Integer> getBuyingRoleIDList(){
        return buyResourcesList.stream().map(BuyResources::getRoleID).collect(Collectors.toList());
    }

    @Override
    public List<BuyResources> getBuyResourcesList() {
        return buyResourcesList;
    }
}
