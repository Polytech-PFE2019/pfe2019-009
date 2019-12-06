package org.polytech.pfe.domego.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "Roles")
public class Role {

    @Id
    private int id;

    private RoleType name;

    @JsonIgnore
    private String description;

    private int budget;

    @JsonIgnore
    private String special;

    private List<Objective> objectiveList;

    public Role() {
        this.id = RoleType.NON_DEFINI.getId();
        this.name = RoleType.NON_DEFINI;
        this.description = "";
        this.budget = 0;
        this.special = "";
    }

    public Role(int id, RoleType name, String description, int budget, String special, List<Objective> objectiveList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.special = special;
        this.objectiveList = objectiveList;
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

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Objective> getObjectiveList() {
        return objectiveList;
    }
}
