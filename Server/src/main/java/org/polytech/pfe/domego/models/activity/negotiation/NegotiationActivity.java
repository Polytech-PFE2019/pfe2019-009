package org.polytech.pfe.domego.models.activity.negotiation;

import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.PayResources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NegotiationActivity extends Activity implements NegotiationAction {

    private List<Negotiation> negotiationList;

    public NegotiationActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList, List<Negotiation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        this.negotiationList = negociationList;
        checkForMultiplicityForOneRole();
    }

    @Override
    public Optional<Negotiation> getNegotiationByID(String id){
        return negotiationList.stream().filter(negotiation -> negotiation.getId().equals(id)).findAny();
    }

    /**
     * if one role has several negotiations, multiply the time of each by the number of negotiations he has.
     */
    @Override
    public void checkForMultiplicityForOneRole(){
        for (RoleType role : RoleType.values()) {
            List<Negotiation> negotiationsForOneRole = negotiationList.stream().filter(negotiation -> negotiation.getGiverRoleID() == (role.getId())).collect(Collectors.toList());
            int number = negotiationsForOneRole.size();
            negotiationsForOneRole.forEach(negotiation -> {negotiation.multiplicateTime(number);});
        }
    }

    @Override
    public List<Negotiation> getNegotiationList() {
        return this.negotiationList;
    }

    //BuyResourcesAction abstract implementation
    @Override
    public List<Integer> getBuyingRoleIDList() {
        return null;
    }
}