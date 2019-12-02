package org.polytech.pfe.domego.models;

public enum PayResourceType {
    MANDATORY(1),
    RISKS(2),
    DAYS(3);

    private int id;
    PayResourceType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
