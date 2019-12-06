package org.polytech.pfe.domego.models.activity.negotiation;

import java.util.Optional;

public interface NegotiationAction {

    Optional<Negotiation> getNegotiationByID(String id);
    void checkForMultiplicityForOneRole();
    boolean allNegotiationsAreFinished();
}
