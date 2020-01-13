package org.polytech.pfe.domego.models;

import org.polytech.pfe.domego.models.risk.Bonus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Qualities")
public class QualityAction {

    @Id
    private Integer id;

    private int qualityOfActivityId;

    private String description;

    private Bonus bonus;

    public QualityAction(int qualityOfActivityId, String description,Bonus bonus) {
        this.qualityOfActivityId = qualityOfActivityId;
        this.description = description;
        this.bonus = bonus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQualityOfActivityId() {
        return qualityOfActivityId;
    }

    public void setQualityOfActivityId(int qualityOfActivityId) {
        this.qualityOfActivityId = qualityOfActivityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityAction that = (QualityAction) o;
        return qualityOfActivityId == that.qualityOfActivityId &&
                Objects.equals(description, that.description) &&
                Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualityOfActivityId, description, bonus);
    }
}
