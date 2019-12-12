package org.polytech.pfe.domego.models.risk;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "Risks")
public class RiskAction {

    @Id
    private Integer id;

    private int riskOfActivityId;

    private String description;

    private List<Bonus> bonusList;

    public RiskAction(int riskOfActivityId, String description, List<Bonus> bonusList) {
        this.riskOfActivityId = riskOfActivityId;
        this.description = description;
        this.bonusList = bonusList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRiskOfActivityId() {
        return riskOfActivityId;
    }

    public void setRiskOfActivityId(int riskOfActivityId) {
        this.riskOfActivityId = riskOfActivityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskAction that = (RiskAction) o;
        return riskOfActivityId == that.riskOfActivityId &&
                Objects.equals(description, that.description) &&
                Objects.equals(bonusList, that.bonusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskOfActivityId, description, bonusList);
    }

    @Override
    public String toString() {
        return "RiskAction{" +
                "riskOfActivityId=" + riskOfActivityId +
                ", description='" + description + '\'' +
                ", bonusList=" + bonusList +
                '}';
    }
}
