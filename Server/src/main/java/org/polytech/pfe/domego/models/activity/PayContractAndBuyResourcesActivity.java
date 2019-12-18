package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayContractAndBuyResourcesActivity extends Activity {

    private List<PayContract> payContractList;
    protected List<BuyResources> buyResourcesList;

    public PayContractAndBuyResourcesActivity(int id, int numbersOfDays, String title, String description,
                                              List<PayResources> payResourcesList, List<RiskCard> riskCards, List<PayContract> payContractList,
                                              List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards);
        this.payContractList = payContractList;
        this.buyResourcesList = buyResourcesList;
    }

    public PayContractAndBuyResourcesActivity(int id, int numbersOfDays, String title, String description,
                                              List<PayResources> payResourcesList, List<RiskCard> riskCards, List<QualityCard> qualityCards,
                                              List<PayContract> payContractList,
                                              List<BuyResources> buyResourcesList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards, qualityCards);
        this.payContractList = payContractList;
        this.buyResourcesList = buyResourcesList;
    }


    public Optional<PayContract> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payContractList.stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giverID
                && payPlayer.getNegotiation().getReceiverRoleID() == receiverID ).findAny();
    }

    public Optional<PayContract> getPayPlayerByID(String id){
        return this.payContractList.stream().filter(payPlayer -> payPlayer.getId().equals(id)).findAny();
    }
    @Override
    public List<PayContract> getPayContractList() {
        return this.payContractList;
    }

    @Override
    public int getExchangeRateForRoleID(int roleID){
        BuyResources action = getBuyResourcesByRoleID(roleID);
        if (action.hasPaid())
            action = getBuyResourcesByRoleID(RoleType.NON_DEFINI.getId());
        return action.getRate();
    }

    @Override
    public void buyResources(Player player, int amount) {
        BuyResources action = getBuyResourcesByRoleID(player.getRole().getId());
        if (action.hasPaid())
            action = getBuyResourcesByRoleID(RoleType.NON_DEFINI.getId());
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

    @Override
    public List<BuyResources> getBuyResourcesList() {
        return this.buyResourcesList;
    }
}
