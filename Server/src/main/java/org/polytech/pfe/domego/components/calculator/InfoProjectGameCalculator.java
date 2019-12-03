package org.polytech.pfe.domego.components.calculator;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ActivityStatus;
import org.polytech.pfe.domego.models.activity.PayResourceType;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InfoProjectGameCalculator {

    private Game game;

    public InfoProjectGameCalculator(Game game) {
        this.game = game;
    }

    public int getMinResourcesToPay(RoleType roleType){
        List<PayResources> listPayResources = new ArrayList<>();
        this.game.getActivities().stream().forEach(activity -> activity.getPayResourcesList().stream().filter( payResources -> payResources.getRoleID() == roleType.getId()).forEach(listPayResources::add));
        int minResourcesToPay = 0;
        minResourcesToPay += listPayResources.stream()
                .filter(payResources ->
                        payResources.hasPaid() && !payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .mapToInt(PayResources::getAmountPaid).sum();
        minResourcesToPay += listPayResources.stream().
                filter(payResources ->
                        payResources.getPayResourceType().equals(PayResourceType.MANDATORY))
                .mapToInt(payResource -> Collections.min(payResource.getPriceAndBonusMap().keySet())).sum();
        return minResourcesToPay;
    }

    public int getMaxResourcesToPay(RoleType roleType){
        List<PayResources> listPayResources = new ArrayList<>();
        this.game.getActivities().stream().forEach(activity -> activity.getPayResourcesList().stream().filter( payResources -> payResources.getRoleID() == roleType.getId()).forEach(listPayResources::add));
        int maxResourcesToPay = 0;
        maxResourcesToPay += listPayResources.stream()
                .filter(payResources ->
                        payResources.hasPaid())
                .mapToInt(PayResources::getAmountPaid).sum();
        maxResourcesToPay += listPayResources.stream()
                .filter(payResources -> !payResources.hasPaid())
                .mapToInt(payResource -> Collections.max(payResource.getPriceAndBonusMap().keySet())).sum();
        return maxResourcesToPay;
    }

    public int getMinTimeOfProject(){
        List<Activity> activities = game.getActivities();
        int minTime = 0;
        for (Activity activity : activities) {
            minTime += activity.getNumberOfDays();
            if (activity.getActivityStatus().equals(ActivityStatus.FINISHED)){
                minTime -= activity.getPayResourcesList().stream()
                        .filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.DAYS))
                        .mapToInt(PayResources::getBonusGiven).sum();

            }
            else{
                minTime -= activity.getPayResourcesList().stream()
                        .filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.DAYS))
                        .mapToInt(payResource -> Collections.max(payResource.getPriceAndBonusMap().values())).sum();
            }
        }
        return minTime;
    }

    public int getMaxTimeOfProject(){
        List<Activity> activities = game.getActivities();
        int maxTime = 0;
        for (Activity activity : activities) {
            maxTime += activity.getNumberOfDays();
            if (activity.getActivityStatus().equals(ActivityStatus.FINISHED))
            {
                for (PayResources payResources: activity.getPayResourcesList().stream().filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.DAYS)).collect(Collectors.toList())) {
                    maxTime -= payResources.getBonusGiven();
                }
            }
        }
        return maxTime;
    }

    public int getMaxFailureOfProject(){
        List<Activity> activities = game.getActivities();
        int maxTime = 0;
        for (Activity activity : activities) {
            maxTime += activity.getNumberOfDays();
            if (activity.getActivityStatus().equals(ActivityStatus.FINISHED))
            {
                for (PayResources payResources: activity.getPayResourcesList().stream().filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.RISKS)).collect(Collectors.toList())) {
                    maxTime -= payResources.getBonusGiven();
                }
            }
        }
        return maxTime;
    }

    public int getMinFailureOfProject(){
        return 1;
    }


}
