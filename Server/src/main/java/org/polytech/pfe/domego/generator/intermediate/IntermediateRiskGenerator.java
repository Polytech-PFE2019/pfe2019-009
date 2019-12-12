package org.polytech.pfe.domego.generator.intermediate;

import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.risk.*;

import java.util.ArrayList;
import java.util.List;

public class IntermediateRiskGenerator {

    public IntermediateRiskGenerator() {
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
        riskActions.addAll(listOfRiskForActivity13());
        riskActions.addAll(listOfRiskForActivity14());
        riskActions.addAll(listOfRiskForActivity15());
        return riskActions;
    }

    private List<RiskAction> listOfRiskForActivity1(){
        int currentActivityId = 1;
        List<RiskAction> riskActions = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-1));
        riskActions.add(new RiskAction(currentActivityId,"Difficultés financières", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,3,2));
        bonusList.add(new RiskBonus(3,1));
        riskActions.add(new RiskAction(currentActivityId,"Oubli d’un élément", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,5));
        riskActions.add(new RiskAction(currentActivityId,"Appui financier de l’Etat", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-2));
        riskActions.add(new RiskAction(currentActivityId,"Erreur sur les  comptes", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActions.add(new RiskAction(currentActivityId,"Recherches de financements possibles non abouties", bonusList));

        bonusList = new ArrayList<>();
        riskActions.add(new RiskAction(currentActivityId,"Invasion de moustiques", bonusList));
        return riskActions;
    }

    private List<RiskAction> listOfRiskForActivity2(){
        int currentActivityId = 2;
        List<RiskAction> riskActionsActivity2 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-3));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Surestimation des moyens humains\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Zone de fouille archéologique\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Imprévu sur le terrain\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Procédure d’expropriation\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Chef de projet  inexpérimenté\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity2.add(new RiskAction(currentActivityId,"Aucun bon film", bonusList));

        return riskActionsActivity2;
    }

    private List<RiskAction> listOfRiskForActivity3(){
        int currentActivityId = 3;
        List<RiskAction> riskActionsActivity3 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-3));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Surestimation des moyens humains\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Café pas bon", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE, -3));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Incohérences : projet non fiable à 100 %\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Travaux à côté du bureau\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Impératif sur les délais\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, 4, 1));
        riskActionsActivity3.add(new RiskAction(currentActivityId,"Phobie administrative\n", bonusList));
        return riskActionsActivity3;
    }

    private List<RiskAction> listOfRiskForActivity4(){
        int currentActivityId = 4;
        List<RiskAction> riskActionsActivity4 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();

        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Solutions extravagantes\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(6,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,6,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Flou dans les spécifications\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Chef de projet trop perfectionniste\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Problème de communication\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Bouchon sur la  route\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Chef de projet maitrisant mal les aspects administratifs\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Manque de ressources humaines internes\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId,1));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Chef de projet  inexpérimenté\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,3));
        riskActionsActivity4.add(new RiskAction(currentActivityId,"Concept de projet simple et concret\n", bonusList));

        return riskActionsActivity4;
    }

    private List<RiskAction> listOfRiskForActivity5(){
        int currentActivityId = 5;
        List<RiskAction> riskActionsActivity5 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();

        bonusList.add(new DaysBonus(currentActivityId, 3));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Réponses tardives\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Négociations longues\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(15,1));
        bonusList.add(new DaysBonus(15,3));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Contrat mal rédigé entre les parties\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId,1));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Peu de candidatures\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Mauvaise entente entre les acteurs\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,4));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Retard administratif\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(6,-5));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Choix du maitre d’ouvre très simple\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,-2));
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Originalité peu attirante\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity5.add(new RiskAction(currentActivityId,"Mauvais repas à la cantine\n", bonusList));

        return riskActionsActivity5;
    }

    private List<RiskAction> listOfRiskForActivity6(){
        int currentActivityId = 6;
        List<RiskAction> riskActionsActivity6 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 2));
        bonusList.add(new DaysBonus(currentActivityId, 2));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Ordinateurs obsolètes\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Week-end pluvieux", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Nouvelle règlementation non maitrisée", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,12, 1));
        bonusList.add(new RiskBonus(12, 1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Manque d’analyse géotechnique\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Architecte peu rigoureux\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(8,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE, 8,1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Oubli de certains éléments\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-3));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Projet classique en termes de calcul\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Ingénieur débutant\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE, currentActivityId,1));
        riskActionsActivity6.add(new RiskAction(currentActivityId,"Oubli de certains éléments\n", bonusList));
        return riskActionsActivity6;
    }

    private List<RiskAction> listOfRiskForActivity7(){
        int currentActivityId = 7;
        List<RiskAction> riskActionsActivity7 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 1));
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Professionnels se rejetant la faute\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(11, 5));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Pas de concertations avec le voisinage\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 1));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Photographie parcelle cadastrale omise\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OEUVRE, 2));
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE, 2));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Idée pertinente de l’architecte\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 2));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Phobie administrative\n", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity7.add(new RiskAction(currentActivityId,"La France, dernière  de l’Eurovision\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Durée de traitement du PC longue\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE,currentActivityId, 2));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Oubli d’un document important dans le dossier du PC\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(11, 1));
        riskActionsActivity7.add(new RiskAction(currentActivityId,"Oubli d’affichage du PC\n", bonusList));
        return riskActionsActivity7;
    }

    private List<RiskAction> listOfRiskForActivity8(){
        int currentActivityId = 8;
        List<RiskAction> riskActionsActivity8 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, -3));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Architecte expérimenté\n", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Démission d’un ingénieur\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Erreurs sur les calculs\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Ingénieur débutant\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 3));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Epidémie de gastro entérite\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,12, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,12, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Erreurs sur les plans\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new RiskBonus(currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Professionnels têtus\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new RiskBonus(currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Trop de spécificités techniques\n", bonusList));
        bonusList = new ArrayList<>();
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Crevaison d’une roue\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new RiskBonus(currentActivityId, 1));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Manque de communication\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 2));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Ordinateurs obsolètes\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity8.add(new RiskAction(currentActivityId,"Supérieur trop exigeant\n", bonusList));
        return riskActionsActivity8;
    }

    private List<RiskAction> listOfRiskForActivity9(){
        int currentActivityId = 9;
        List<RiskAction> riskActionsActivity9 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Non participation de l’ensemble des acteurs\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(11, 1));
        bonusList.add(new DaysBonus(11, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Mauvaise définition des ressources à affecter\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Date d’achèvement trop optimiste\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Technologie mal maitrisées\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Mauvaise entente entre les acteurs\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Week end de chasse\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Incohérence des résultats\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, -5));
        riskActionsActivity9.add(new RiskAction(currentActivityId,"Entreprises avec des dossiers complets\n", bonusList));
        return riskActionsActivity9;
    }

    private List<RiskAction> listOfRiskForActivity10(){
        int currentActivityId = 10;
        List<RiskAction> riskActionsActivity10 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId, 1));
        bonusList.add(new DaysBonus(currentActivityId, 5));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Mauvaise entente entre les acteurs\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Erreur importante dans les plan GO\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId, 1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Notes méthodologique incomplètes\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Mauvaise mise à jour des versions de documents\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Tache CES non prise en compte dans le planning\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Incohérence technique \n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Documents non transmis au maitre d’oeuvre\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Ordre de service  et procès verbaux  non délivrés\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE,currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,2));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Entreprises avec des dossiers incomplets\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-5));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Rapidité des échanges entre les partis\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Festival annulé pour cause de mauvais temps \n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity10.add(new RiskAction(currentActivityId,"Manque de responsabilisation des partis\n", bonusList));

        return riskActionsActivity10;
    }

    private List<RiskAction> listOfRiskForActivity11(){
        int currentActivityId = 11;
        List<RiskAction> riskActionsActivity11 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, -5));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Bonne entente entre les entreprises\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,2));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Implantations base vie gênante\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"PIC non unanime\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new RiskBonus(currentActivityId, 1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Oubli de détails d’importance\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Manque de points d’eau\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId, 1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Raccordement électricité à revoir\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Incompréhension entre les parties\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Série Netflix vraiment nulle\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Epidémie de rhume\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Zone de stockage trop petite\n", bonusList));
        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,1));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,1));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Vol de matériel\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        riskActionsActivity11.add(new RiskAction(currentActivityId,"Matériel non livré\n", bonusList));
        return riskActionsActivity11;
    }

    private List<RiskAction> listOfRiskForActivity12(){
        int currentActivityId = 12;
        List<RiskAction> riskActionsActivity12 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-3));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Accident sur chantier\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Panne d’une machine\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 3));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Matériel non livré\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Manque de ressources internes structures\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Mauvaise compréhension d’instructions\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-10));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Equipe d’ouvriers efficace\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Jour de neige\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE, -1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Vol de matériel\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Pas de concertations avec le voisinage\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Grue en panne \n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Inefficacité des réunion de pilotage\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity12.add(new RiskAction(currentActivityId,"Nième manifestation des gilets jaune ce samedi\n", bonusList));
        return riskActionsActivity12;
    }

    private List<RiskAction> listOfRiskForActivity13(){
        int currentActivityId = 13;
        List<RiskAction> riskActionsActivity13 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Grue en panne \n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-3));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Accident sur chantier\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Augmentation du prix des matériaux\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE,-1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,2));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Ouvriers en situation irrégulières\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Erreur sur la lecture d’un plan\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,3));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Béton non livré\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Tempête\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-10));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Equipe d’ouvriers efficace\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Difficile de trouver fournisseurs structure\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,10));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Panne d’une machine\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Règles de sécurité non respectées\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity13.add(new RiskAction(currentActivityId,"Match de foot ce soir à la TV\n", bonusList));
        return riskActionsActivity13;
    }

    private List<RiskAction> listOfRiskForActivity14(){
        int currentActivityId = 14;
        List<RiskAction> riskActionsActivity14 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Matériel non livré\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 10));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,-3));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Accident sur le chantier\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,-1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Vol de matériel\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Mauvaise entente entre les ouvriers de CES\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Ouvrier peu compétent\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-10));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Equipe d’ouvriers efficace\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Pénurie de lait au supermarché\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Manque de ressources internes CES\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Difficultés à trouver fournisseurs CES\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Problème de plomberie\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Problème électrique\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity14.add(new RiskAction(currentActivityId,"Règles de sécurité non respectées\n", bonusList));

        return riskActionsActivity14;
    }

    private List<RiskAction> listOfRiskForActivity15(){
        int currentActivityId = 15;
        List<RiskAction> riskActionsActivity15 = new ArrayList<>();
        List<Bonus> bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Désaccord interne sur la réception\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OUVRAGE,  -1));
        bonusList.add(new MoneyBonus(RoleType.MAITRE_D_OEUVRE,  -1));
        bonusList.add(new MoneyBonus(RoleType.BUREAU_D_ETUDE,  -1));
        bonusList.add(new MoneyBonus(RoleType.BUREAU_DE_CONTROLE,  -1));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_GROS_OEUVRE, -1));
        bonusList.add(new MoneyBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, -1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Erreur de compte\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE,currentActivityId,1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Nombreuses réserves structure\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId, 5));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,currentActivityId,1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Nombreuses réserves CES\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE, currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 2));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 2));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Défaillance grave à reprendre\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-10));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Procédure administrative rapide\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,10));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Retard sur la livraison\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new RiskBonus(currentActivityId,1));
        bonusList.add(new DaysBonus(currentActivityId,2));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Problèmes de coordination de planning\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new DaysBonus(currentActivityId,-5));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Reprise rapide des réserves \n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_D_ETUDE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"Incendie dans les archives papier du projet\n", bonusList));

        bonusList = new ArrayList<>();
        bonusList.add(new ResourcesBonus(RoleType.MAITRE_D_OUVRAGE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.BUREAU_DE_CONTROLE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_GROS_OEUVRE, currentActivityId, 1));
        bonusList.add(new ResourcesBonus(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, currentActivityId, 1));
        riskActionsActivity15.add(new RiskAction(currentActivityId,"DOE rendu incomplet\n", bonusList));

        bonusList = new ArrayList<>();
        riskActionsActivity15.add(new RiskAction(currentActivityId,"3ème démarque pour les soldes \n", bonusList));
        return riskActionsActivity15;
    }

}
