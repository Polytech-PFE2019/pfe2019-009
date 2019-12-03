package org.polytech.pfe.domego.models.activity.negotiation;

import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.PayResources;
import org.polytech.pfe.domego.models.activity.negotiation.Negociation;
import org.polytech.pfe.domego.models.activity.negotiation.NegociationAction;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NegociationActivity extends Activity implements NegociationAction {


    public NegociationActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList, List<Negociation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        super.negociationList = negociationList;
        checkForMultiplicityForOneRole();
    }

    public Optional<Negociation> getNegotiationByID(String id){
        return negociationList.stream().filter(negotiation -> negotiation.getId().equals(id)).findAny();
    }

    /**
     * if one role has several negotiations, multiply the time of each by the number of negotiations he has.
     */
    public void checkForMultiplicityForOneRole(){
        for (RoleType role : RoleType.values()) {
            List<Negociation> negotiationsForOneRole = negociationList.stream().filter(negotiation -> negotiation.getGiverRoleID() == (role.getId())).collect(Collectors.toList());
            int number = negotiationsForOneRole.size();
            negotiationsForOneRole.forEach(negotiation -> {negotiation.multiplicateTime(number);});
        }
    }

}
