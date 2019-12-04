package org.polytech.pfe.domego.models.activity.pay;

import java.util.Optional;

public interface PayContractAction {

    Optional<PayContract> getPayPlayerByRoleIDs(int giverID, int receiverID);
    Optional<PayContract> getPayPlayerByID(String id);
}
