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

public class InfoProjectGameCalculator {

    private Game game;

    public InfoProjectGameCalculator(Game game) {
        this.game = game;
    }

    public int getMinResourcesToPay(RoleType roleType){
        List<PayResources> listPayResources = new ArrayList<>();
        this.game.getActivities().stream().
                filter(activity -> activity.getActivityStatus().equals(ActivityStatus.NOT_STARTED) || activity.getActivityStatus().equals(ActivityStatus.ONGOING)).
                forEach(activity -> activity.getPayResourcesList().stream().filter( payResources -> payResources.getRoleID() == roleType.getId())
                        .forEach(listPayResources::add));

        int minResourcesToPay = 0;
        minResourcesToPay += listPayResources.stream().
                filter(payResources ->
                        payResources.getPayResourceType().equals(PayResourceType.MANDATORY) && !payResources.hasPaid())
                .mapToInt(payResource -> Collections.min(payResource.getPriceAndBonusMap().keySet())).sum();
        return minResourcesToPay;
    }

    public int getMaxResourcesToPay(RoleType roleType){
        List<PayResources> listPayResources = new ArrayList<>();
        this.game.getActivities().stream().
                filter(activity -> activity.getActivityStatus().equals(ActivityStatus.NOT_STARTED) || activity.getActivityStatus().equals(ActivityStatus.ONGOING)).
                forEach(activity -> activity.getPayResourcesList().stream().filter( payResources -> payResources.getRoleID() == roleType.getId())
                        .forEach(listPayResources::add));
        int maxResourcesToPay = 0;
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
            if (activity.getActivityStatus().equals(ActivityStatus.NOT_STARTED) || activity.getActivityStatus().equals(ActivityStatus.ONGOING)){
                minTime -= activity.getPayResourcesList().stream()
                        .filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.DAYS) && !payResources.hasPaid())
                        .mapToInt(payResource -> Collections.max(payResource.getPriceAndBonusMap().values())).sum();
            }
        }
        return minTime;
    }

    public int getMaxTimeOfProject(){
        List<Activity> activities = game.getActivities();
        int maxTime = activities.stream().mapToInt(Activity::getNumberOfDays).sum();
        return maxTime;
    }

    public int getMaxFailureOfProject(){
        List<Activity> activities = game.getActivities();
        int maxTime = activities.stream().mapToInt(activity -> activity.getRiskCardList().size()).sum();
        return maxTime;
    }

    public int getMinFailureOfProject(){
        List<Activity> activities = game.getActivities();
        int minFailure = 0;
        for (Activity activity : activities) {
            minFailure += activity.getRiskCardList().size();
            if (activity.getActivityStatus().equals(ActivityStatus.NOT_STARTED) || activity.getActivityStatus().equals(ActivityStatus.ONGOING)){
                minFailure -= activity.getPayResourcesList().stream()
                        .filter(payResources -> payResources.getPayResourceType().equals(PayResourceType.RISKS) && !payResources.hasPaid())
                        .mapToInt(payResource -> Collections.max(payResource.getPriceAndBonusMap().values())).sum();
            }
        }
        return minFailure;
    }


}
