package org.polytech.pfe.domego.models.activity.pay;

import java.util.Optional;

public interface PayPlayerAction {

    Optional<PayPlayer> getPayPlayerByRoleIDs(int giverID, int receiverID);
    Optional<PayPlayer> getPayPlayerByID(String id);
}
