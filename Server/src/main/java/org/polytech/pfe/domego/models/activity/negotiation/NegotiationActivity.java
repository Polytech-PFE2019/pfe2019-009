package org.polytech.pfe.domego.models.activity.negotiation;

import org.polytech.pfe.domego.components.game.card.QualityCard;
import org.polytech.pfe.domego.components.game.card.RiskCard;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NegotiationActivity extends Activity implements NegotiationAction {

    private List<Negotiation> negotiationList;

    public NegotiationActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList,  List<RiskCard> riskCards, List<Negotiation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards);
        this.negotiationList = negociationList;
        checkForMultiplicityForOneRole();
    }

    public NegotiationActivity(int id, int numbersOfDays, String title , String description, List<PayResources> payResourcesList, List<RiskCard> riskCards, List<QualityCard> qualityCards , List<Negotiation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList, riskCards, qualityCards);
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


    @Override
    public boolean allNegotiationsAreFinished(){
        return negotiationList.stream()
                .allMatch(negotiation -> negotiation.getNegotiationStatus().equals(NegotiationStatus.FAILURE)
                        ||  negotiation.getNegotiationStatus().equals(NegotiationStatus.SUCCESS));

    }

}
