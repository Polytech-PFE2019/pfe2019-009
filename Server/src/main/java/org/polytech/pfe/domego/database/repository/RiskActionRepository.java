package org.polytech.pfe.domego.database.repository;

import org.polytech.pfe.domego.models.risk.RiskAction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskActionRepository extends MongoRepository<RiskAction, Integer> {

    List<RiskAction> getAllByRiskOfActivityId(int riskOfActivityId);
}
