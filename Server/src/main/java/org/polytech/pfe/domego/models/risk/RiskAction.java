package org.polytech.pfe.domego.models.risk;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
}
