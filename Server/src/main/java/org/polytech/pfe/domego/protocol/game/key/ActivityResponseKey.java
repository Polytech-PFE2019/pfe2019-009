package org.polytech.pfe.domego.protocol.game.key;

public enum ActivityResponseKey {

    ACTIVITY_ID("activityID"),
    GAME_TYPE("gameType"),
    NUMBER_OF_DAYS("numberOfDays"),
    TITLE("title"),
    STATUS("status"),
    DESCRIPTION("description"),
    BUYING_ACTIONS("buyingActions"),
    PAYING_ACTIONS("payingActions"),
    NEGOTIATION_ACTIONS("negotiationActions"),
    PAY_CONTRACT_ACTION("payContractAction"),
    PLAYER_ID_LIST("playersID"),
    ROLE_ID_LIST("rolesID"),
    RISKS("risks");


    public final String key;

    ActivityResponseKey(String key) {
        this.key = key;
    }
}
