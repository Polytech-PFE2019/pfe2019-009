package org.polytech.pfe.domego.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Roles")
public class Role {

    @Id
    private int id;

    private RoleType name;

    private String description;

    private int budget;

    private String special;
    //private List<Objective> objectives;
    private int roleID;



    public Role(int id, RoleType name, String description, int budget, String special) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.special = special;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

}
