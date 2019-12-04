package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.database.repository.RiskActionRepository;
import org.polytech.pfe.domego.models.risk.RiskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAccessor {

    private final RiskActionRepository riskDB;

    @Autowired
    public RiskAccessor(RiskActionRepository db) {
        this.riskDB = db;
        initDBRole();
    }

    public List<RiskAction> getAllRisk(){
        return riskDB.findAll();
    }

    public List<RiskAction> getRiskByActivityID(int activityId){ return riskDB.getAllByRiskOfActivityId(activityId);}


    void initDBRole(){


    }
}
