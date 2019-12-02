package org.polytech.pfe.domego.models.activity;

import java.util.Optional;

public interface NegociationAction {

    Optional<Negociation> getNegocationByRoleIDs(int giverRoleID, int receiverRoleID);
}
