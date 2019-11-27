package org.polytech.pfe.domego.protocol.game.key;

public enum GameResponseKey {
    RESPONSE("response"),
    RESOURCES("resources"),
    MONEY("money"),
    GAMEID("gameID"),
    NOPC("numberOfPlayersConnected"),
    PLAYER("player"),
    USERID("userID"),
    AMOUNT("amount"),
    ROLEID("roleID"),
    USERNAME("username"),
    BONUSTYPE("bonusType"),
    ACTIVITIES("activities"),
    PLAYER_ID_LIST("playersID");


    public final String key;

    GameResponseKey(String key) {
        this.key = key;
    }
}
