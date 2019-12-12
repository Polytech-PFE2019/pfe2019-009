package org.polytech.pfe.domego.models.activity.pay;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.activity.Activity;

import java.util.List;
import java.util.Optional;

public class PayContractActivity extends Activity {
    private List<PayContract> payContractList;

    public PayContractActivity(int id, int numbersOfDays, String title, String description, List<PayResources> payResourcesList, List<RiskCard> riskCards,List<PayContract> payContractList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards);
        this.payContractList = payContractList;
    }

    public PayContractActivity(int id, int numbersOfDays, String title, String description, List<PayResources> payResourcesList, List<RiskCard> riskCards, List<QualityCard> qualityCards, List<PayContract> payContractList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards, qualityCards);
        this.payContractList = payContractList;
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
}
