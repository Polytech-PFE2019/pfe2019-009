package org.polytech.pfe.domego.models.activity;

import java.util.List;
import java.util.Optional;

public class NegociationActivity extends Activity implements NegociationAction {


    public NegociationActivity(int id, int numbersOfDays,String title ,String description, List<PayResources> payResourcesList, List<Negociation> negociationList) {
        super(id, numbersOfDays, title,description, payResourcesList);
        super.negociationList = negociationList;
    }

    public Optional<Negociation> getNegotiationByID(String id){
        return negociationList.stream().filter(negociation -> negociation.getId().equals(id)).findAny();
    }

}
