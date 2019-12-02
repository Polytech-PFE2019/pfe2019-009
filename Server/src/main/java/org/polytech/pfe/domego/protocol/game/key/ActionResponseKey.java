package org.polytech.pfe.domego.protocol.game.key;

public enum ActionResponseKey {
    AMOUNT_TO_PAY("amountToPay"),
    BONUS_AMOUNT("bonusAmount"),
    AMOUNT_PAID("amountPaid"),
    BONUS_GIVEN("bonusGiven"),
    PAY_TYPE("payType"),
    ROLEID("roleID"),
    STATUS("status"),
    RATE("rate"),
    RESOURCES_GIVEN("resourcesGiven"),
    MONEY_PAID("moneyPaid"),
    ACTIONS("actions"),
    GIVERID("giverID"),
    RECEIVERID("receiverID"),
    NEGOTIATIONID("negotiationID");

    public final String key;

    ActionResponseKey(String key) {
        this.key = key;
    }
}
