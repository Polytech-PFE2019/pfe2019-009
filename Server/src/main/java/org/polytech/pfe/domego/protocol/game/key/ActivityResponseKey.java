package org.polytech.pfe.domego.protocol.game.key;

public enum ActivityResponseKey {

    ACTIVITY_ID("activityID"),
    NUMBER_OF_DAYS("numberOfDays"),
    TITLE("title"),
    STATUS("status"),
    DESCRIPTION("description"),
    BUYING_ACTIONS("buyingActions"),
    PAYING_ACTIONS("payingActions"),
    PLAYER_ID_LIST("playersID"),
    RISKS("risks");


    public final String key;

    ActivityResponseKey(String key) {
        this.key = key;
    }
}
