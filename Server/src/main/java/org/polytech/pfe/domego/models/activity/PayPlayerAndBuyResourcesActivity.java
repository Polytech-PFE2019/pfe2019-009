package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.pay.PayPlayer;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayPlayerAndBuyResourcesActivity extends Activity {

    private List<PayPlayer> payPlayerList;
    protected List<BuyResources> buyResourcesList;

    public PayPlayerAndBuyResourcesActivity(int id, int numbersOfDays, String title, String description,
                                            List<PayResources> payResourcesList, List<PayPlayer> payPlayerList,
                                            List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title, description, payResourcesList);
        this.payPlayerList = payPlayerList;
        this.buyResourcesList = buyResourcesList;
    }


    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giverID
                && payPlayer.getNegotiation().getReceiverRoleID() == receiverID ).findAny();
    }

    public Optional<PayPlayer> getPayPlayerByID(String id){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getId().equals(id)).findAny();
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

    private BuyResources getBuyResourcesByRoleID(int roleID){
        return buyResourcesList.stream().filter(buyResources -> buyResources.getRoleID() == roleID).findAny().orElse(new BuyResources(roleID,2));
    }

    public List<Integer> getBuyingRoleIDList(){
        return buyResourcesList.stream().map(BuyResources::getRoleID).collect(Collectors.toList());
    }


}
