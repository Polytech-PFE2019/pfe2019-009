package org.polytech.pfe.domego.protocol.game.key;

public enum GameResponseKey {
    RESPONSE("response"),
    FINISH("FINISH"),
    RANKING("ranking"),
    RANK("rank"),
    RESOURCES("resources"),
    UPDATE_PAYMENT("UPDATE_PAYMENT"),
    LAUNCH_GAME("LAUNCH_GAME"),
    ACTIVITY_ID("activityID"),
    MONEY("money"),
    TYPE("type"),
    GAME_ID("gameID"),
    NOPC("numberOfPlayersConnected"),
    PLAYER("player"),
    PROJECT("project"),
    USER_ID("userID"),
    AMOUNT("amount"),
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
    BONUS_TYPE("bonusType"),
    CURRENT_ACTIVITY_ID("currentActivityID"),
    DELAY_PROJECT("delayProject"),
    COST_PROJECT("costProject"),
    FAILURE_PROJECT("failureProject"),
    PAYMENTS("payments"),
    CHANGE_ACTIVITY("CHANGE_ACTIVITY"),
    NOVP("NumberOfVictoryPoints"),
    OTHER_USER_NAME("otherUserName"),
    TIME("time");

    public final String key;

    GameResponseKey(String key) {
        this.key = key;
    }
}
