package org.polytech.pfe.domego.protocol.game.key;

public enum  RiskResponseKey {
    AMOUNT("amount"),
    TYPE("type"),
    ROLE_ID("roleID"),
    ACTIVITY_ID_ASSOCIATE("activityIdAssociate");

    public String key;

    RiskResponseKey(String key) {
        this.key = key;
    }
}
