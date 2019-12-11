package org.polytech.pfe.domego.models.activity;

public enum PayResourceType {
    MANDATORY("MANDATORY",1),
    RISKS("RISKS",2),
    DAYS("DAYS",3),
    QUALITY("QUALITY",4);

    private final int id;
    private final String name;

    PayResourceType(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
