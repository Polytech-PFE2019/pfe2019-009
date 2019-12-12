package org.polytech.pfe.domego.protocol.game.key;

public enum GameResponseKey {
    RESPONSE("response"),
    DRAW_RISK("drawRisk"),
    FINISH("FINISH"),
    RANKING("ranking"),
    RANK("rank"),
    RESOURCES("resources"),
    UPDATE_PAYMENT("UPDATE_PAYMENT"),
    LAUNCH_GAME("LAUNCH_GAME"),
    ACTIVITY_ID("activityID"),
    INITIAL_DAYS("initialDays"),
    INITIAL_RISKS("initialRisks"),
    ACTUAL_DAYS("actualDays"),
    ACTUAL_RISKS("actualRisks"),
    EXTRA_PAYING("extraPaying"),
    MONEY("money"),
    TYPE("type"),
    GAME_ID("gameID"),
    NOPC("numberOfPlayersConnected"),
    PLAYER("player"),
    PROJECT("project"),
    USER_ID("userID"),
    AMOUNT("amount"),
    AMOUNT_PAID("amountPaid"),
    ROLE_ID("roleID"),
    USERNAME("username"),
    MIN_COST("minCost"),
    MAX_COST("maxCost"),
    MIN_TIME("minTime"),
    MAX_TIME("maxTime"),
    MIN_FAILURE("minFailure"),
    MAX_FAILURE("maxFailure"),
    BONUSTYPE("bonusType"),
    CURRENTACTIVITYID("currentActivityID"),
    DELAYPROJECT("delayProject"),
    COSTPROJECT("costProject"),
    FAILUREPROJECT("failureProject"),
    ACTIVITIES("activities"),
    GIVERID("giverID"),
    RECEIVERID("receiverID"),
    NEGOCIATIONID("negociationID"),
    MESSAGE("message"),
    DECLINETYPE("declineType"),
    BONUS("bonus"),
    DESCRIPTION("description"),
    RISK_OF_ACTIVITY_ID("riskOfActivityId"),
    RISKS("risks"),
    BONUS_TYPE("bonusType"),
    CURRENT_ACTIVITY_ID("currentActivityID"),
    DELAY_PROJECT("delayProject"),
    COST_PROJECT("costProject"),
    COST("cost"),
    DAYS("days"),

    FAILURE_PROJECT("failureProject"),
    PAYMENTS("payments"),
    CHANGE_ACTIVITY("CHANGE_ACTIVITY"),
    NOVP("NumberOfVictoryPoints"),
    OTHER_USER_NAME("otherUserName"),
    GIVER_ROLE_NAME("giverRoleName"),
    RECEIVER_ROLE_NAME("receiverRoleName"),
    TIME("time"),
    INFORMATION("information"),

    CONTRACTS_GIVER("contractsGiver"),
    CONTRACTS_RECEIVER("contractsReceiver");

    public final String key;

    GameResponseKey(String key) {
        this.key = key;
    }
}
