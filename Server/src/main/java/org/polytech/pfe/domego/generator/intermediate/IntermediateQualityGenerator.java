package org.polytech.pfe.domego.generator.intermediate;

import org.polytech.pfe.domego.models.QualityAction;
import org.polytech.pfe.domego.models.risk.QualityBonus;

import java.util.ArrayList;
import java.util.List;

public class IntermediateQualityGenerator {

    private List<QualityAction> qualityActionList;

    public IntermediateQualityGenerator() {
        this.qualityActionList = new ArrayList<>();
    }

    public List<QualityAction> getAllQualityAction(){
        listOfRiskForActivity1();
        listOfRiskForActivity2();
        listOfRiskForActivity3();
        listOfRiskForActivity4();
        listOfRiskForActivity5();
        listOfRiskForActivity6();
        listOfRiskForActivity7();
        listOfRiskForActivity8();
        listOfRiskForActivity9();
        listOfRiskForActivity10();
        listOfRiskForActivity11();
        listOfRiskForActivity12();
        listOfRiskForActivity13();
        listOfRiskForActivity14();
        listOfRiskForActivity15();


        return qualityActionList;
    }

    private void listOfRiskForActivity1(){
        int currentActivityId = 1;
        qualityActionList.add(new QualityAction(currentActivityId,"Prise en compte de la qualité du projet dans les couts", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dossier de financement bien monté", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etude complète des coûts", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne estimation de l’enveloppe financière", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne maitrise des coûts par le maitre d’ouvrage", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Anticipation des caractéristiques du projet", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity2(){
        int currentActivityId = 2;
        qualityActionList.add(new QualityAction(currentActivityId,"Choix du terrain idéal", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Analyse foncière de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Compromis de vente bien rédigé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Diagnostic technique de bonne qualité", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Acteurs engagés dans le projet", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne connaissance du terrain", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity3(){
        int currentActivityId = 3;
        qualityActionList.add(new QualityAction(currentActivityId,"Analyse des besoins complète", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Idées architecturales de départ intéressantes", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Raisonnable évaluation du budget nécessaire", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Règles d'urbanisme bien analysées", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Idée à la base du projet précisément définie", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne évaluation du délai de réalisation", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity4(){
        int currentActivityId = 4;
        qualityActionList.add(new QualityAction(currentActivityId,"Définition intégrale des besoins", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Analyse niveau de performance fonctionnelle complète", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Calendrier de réalisation achevé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Budget coûts  bien décomposé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Analyse de risques potentiels complète", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Performance fonctionnelle correctement définie", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Objectifs environnementaux cohérents", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Spécificités techniques bien définies", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne clairvoyance sur les difficultés opérationnelles", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity5(){
        int currentActivityId = 5;
        qualityActionList.add(new QualityAction(currentActivityId,"CCTP Clair et concis", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"DCE attractif", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Répartition des coûts équitable", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Acte d’engagement bien rédigé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dialogue compétitif enrichissant", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Réponses des entreprises de qualités", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Négociations constructives", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Programme de bonne qualité", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Contrat parfaitement rédigé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Estimation des détails quantitatif correcte", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Offre reçues compétitives", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Contenu des documents clair et détaillé", new QualityBonus(currentActivityId, 2)));
    }

    private void listOfRiskForActivity10(){
        int currentActivityId = 10;
        qualityActionList.add(new QualityAction(currentActivityId,"Plans d’exécution bien détaillés", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Planning prévisionnel réalisé avec soin", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Protocole de mise en place des éléments technique travaillé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Estimation des détails quantitatif correcte", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Rédaction complète des documents d’exécution", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Délivrance de visas réfléchie et étudiée", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dialogue constructif entre les acteurs", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Maitre d’œuvre investit avec un bon leadership", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Acteurs avec beaucoup d’idées et de compétences", new QualityBonus(currentActivityId, 2)));
    }

    private void listOfRiskForActivity6(){
        int currentActivityId = 6;
        qualityActionList.add(new QualityAction(currentActivityId,"Plans d’esquisse approuvés à l’unanimité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Visa d’étude d’exécution précis", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Calculs de surface approfondis", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Synthèse d’étude opérationnelles complète", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etudes de diagnostic de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de synthèse validés", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Performance énergétique honorable", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etude thermique bien réalisées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Unité de passage aux normes", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity7(){
        int currentActivityId = 7;
        qualityActionList.add(new QualityAction(currentActivityId,"Demande de DICT Validée", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etude d’impacts environnementaux complète", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Démarche administratives performantes", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dossier de permis de construire bien monté ", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bon respect des règles d’urbanisme en vigueur", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Permis de démolir correctement démarché", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de masse complet", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de coupe précis", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Panneau de chantier correctement affiché", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity8(){
        int currentActivityId = 8;
        qualityActionList.add(new QualityAction(currentActivityId,"Dimensionnement ventilation correct", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dimensionnement électrique correct", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dimensionnement canalisation confirmé", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dimensionnement structurel validé", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Respect des normes", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Trémies techniques bien localisées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Très bon niveau de détails", new QualityBonus(currentActivityId, 3)));
        qualityActionList.add(new QualityAction(currentActivityId,"Maquette BIM excellente", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Dimensionnement aciers sécuritaire", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Sécurité incendie respectée", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bureau de contrôle efficace", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plans correctement achevés", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity9(){
        int currentActivityId = 9;
        qualityActionList.add(new QualityAction(currentActivityId,"Dialogue constructif entre les acteurs", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Maitre d’œuvre investit avec un bon leadership", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Acteurs avec beaucoup d’idées et de compétences", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etude de l’ensemble des points techniques", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Vérification des dimensionnements structurels ", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Vérification des dimensionnement de CVC ", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Vérification des équipements techniques ", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Exploitation de la maquette BIM", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Gestion documentaire suivie ", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity15(){
        int currentActivityId = 15;
        qualityActionList.add(new QualityAction(currentActivityId,"Archivage des documents proprement fait", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Déclaration d’achèvement des travaux complète", new QualityBonus(currentActivityId, 3)));
        qualityActionList.add(new QualityAction(currentActivityId,"Carrelage non dégradé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Propreté inégalée", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pénalités évitées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Absence d’écaillage de peinture", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Inexistence de fissuration ", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pas d’arrachement de support", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Travaux supplémentaires rapidement traités", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pas de décollement de revêtement", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Décompte général et définitif sans erreur", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Aucun problème de paiement", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity11(){
        int currentActivityId = 11;
        qualityActionList.add(new QualityAction(currentActivityId,"Prévention chantier réussie", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Planning bien pensé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Règle de sécurité respectées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Réunion de coordination technique constructive", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"PIC bien pensé", new QualityBonus(currentActivityId, 3)));
        qualityActionList.add(new QualityAction(currentActivityId,"PPSPS correctement rédigé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Livraison de matériel de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Vérifications rapides des plans d’exécution", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Coordination inter-entreprises de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Piquetage réalisé avec soins", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Chantier parfaitement entretenu", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de géomètre de qualité", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Implantation rapide du chantier", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity12(){
        int currentActivityId = 12;
        qualityActionList.add(new QualityAction(currentActivityId,"Registre de chantier tenu à jour", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Etude géotechnique éfficace", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Engins de chantier performants", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Drainage de bonne qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Evacuation des eaux bien réalisée", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de VRD organisé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Démarche de raccordement aux EP de bonne qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Surfaces toutes à niveaux", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Aucun développement de racines sous les fondations", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Règle de sécurité respectées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Main d’œuvre compétente", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Chantier parfaitement entretenu", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Coordination inter-entreprises de qualité", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity13(){
        int currentActivityId = 13;
        qualityActionList.add(new QualityAction(currentActivityId,"Aucune corrosion d’armature", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Matériaux de qualité", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Ponts thermiques traités ", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Toiture bien réalisée", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Poteaux bien placés", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Poutres esthétiquement installées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pas d’infiltrations en toiture", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pas d’infiltrations en pieds de murs", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Règles de sécurité respectées", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bonne organisation des réseaux sous dallage", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Chantier parfaitement entretenu", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pas de stagnation d’eau repérée", new QualityBonus(currentActivityId, 1)));
    }

    private void listOfRiskForActivity14(){
        int currentActivityId = 14;
        qualityActionList.add(new QualityAction(currentActivityId,"Hors d’eau bien réalisé", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Revêtement de sol de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Hors d’air bien réalisé", new QualityBonus(currentActivityId, 2)));
        qualityActionList.add(new QualityAction(currentActivityId,"Bardage de qualité", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Plan de calepinage complet", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"GTB à la pointe de la technologie", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Isolation performante", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Aucun oubli de passage de gaines", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Main d’œuvre compétente", new QualityBonus(currentActivityId, 3)));
        qualityActionList.add(new QualityAction(currentActivityId,"Pare-vapeur correctement installé", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Chantier parfaitement entretenu", new QualityBonus(currentActivityId, 1)));
        qualityActionList.add(new QualityAction(currentActivityId,"Règle de sécurité respectées", new QualityBonus(currentActivityId, 1)));
    }


}
