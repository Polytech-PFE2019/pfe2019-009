package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.Player;

import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BuyingResourcesActivity  extends Activity {

    private List<BuyResources> buyResourcesList;

    public BuyingResourcesActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList, List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        this.buyResourcesList = buyResourcesList;
    }


    @Override
    public int getExchangeRateForRoleID(int roleID){
        BuyResources action = getBuyResourcesByRoleID(roleID);
        return action.getRate();
    }

    @Override
    public void buyResources(Player player, int amount) {
        BuyResources action = getBuyResourcesByRoleID(player.getRole().getId());
        action.buyResources(amount);
        player.addResources(amount);
        player.subtractMoney(amount * action.getRate());
    }

    @Override
    public BuyResources getBuyResourcesByRoleID(int roleID){
        return buyResourcesList.stream().filter(buyResources -> buyResources.getRoleID() == roleID).findAny().orElse(new BuyResources(roleID,2));
    }

    @Override
    public List<Integer> getBuyingRoleIDList(){
        return buyResourcesList.stream().map(BuyResources::getRoleID).collect(Collectors.toList());
    }

    @Override
    public Optional<Negotiation> getNegotiationByID(String id) {
        return Optional.empty();
    }

    @Override
    public void checkForMultiplicityForOneRole() {

    }
}
