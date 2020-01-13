package org.polytech.pfe.domego.models.risk;

public enum BonusType {

    MONEY("MONEY"),
    RESOURCES("RESOURCES"),
    DAYS("DAYS"),
    RISK("RISK"),
    QUALITY("QUALITY"),
    NOTHING("NOTHING");

    final String key;

    BonusType(String s) {
        this.key = s;
    }
}
