package org.polytech.pfe.domego.protocol.game.key;

public enum ActivityResponseKey {

    ACTIVITY_ID("activityID"),
    NUMBER_OF_DAYS("numberOfDays"),
    STATUS("status"),
    DESCRIPTION("description"),
    BUYING_ACTIONS("buyingActions"),
    PAYING_ACTIONS("payingActions");

    public final String key;

    ActivityResponseKey(String key) {
        this.key = key;
    }
}
