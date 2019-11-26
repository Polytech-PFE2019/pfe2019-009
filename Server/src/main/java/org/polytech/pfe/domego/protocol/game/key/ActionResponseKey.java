package org.polytech.pfe.domego.protocol.game.key;

public enum ActionResponseKey {
    AMOUNT_TO_PAY("amountToPay"),
    BONUS_AMOUNT("bonusAmount"),
    PAY_TYPE("payType"),
    ROLEID("roleID"),
    STATUS("status"),
    RATE("rate"),
    RESOURCES_GIVEN("resourcesGiven"),
    MONEY_PAID("moneyPaid");


    public final String key;

    ActionResponseKey(String key) {
        this.key = key;
    }
}
