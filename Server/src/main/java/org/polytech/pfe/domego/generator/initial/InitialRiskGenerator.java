package org.polytech.pfe.domego.generator.initial;

import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.risk.*;

import java.util.ArrayList;
import java.util.List;

public class InitialRiskGenerator {

    public InitialRiskGenerator() {
    }

    public List<RiskAction> getInitialRiskAction(){
        List<RiskAction> riskActions = new ArrayList<>();
        riskActions.addAll(listOfRiskForActivity1());
        riskActions.addAll(listOfRiskForActivity2());
        riskActions.addAll(listOfRiskForActivity3());
        riskActions.addAll(listOfRiskForActivity4());
        riskActions.addAll(listOfRiskForActivity5());
        riskActions.addAll(listOfRiskForActivity6());
        riskActions.addAll(listOfRiskForActivity7());
        riskActions.addAll(listOfRiskForActivity8());
        riskActions.addAll(listOfRiskForActivity9());
        riskActions.addAll(listOfRiskForActivity10());
        riskActions.addAll(listOfRiskForActivity11());
        riskActions.addAll(listOfRiskForActivity12());
        return riskActions;
    }

    private List<RiskAction> listOfRiskForActivity1(){
        List<RiskAction> riskActions = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,1,2));
        riskActions.add(new RiskAction(1,"Manque de ressources humaines internes", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(2,1));
        riskActions.add(new RiskAction(1,"Chef de projet inexpérimenté", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(3,1));
        riskActions.add(new RiskAction(1,"Chef de projet maitrisant mal les aspects administratifs", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(12,10));
        riskActions.add(new RiskAction(1,"Chef de projet trop perfectionniste", bonusList));
        bonusList = new ArrayList<>();
        riskActions.add(new RiskAction(1,"Invasion de moustiques", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,5));
        riskActions.add(new RiskAction(1,"Appui financier de l’Etat", bonusList));
        return riskActions;
    }

    private List<RiskAction> listOfRiskForActivity2(){
        int currentActivityId = 2;
        List<RiskAction> riskActionsActivity2 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-10));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Défaut d’une banque", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(9,10));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Manque d’analyse géotechnique", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(2,5));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Impératif sur les délais", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(10,1));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Pas de concertation avec le voisinage", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Café pas bon", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(10,1));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Projet apprécié par le voisinage", bonusList));
        return riskActionsActivity2;
    }

    private List<RiskAction> listOfRiskForActivity3(){
        int currentActivityId = 3;
        List<RiskAction> riskActionsActivity3 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(3,5));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Négociation longue", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Incompréhension entre les parties", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(12,5));
        bonusList.add(new RiskBonus(12,1));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Contrat mal rédigé", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-3));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Erreur sur les comptes", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Travaux à coté du bureau", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-3));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Bonne confiance entre les parties", bonusList));
        return riskActionsActivity3;
    }

    private List<RiskAction> listOfRiskForActivity4(){
        int currentActivityId = 4;
        List<RiskAction> riskActionsActivity4 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Négociation longue", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Incompréhension entre les parties", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(12,5));
        bonusList.add(new RiskBonus(12,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Contrat mal rédigé", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-3));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Erreur sur les comptes", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Grève des transports", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-5));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Bonne confiance entre les parties", bonusList));
        return riskActionsActivity4;
    }

    private List<RiskAction> listOfRiskForActivity5(){
        int currentActivityId = 5;
        List<RiskAction> riskActionsActivity5 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Manque de ressources humaines internes", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(6,1));
        bonusList.add(new RiskBonus(7,1));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Architecte inexpérimenté", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(6,1));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Phobie administrative de l’architecte", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(12,2));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Architecte pas du tout rigoureux", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Crevaison sur la route", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(6,-1));
        bonusList.add(new RiskBonus(7,-1));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Architecte expérimenté", bonusList));
        return riskActionsActivity5;
    }

    private List<RiskAction> listOfRiskForActivity6(){
        int currentActivityId = 6;
        List<RiskAction> riskActionsActivity6 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,7, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,7, 1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Erreur sur des plans", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,7, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,7, 2));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Solution extravagante", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 2));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Problème de communication", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Week-end pluvieux", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Oubli d’un élément", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-8));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Idée pertinente de l’architecte", bonusList));
        return riskActionsActivity6;
    }

    private List<RiskAction> listOfRiskForActivity7(){
        int currentActivityId = 7;
        List<RiskAction> riskActionsActivity7 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,10, 1));
        bonusList.add(new MoneyBonus(RoleType.BUREAU_D_ETUDE,-3));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Erreur sur les calculs", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,10, 2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,11, 2));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Flou dans les spécifications", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,7));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 2));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Nouvelle réglementation non maitrisée", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Mauvaise entente entre les acteurs", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Plus d'encre dans l'imprimante", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-6));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Projet classique en termes de calculs", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId,1));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Démission d'un ingénieur", bonusList));
        return riskActionsActivity7;
    }

    private List<RiskAction> listOfRiskForActivity8(){
        int currentActivityId = 8;
        List<RiskAction> riskActionsActivity8 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Manque de ressources internes CES", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,10, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Manque de ressources internes structure", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 2));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Difficultés à trouver des fournisseurs CES", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Difficultés à trouver des fournisseurs structure", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Aucun bon film au cinéma", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-5));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Bonne entente entre les entreprises", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,2));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Nouvelle procédure interne incompréhensible", bonusList));
        return riskActionsActivity8;
    }

    private List<RiskAction> listOfRiskForActivity9(){
        int currentActivityId = 9;
        List<RiskAction> riskActionsActivity9 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 2));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Accident sur le chantier", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 2));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Panne d'une machine", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Pluie abondante", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,10, 1));
        bonusList.add(new DaysBonus(currentActivityId, 3));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Ouvrage mal implanté", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Blague pas drole", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-8));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Equipe d'ouvriers efficace", bonusList));
        return riskActionsActivity9;
    }

    private List<RiskAction> listOfRiskForActivity10(){
        int currentActivityId = 10;
        List<RiskAction> riskActionsActivity10 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 8));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Accident sur le chantier", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-3));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Augmentation du prix des matériaux", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 2));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Ouvriers en situation irrégulières", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Erreur sur la lecture d'un plan", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity10.add(new RiskAction(currentActivityId,"La France dernière de l\'Eurovision", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-7));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Equipe d'ouvriers efficace", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Béton non livré", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Jour de neige", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,7));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Ouvriers peu compétent", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Grue en panne", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Tempête", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Grue en panne", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Mauvaise Compréhension d'instructions", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,1));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-3));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Vol de matériel", bonusList));
        return riskActionsActivity10;
    }

    private List<RiskAction> listOfRiskForActivity11(){
        int currentActivityId = 11;
        List<RiskAction> riskActionsActivity11 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 2));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Matériel non livré", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Accident sur le chantier", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Vol de matériel", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Mauvaise entente entre les ouvriers de CES", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Bouchon sur la route", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-8));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Equipe d'ouvriers efficace", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,2));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Erreur de verification", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Matériel non livré", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Epidémie de gastro entérite", bonusList));
        return riskActionsActivity11;
    }

    private List<RiskAction> listOfRiskForActivity12(){
        int currentActivityId = 12;
        List<RiskAction> riskActionsActivity12 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,2));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Nombreuses réserves CES", bonusList));
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,2));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Nombreuses réserves CES", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Problème de coordination de planning", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Défaillance grave à reprendre", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Mauvais repas à la cantine", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-8));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Ouvriers très qualifiés", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Cloison mal positionnée", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Normes handicapés non respectées", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Problème de plomberie", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Désaccord interne sur la reception", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Peinture à refaire", bonusList));
        return riskActionsActivity12;
    }

}
