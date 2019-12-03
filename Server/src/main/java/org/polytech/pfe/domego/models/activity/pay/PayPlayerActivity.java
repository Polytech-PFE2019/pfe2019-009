package org.polytech.pfe.domego.models.activity.pay;

import org.polytech.pfe.domego.models.activity.Activity;

import java.util.List;
import java.util.Optional;

public class PayPlayerActivity extends Activity {
    private List<PayPlayer> payPlayerList;

    public PayPlayerActivity(int id, int numbersOfDays, String title, String description, List<PayResources> payResourcesList, List<PayPlayer> payPlayerList) {
        super(id, numbersOfDays, title, description, payResourcesList);
        this.payPlayerList = payPlayerList;
    }

    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giverID
                && payPlayer.getNegotiation().getReceiverRoleID() == receiverID ).findAny();
    }

    public Optional<PayPlayer> getPayPlayerByID(String id){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getId().equals(id)).findAny();
    }

    @Override
    public List<PayPlayer> getPayPlayerList() {
        return this.payPlayerList;
    }
}
