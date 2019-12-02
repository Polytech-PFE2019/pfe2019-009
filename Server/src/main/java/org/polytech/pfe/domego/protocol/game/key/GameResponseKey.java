package org.polytech.pfe.domego.protocol.game.key;

public enum GameResponseKey {
    RESPONSE("response"),
    RESOURCES("resources"),
    UPDATE_PAYMENT("UPDATE_PAYMENT"),
    LAUNCH_GAME("LAUNCH_GAME"),
    ACTIVITY_ID("activityID"),
    MONEY("money"),
    TYPE("type"),
    GAME_ID("gameID"),
    NOPC("numberOfPlayersConnected"),
    PLAYER("player"),
    USER_ID("userID"),
    AMOUNT("amount"),
    ROLE_ID("roleID"),
    USERNAME("username"),
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
    CHANGE_ACTIVITY("CHANGE_ACTIVITY");

    public final String key;

    GameResponseKey(String key) {
        this.key = key;
    }
}
