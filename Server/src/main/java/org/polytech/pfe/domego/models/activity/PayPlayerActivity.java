package org.polytech.pfe.domego.models.activity;

import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PayPlayerActivity extends Activity  {
    private List<PayPlayer> payPlayerList;

    public PayPlayerActivity(int id, int numbersOfDays, String title, String description, List<PayResources> payResourcesList, List<PayPlayer> payPlayerList) {
        super(id, numbersOfDays, title, description, payResourcesList);
        this.payPlayerList = payPlayerList;
    }

    @Override
    public Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getNegotiation().getGiverRoleID() == giverID
                && payPlayer.getNegotiation().getReceiverRoleID() == receiverID ).findAny();
    }

    @Override
    public Optional<PayPlayer> getPayPlayerByID(String id){
        return this.payPlayerList.stream().filter(payPlayer -> payPlayer.getId().equals(id)).findAny();
    }

    @Override
    public List<Integer> getBuyingRoleIDList() {
        return null;
    }

    //NegotiationAction abstract implementation

    @Override
    public Optional<Negotiation> getNegotiationByID(String id) {
        return Optional.empty();
    }

    @Override
    public void checkForMultiplicityForOneRole() {

    }
}
