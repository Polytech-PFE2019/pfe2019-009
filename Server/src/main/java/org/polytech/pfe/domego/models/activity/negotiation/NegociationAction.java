package org.polytech.pfe.domego.models.activity.negotiation;

import java.util.Optional;

public interface NegociationAction {

    Optional<Negociation> getNegotiationByID(String id);
    void checkForMultiplicityForOneRole();
}
