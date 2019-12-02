package org.polytech.pfe.domego.models.activity;

import java.util.List;
import java.util.Optional;

public class NegociationActivity extends Activity implements NegociationAction {


    public NegociationActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList, List<Negociation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        super.negociationList = negociationList;
    }

    public Optional<Negociation> getNegocationByRoleIDs(int giverRoleID, int receiverRoleID){
        return negociationList.stream().filter(negociation -> negociation.getGiverRoleID() == giverRoleID && negociation.getReceiverRoleID() == receiverRoleID).findAny();
    }

}
