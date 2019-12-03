package org.polytech.pfe.domego.generator;

import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.*;
import org.polytech.pfe.domego.models.activity.negotiation.Contract;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationActivity;

import java.util.*;
import java.util.stream.Collectors;

public class InitialGameGenerator implements GameGenerator {

    private static final int rateResourceWhenBuyingActivity = 1;

    private List<Activity> activities;

    public InitialGameGenerator() {
        activities = new ArrayList<>();

        activities.add(generateFirstActivity());
        activities.add(generateSecondActivity());
        activities.add(generateThirdActivity());
        activities.add(generateFourthActivity());
        activities.add(generateFifthActivity());
        activities.add(generateSixthActivity());
        activities.add(generateSeventhActivity());
        activities.add(generateEighthActivity());
        activities.add(generateNinthActivity());
        activities.add(generateTenthActivity());
        activities.add(generateEleventhActivity());
        activities.add(generateTwelfthActivity());

    }

    @Override
    public List<Activity> getAllActivitiesOfTheGame() {
        return activities.stream().sorted(Comparator.comparing(Activity::getId)).collect(Collectors.toList());
    }

    @Override
    public Game generateGame() {
        return null;
    }


    private Activity generateFirstActivity(){
        BuyResources buyResources = new BuyResources(RoleType.MAITRE_D_OUVRAGE.getId(),rateResourceWhenBuyingActivity);
        List<BuyResources> buyResourcesList = new ArrayList<>();
        buyResourcesList.add(buyResources);


        Map<Integer,Integer> mandatoryMap = new HashMap<>();
        mandatoryMap.put(1,0);

        Map<Integer,Integer> riskMap = new HashMap<>();
        riskMap.put(1,1);
        riskMap.put(3,2);

        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMap, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMap, PayResourceType.RISKS));

        String title = "ALLOCATION DE RESSOURCES MAITRE D’OUVRAGE";
        String description = "Le maître d’ouvrage estime la quantité de ressources qui lui sera nécessaire pour mener à bien le projet. Il se les procure en conséquence.";
        return new BuyingResourcesActivity(1,2,title, description,payResourcesList,buyResourcesList);
    }

    private Activity generateSecondActivity(){
        Map<Integer,Integer> mandatoryMap = new HashMap<>();
        mandatoryMap.put(1,0);

        Map<Integer,Integer> riskMap = new HashMap<>();
        riskMap.put(1,1);
        riskMap.put(3,2);

        Map<Integer,Integer> timeMap = new HashMap<>();
        timeMap.put(1,2);
        timeMap.put(2,5);

        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMap, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMap, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMap, PayResourceType.DAYS));
        String title = "Etudes de faisabilité";
        String description = "Une étude de faisabilité permet de déterminer si le projet est viable ou non. Elle permet aussi de rassembler toutes les informations nécessaires pour en assurer le financement. Cette étude va aussi aider à visualiser les Risques potentiels liés à la construction de l’ouvrage. Ces études vont donc permettre de déterminer si le projet peut continuer, s’il doit être modifié ou bien s’il doit simplement être abandonné.";
        return new ClassicActivity(2,15,title,description,payResourcesList);
    }

    private Activity generateThirdActivity(){
        Map<Integer,Integer> mandatoryMap = new HashMap<>();
        mandatoryMap.put(1,0);

        Map<Integer,Integer> riskMap = new HashMap<>();
        riskMap.put(1,1);
        riskMap.put(3,2);

        Map<Integer,Integer> timeMap = new HashMap<>();
        timeMap.put(2,2);
        timeMap.put(4,5);


        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMap, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMap, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMap, PayResourceType.DAYS));
        String title = "NEGOCIATION ET CONTRACTUALISATION\n" + "Maitre d’ouvrage – Maitre d’oeuvre";
        String description = "Le maître d’ouvrage négocie avec le maître d’oeuvre afin de déterminerla rémunération de ce dernier. Une fois qu’ils se sont entendus, le contrat peut alors être signé. Ce contrat précisera notamment le délai ainsi que les coûts prévus.";

        Contract contract = new Contract(80,115);
        Negotiation negociation = new Negotiation(1,2,contract);
        List<Negotiation> negociationList = new ArrayList<>();
        negociationList.add(negociation);

        return new NegotiationActivity(3,8,title,description,payResourcesList, negociationList);
    }

    private Activity generateFourthActivity(){
        Map<Integer,Integer> mandatoryMap = new HashMap<>();
        mandatoryMap.put(1,0);

        Map<Integer,Integer> riskMap = new HashMap<>();
        riskMap.put(2,1);
        riskMap.put(3,2);

        Map<Integer,Integer> timeMap = new HashMap<>();
        timeMap.put(1,1);
        timeMap.put(3,5);



        List<PayResources> payResourcesList = new ArrayList<>();
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMap, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMap, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMap, PayResourceType.DAYS));
        String title = "NEGOCIATION ET CONTRACTUALISATION\n" + "Maitre d’oeuvre – ENTREPRI  SES – BUREAU CONTROLE";
        String description = "Le maître d’oeuvre et les entreprises négocient afin de tomber d’accord sur un montant qui satisfera les différents intervenants. Le maître d’ouvrage négocie avec le bureau de contrôle afin de déterminer la rémunération de ce dernier. Une fois que les acteurs se sont entendus, les contrats peuvent alors être signés.";

        Contract contract1 = new Contract(10,25);
        Negotiation negociation1 = new Negotiation(1,4,contract1);
        Contract contract2 = new Contract(25,50);
        Negotiation negociation2 = new Negotiation(2,5,contract2);
        Contract contract3 = new Contract(20,40);
        Negotiation negociation3 = new Negotiation(2,6,contract3);
        Contract contract4 = new Contract(10,20);
        Negotiation negociation4 = new Negotiation(2,3,contract4);

        List<Negotiation> negociationList = new ArrayList<>();
        negociationList.add(negociation1);
        negociationList.add(negociation2);
        negociationList.add(negociation3);
        negociationList.add(negociation4);


        return new NegotiationActivity(4,15,title,description,payResourcesList, negociationList);
    }

    private Activity generateFifthActivity(){

        List<BuyResources> buyResourcesList = new ArrayList<>();
        buyResourcesList.add(new BuyResources(RoleType.MAITRE_D_OEUVRE.getId(),rateResourceWhenBuyingActivity));
        buyResourcesList.add(new BuyResources(RoleType.BUREAU_D_ETUDE.getId(),rateResourceWhenBuyingActivity));
        buyResourcesList.add(new BuyResources(RoleType.BUREAU_DE_CONTROLE.getId(),rateResourceWhenBuyingActivity));

        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(1,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForController = new HashMap<>();
        mandatoryMapForController.put(1,0);

        Map<Integer,Integer> riskMapForController = new HashMap<>();
        riskMapForController.put(2,1);

        Map<Integer,Integer> timeMapForController = new HashMap<>();
        timeMapForController.put(1,1);

        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),mandatoryMapForController, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),riskMapForController, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),timeMapForController, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForOfficer = new HashMap<>();
        mandatoryMapForOfficer.put(1,0);

        Map<Integer,Integer> riskMapForOfficer = new HashMap<>();
        riskMapForOfficer.put(2,1);

        Map<Integer,Integer> timeMapForOfficer = new HashMap<>();
        timeMapForOfficer.put(1,1);

        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),mandatoryMapForOfficer, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),riskMapForOfficer, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),timeMapForOfficer, PayResourceType.DAYS));



        String title = "ALLOCATION DE RESSOURCES : MAITRE D’OEUVRE, Bureau d’etudes et de contrôle";
        String description = "Le maître d’oeuvre, le bureau d’étude et le bureau de contrôle prévoient les ressources dont ils estiment avoir besoin durant l’opération de construction.";
        return new BuyingResourcesActivity(5,5,title,description,payResourcesList,buyResourcesList);
    }

    private Activity generateSixthActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForManager = new HashMap<>();
        mandatoryMapForManager.put(1,0);

        Map<Integer,Integer> riskMapForManager = new HashMap<>();
        riskMapForManager.put(2,1);

        Map<Integer,Integer> timeMapForManager = new HashMap<>();
        timeMapForManager.put(1,2);
        timeMapForManager.put(2,5);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMapForManager, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMapForManager, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMapForManager, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(2,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);
        riskMapForArchitect.put(4,2);


        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);
        timeMapForArchitect.put(2,4);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));
        String title = "CONCEPTION PRELIMINAIRE";
        String description = "La conception préliminaire permet au client de faire des choix stratégiques entre les concepts fonctionnels et les options envisagées. Durant celle-ci, une conception du bien immobilier est élaborée pour proposer un aperçu général couvrant les aspects liés à l’implantation, l’organisation fonctionnelle, la structure spatiale et l’aspect général.";
        return new ClassicActivity(6,20,title,description,payResourcesList);
    }

    private Activity generateSeventhActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForManager = new HashMap<>();
        mandatoryMapForManager.put(1,0);

        Map<Integer,Integer> riskMapForManager = new HashMap<>();
        riskMapForManager.put(2,1);

        Map<Integer,Integer> timeMapForManager = new HashMap<>();
        timeMapForManager.put(1,1);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMapForManager, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMapForManager, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMapForManager, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(5,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);
        timeMapForArchitect.put(2,3);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForController = new HashMap<>();
        mandatoryMapForController.put(2,0);

        Map<Integer,Integer> riskMapForController = new HashMap<>();
        riskMapForController.put(2,1);

        Map<Integer,Integer> timeMapForController = new HashMap<>();
        timeMapForController.put(2,2);

        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),mandatoryMapForController, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),riskMapForController, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),timeMapForController, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForOfficer = new HashMap<>();
        mandatoryMapForOfficer.put(5,0);

        Map<Integer,Integer> riskMapForOfficer = new HashMap<>();
        riskMapForOfficer.put(2,1);

        Map<Integer,Integer> timeMapForOfficer = new HashMap<>();
        timeMapForOfficer.put(1,1);
        timeMapForOfficer.put(3,5);

        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),mandatoryMapForOfficer, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),riskMapForOfficer, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),timeMapForOfficer, PayResourceType.DAYS));



        String title = "CONCEPTION DEFINITIVE";
        String description = "Une fois la conception préliminaire validée par le maître d’ouvrage, il est alors possible de faire une mise au point définitive des plans du projet. On créé alors les dossiers de consultation pour les autorisations administratives, les plans d’exécution sont établis et les commandes de matériaux peuvent être effectuées. Une date de démarrage des travaux est prévue.";
        return new ClassicActivity(7,30,title,description,payResourcesList);
    }

    private Activity generateEighthActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();
        List<BuyResources> buyResourcesList = new ArrayList<>();

        buyResourcesList.add(new BuyResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),rateResourceWhenBuyingActivity));
        buyResourcesList.add(new BuyResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),rateResourceWhenBuyingActivity));

        Map<Integer,Integer> mandatoryMapForGrosOeuvre = new HashMap<>();
        mandatoryMapForGrosOeuvre.put(2,0);

        Map<Integer,Integer> riskMapForGrosOeuvre = new HashMap<>();
        riskMapForGrosOeuvre.put(2,1);
        riskMapForGrosOeuvre.put(5,2);

        Map<Integer,Integer> timeMapForGrosOeuvre = new HashMap<>();
        timeMapForGrosOeuvre.put(2,1);
        timeMapForGrosOeuvre.put(3,2);
        timeMapForGrosOeuvre.put(4,5);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),mandatoryMapForGrosOeuvre, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),riskMapForGrosOeuvre, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),timeMapForGrosOeuvre, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForSecondary = new HashMap<>();
        mandatoryMapForSecondary.put(2,0);

        Map<Integer,Integer> riskMapForSecondary = new HashMap<>();
        riskMapForSecondary.put(2,1);
        riskMapForSecondary.put(5,2);

        Map<Integer,Integer> timeMapForSecondary = new HashMap<>();
        timeMapForSecondary.put(2,1);
        timeMapForSecondary.put(3,3);
        timeMapForSecondary.put(6,8);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),mandatoryMapForSecondary, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),riskMapForSecondary, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),timeMapForSecondary, PayResourceType.DAYS));

        String title = "Preparation de chantier";
        String description = "Les entreprises doivent préparer le chantier en anticipant tout ce qui sera nécessaire à sa réalisation. Il s’agit de prévoir et d’organiser les différentes interventions, d’établir le planning prévisionnel d’exécution des taches, les installations de chantier et de prévoir les ressources nécessaires à son déroulement.";
        return new BuyingResourcesActivity(8,20,title, description,payResourcesList,buyResourcesList);
    }

    private Activity generateNinthActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(1,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);
        timeMapForArchitect.put(2,3);


        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForGrosOeuvre = new HashMap<>();
        mandatoryMapForGrosOeuvre.put(5,0);

        Map<Integer,Integer> riskMapForGrosOeuvre = new HashMap<>();
        riskMapForGrosOeuvre.put(2,1);
        riskMapForGrosOeuvre.put(4,2);
        riskMapForGrosOeuvre.put(6,3);

        Map<Integer,Integer> timeMapForGrosOeuvre = new HashMap<>();
        timeMapForGrosOeuvre.put(1,1);
        timeMapForGrosOeuvre.put(2,3);
        timeMapForGrosOeuvre.put(3,5);
        timeMapForGrosOeuvre.put(5,10);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),mandatoryMapForGrosOeuvre, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),riskMapForGrosOeuvre, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),timeMapForGrosOeuvre, PayResourceType.DAYS));
        String title = "Terrassement et fondations";
        String description = "TERRASSEMENT :\n" +
                "Ils permettent de préparer l'assise de la construction et de ses abords.\n" +
                "Travaux à effectuer :\n" +
                "- creuser à l'emplacement des fondations\n" +
                "- dégager les terres extraites\n" +
                "- niveler, aplanir ou combler suivant les cas.\n" +
                "\n" +
                "FONDATIONS :\n" +
                "- Elles servent à transmettre directement au sol les charges du bâtiment en tenant compte de sa propre masse.\n" +
                "- Elles répartissent les pressions sur le sol souvent par des « semelles continues » sous les murs. La semelle placée sous un poteau est dite « semelle isolée ».";
        return new ClassicActivity(9,50,title,description,payResourcesList);
    }

    private Activity generateTenthActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(1,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,2);
        timeMapForArchitect.put(3,8);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForGrosOeuvre = new HashMap<>();
        mandatoryMapForGrosOeuvre.put(7,0);

        Map<Integer,Integer> riskMapForGrosOeuvre = new HashMap<>();
        riskMapForGrosOeuvre.put(2,1);
        riskMapForGrosOeuvre.put(5,2);

        Map<Integer,Integer> timeMapForGrosOeuvre = new HashMap<>();
        timeMapForGrosOeuvre.put(2,3);
        timeMapForGrosOeuvre.put(3,5);
        timeMapForGrosOeuvre.put(6,10);
        timeMapForGrosOeuvre.put(8,20);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),mandatoryMapForGrosOeuvre, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),riskMapForGrosOeuvre, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),timeMapForGrosOeuvre, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForController = new HashMap<>();
        mandatoryMapForController.put(2,0);

        Map<Integer,Integer> riskMapForController = new HashMap<>();
        riskMapForController.put(2,1);


        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),mandatoryMapForController, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),riskMapForController, PayResourceType.RISKS));
        String title = "Gros oeuvre";
        String description = "Le gros oeuvre désigne les parties d'une construction qui constituent l'ossature de celle-ci et qui comprennent à la fois : \n" +
                "* les éléments porteurs qui concourent à la stabilité ou à la solidité du bâtiment (murs, charpente, poutres, poteaux) et tous autres éléments qui leur sont intégrés ou forment corps avec eux (plancher, dallages).\n" +
                "* les éléments qui assurent le clos, le couvert et l'étanchéité à l'exclusion de leurs parties mobiles(couverture).";
        return new ClassicActivity(10,80,title,description,payResourcesList);
    }

    private Activity generateEleventhActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(2,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(2,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);
        timeMapForArchitect.put(3,8);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForSecondary = new HashMap<>();
        mandatoryMapForSecondary.put(8,0);

        Map<Integer,Integer> riskMapForSecondary = new HashMap<>();
        riskMapForSecondary.put(2,1);
        riskMapForSecondary.put(4,2);
        riskMapForSecondary.put(6,3);

        Map<Integer,Integer> timeMapForSecondary = new HashMap<>();
        timeMapForSecondary.put(1,2);
        timeMapForSecondary.put(3,6);
        timeMapForSecondary.put(6,11);
        timeMapForSecondary.put(8,20);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),mandatoryMapForSecondary, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),riskMapForSecondary, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),timeMapForSecondary, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForController = new HashMap<>();
        mandatoryMapForController.put(2,0);

        Map<Integer,Integer> riskMapForController = new HashMap<>();
        riskMapForController.put(2,1);


        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),mandatoryMapForController, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),riskMapForController, PayResourceType.RISKS));
        String title = "Gros d\'etat secondaire";
        String description = "Les corps d'état secondaires recouvrent l'ensemble des travaux réalisés à l'intérieur d'un bâtiment comme les enduits et revêtements intérieurs ainsi que les cloisons. Mais aussi des travaux spécifiques tels que la plomberie ou l’électricité. ";
        return new ClassicActivity(11,100,title,description,payResourcesList);
    }

    private Activity generateTwelfthActivity(){
        List<PayResources> payResourcesList = new ArrayList<>();

        Map<Integer,Integer> mandatoryMapForGrosOeuvre = new HashMap<>();
        mandatoryMapForGrosOeuvre.put(3,0);

        Map<Integer,Integer> riskMapForGrosOeuvre = new HashMap<>();
        riskMapForGrosOeuvre.put(3,1);

        Map<Integer,Integer> timeMapForGrosOeuvre = new HashMap<>();
        timeMapForGrosOeuvre.put(1,1);
        timeMapForGrosOeuvre.put(3,3);
        timeMapForGrosOeuvre.put(4,6);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),mandatoryMapForGrosOeuvre, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),riskMapForGrosOeuvre, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),timeMapForGrosOeuvre, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForController = new HashMap<>();
        mandatoryMapForController.put(1,0);

        Map<Integer,Integer> riskMapForController = new HashMap<>();
        riskMapForController.put(2,1);

        Map<Integer,Integer> timeMapForController = new HashMap<>();
        timeMapForController.put(1,1);


        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),mandatoryMapForController, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),riskMapForController, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.BUREAU_DE_CONTROLE.getId(),timeMapForController, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForManager = new HashMap<>();
        mandatoryMapForManager.put(2,0);

        Map<Integer,Integer> riskMapForManager = new HashMap<>();
        riskMapForManager.put(3,1);

        Map<Integer,Integer> timeMapForManager = new HashMap<>();
        timeMapForManager.put(2,3);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),mandatoryMapForManager, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),riskMapForManager, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OUVRAGE.getId(),timeMapForManager, PayResourceType.DAYS));



        Map<Integer,Integer> mandatoryMapForArchitect = new HashMap<>();
        mandatoryMapForArchitect.put(2,0);

        Map<Integer,Integer> riskMapForArchitect = new HashMap<>();
        riskMapForArchitect.put(3,1);

        Map<Integer,Integer> timeMapForArchitect = new HashMap<>();
        timeMapForArchitect.put(1,1);
        timeMapForArchitect.put(3,3);

        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),mandatoryMapForArchitect, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),riskMapForArchitect, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.MAITRE_D_OEUVRE.getId(),timeMapForArchitect, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForSecondary = new HashMap<>();
        mandatoryMapForSecondary.put(4,0);

        Map<Integer,Integer> riskMapForSecondary = new HashMap<>();
        riskMapForSecondary.put(3,1);

        Map<Integer,Integer> timeMapForSecondary = new HashMap<>();
        timeMapForSecondary.put(1,1);
        timeMapForSecondary.put(3,3);
        timeMapForSecondary.put(5,6);

        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),mandatoryMapForSecondary, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),riskMapForSecondary, PayResourceType.RISKS));
        payResourcesList.add(new PayResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),timeMapForSecondary, PayResourceType.DAYS));

        Map<Integer,Integer> mandatoryMapForOfficer = new HashMap<>();
        mandatoryMapForOfficer.put(1,0);

        Map<Integer,Integer> timeMapForOfficer = new HashMap<>();
        timeMapForOfficer.put(1,1);


        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),mandatoryMapForOfficer, PayResourceType.MANDATORY));
        payResourcesList.add(new PayResources(RoleType.BUREAU_D_ETUDE.getId(),timeMapForOfficer, PayResourceType.DAYS));
        String title = "Livraison et reception";
        String description = "La livraison correspond à la fin des travaux. Le maître d’ouvrage émet alors ou non des réserves sur la construction.\n" +
                "La réception traduit l'intention du maître d'ouvrage d'accepter les travaux réalisés.";
        return new ClassicActivity(12,30,title,description,payResourcesList);
    }


}
