package org.polytech.pfe.domego.generator.intermediate;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.polytech.pfe.domego.database.accessor.QualityAccessor;
import org.polytech.pfe.domego.database.accessor.RiskAccessor;
import org.polytech.pfe.domego.generator.GameGenerator;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.ClassicActivity;
import org.polytech.pfe.domego.models.activity.PayResourceType;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.buying.BuyingResourcesActivity;
import org.polytech.pfe.domego.models.activity.negotiation.Contract;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.negotiation.NegotiationActivity;
import org.polytech.pfe.domego.models.activity.pay.PayContract;
import org.polytech.pfe.domego.models.activity.pay.PayContractActivity;
import org.polytech.pfe.domego.models.activity.pay.PayResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class IntermediateGameGenerator implements GameGenerator {

    private RiskAccessor riskAccessor;
    private QualityAccessor qualityAccessor;


    private static final GameType GAME_TYPE = GameType.INTERMEDIATE;


    private static final int RateResourceWhenBuyingActivity = 1;
    private static final int SheetNumber = 0;
    private static final int FirstRowOfGameInformation = 5;
    private static final int FirstRowOfNumberOfBet = 47;
    private static final int FirstRowOfInfoResourcesToPut = 69;
    private static final int NumberOfActivities = 15;

    private List<Negotiation> negotiationForGame;
    private List<Activity> activities;
    private int timeOfProject;
    private int costOfProject;
    private Map<RoleType, Integer> budgetByRole;

    public IntermediateGameGenerator(int timeOfProject, int costOfProject) {
        this.negotiationForGame = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.budgetByRole = new HashMap<>();
        this.timeOfProject = timeOfProject;
        this.costOfProject = costOfProject;
        riskAccessor = new RiskAccessor();
        qualityAccessor = new QualityAccessor();
        this.generateIntermediateGame();


    }

    @Override
    public List<Activity> getAllActivitiesOfTheGame() {
        return activities.stream().sorted(Comparator.comparing(Activity::getId)).collect(Collectors.toList());
    }



    private void generateIntermediateGame() {
        String path = IntermediateGameGenerator.class.getResource("/generator.xlsm").getPath();
        // Creating a Workbook from an Excel file (.xls or .xlsx)

        FileInputStream excelFile;
        Workbook workbook ;
        try {
            excelFile = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            this.generateIntermediateGame();
            return;
        }

        Sheet datatypeSheet = workbook.getSheetAt(SheetNumber);
        new DefineSpecificationProject(workbook,datatypeSheet, this.timeOfProject,this.costOfProject).generateSheetForThisGame();

        for (RoleType role : RoleType.values()) {
            if (role.equals(RoleType.NON_DEFINI))
                continue;
            budgetByRole.put(role,(int)datatypeSheet.getRow(13 + role.getId() ).getCell(12).getNumericCellValue());
        }

        for (int i = 0; i < NumberOfActivities; i++) {
            int activityId = i + 1;
            String title = datatypeSheet.getRow(FirstRowOfGameInformation + i).getCell(0).getStringCellValue();
            String description = datatypeSheet.getRow(FirstRowOfGameInformation + i).getCell(1).getStringCellValue();
            int time = ((int) datatypeSheet.getRow(FirstRowOfGameInformation + i).getCell(3).getNumericCellValue());
            int numberOfRisks = (int)datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(2).getNumericCellValue();
            int numberOfQualities = (int)datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(3).getNumericCellValue();
            List<PayResources> payResourcesListByActivity = new ArrayList<>();
            for (RoleType roleType : RoleType.values()) {
                if (roleType.equals(RoleType.NON_DEFINI))
                    continue;

                int positionExcel = 4 * roleType.getId();
                Map<Integer,Integer> mandatoryMapByRole = new HashMap<>();
                double numberOfCostToPaid = datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel) == null ? 0 : datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel).getNumericCellValue();
                for (int j = 0; j < numberOfCostToPaid; j++) {
                    int cost = (int) datatypeSheet.getRow(FirstRowOfInfoResourcesToPut + i ).getCell(2 + (9 * (roleType.getId() -1))).getNumericCellValue();
                    mandatoryMapByRole.put(cost,0);
                }
                if (!mandatoryMapByRole.isEmpty())
                    payResourcesListByActivity.add(new PayResources(roleType.getId(), mandatoryMapByRole, PayResourceType.MANDATORY));

                Map<Integer,Integer> riskMapByRole = new HashMap<>();
                double numberOfRiskBonus = datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 1) == null ? 0 : datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 1).getNumericCellValue();
                for (int j = 0; j < numberOfRiskBonus; j++) {
                    int cost = riskMapByRole.keySet().stream().mapToInt(value -> value).max().orElse(0) + (int) datatypeSheet.getRow(FirstRowOfInfoResourcesToPut + i ).getCell(3 + (9 * (roleType.getId() -1))).getNumericCellValue();
                    int bonus = j+1;
                    riskMapByRole.put(cost,bonus);
                }
                if (!riskMapByRole.isEmpty())
                    payResourcesListByActivity.add(new PayResources(roleType.getId(), riskMapByRole, PayResourceType.RISKS));

                Map<Integer,Integer> timeMapByRole = new HashMap<>();
                double numberOfDelayBonus = datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 2) == null ? 0 : datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 2).getNumericCellValue();
                for (int j = 0; j < numberOfDelayBonus; j++) {
                    int bonus = 0;
                    try{
                        bonus = (int)datatypeSheet.getRow(96 + ((roleType.getId() - 1) * 19) + i).getCell(19 + j).getNumericCellValue();
                    }catch (IllegalStateException e){
                        continue;
                    }
                    if (bonus == 0)
                        continue;
                    int cost = timeMapByRole.keySet().stream().mapToInt(value -> value).max().orElse(0) + (int) datatypeSheet.getRow(FirstRowOfInfoResourcesToPut + i ).getCell(5 + (9 * (roleType.getId() -1))).getNumericCellValue();
                    timeMapByRole.put(cost,bonus);
                }
                if (!timeMapByRole.isEmpty())
                    payResourcesListByActivity.add(new PayResources(roleType.getId(), timeMapByRole, PayResourceType.DAYS));

                Map<Integer,Integer> qualityMapByRole = new HashMap<>();
                double numberOfQualityBonusByRole = datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 3) == null ? 0 : datatypeSheet.getRow(FirstRowOfNumberOfBet + i ).getCell(positionExcel + 3).getNumericCellValue();
                for (int j = 0; j < numberOfQualityBonusByRole; j++) {
                    int cost = qualityMapByRole.keySet().stream().mapToInt(value -> value).max().orElse(0) + (int) datatypeSheet.getRow(FirstRowOfInfoResourcesToPut + i ).getCell(7 + (9 * (roleType.getId() -1))).getNumericCellValue();
                    int bonus = j+1;
                    qualityMapByRole.put(cost,bonus);
                }
                if (!qualityMapByRole.isEmpty())
                    payResourcesListByActivity.add(new PayResources(roleType.getId(), qualityMapByRole, PayResourceType.QUALITY));

            }

            Negotiation findNegotiationBetweenBlueAndGreen = negotiationForGame.stream().filter(negotiation -> negotiation.getGiverRoleID() == RoleType.MAITRE_D_OUVRAGE.getId() && negotiation.getReceiverRoleID() == RoleType.MAITRE_D_OEUVRE.getId()).findFirst().orElse(new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(),RoleType.MAITRE_D_OEUVRE.getId(),new Contract(90,130)));
            Negotiation findNegotiationBetweenGreenAndYellow = negotiationForGame.stream().filter(negotiation -> negotiation.getGiverRoleID() == RoleType.MAITRE_D_OEUVRE.getId() && negotiation.getReceiverRoleID() == RoleType.BUREAU_D_ETUDE.getId()).findFirst().orElse(new Negotiation(RoleType.MAITRE_D_OEUVRE.getId(),RoleType.BUREAU_D_ETUDE.getId(),new Contract(10,15)));
            Negotiation findNegotiationBetweenBlueAndBlack = negotiationForGame.stream().filter(negotiation -> negotiation.getGiverRoleID() == RoleType.MAITRE_D_OUVRAGE.getId() && negotiation.getReceiverRoleID() == RoleType.BUREAU_DE_CONTROLE.getId()).findFirst().orElse(new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(),RoleType.MAITRE_D_OEUVRE.getId(),new Contract(90,130)));
            Negotiation findNegotiationBetweenBlueAndRed = negotiationForGame.stream().filter(negotiation -> negotiation.getGiverRoleID() == RoleType.MAITRE_D_OUVRAGE.getId() && negotiation.getReceiverRoleID() == RoleType.ENTREPRISE_GROS_OEUVRE.getId()).findFirst().orElse(new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(),RoleType.MAITRE_D_OEUVRE.getId(),new Contract(90,130)));
            Negotiation findNegotiationBetweenBlueAndViolet = negotiationForGame.stream().filter(negotiation -> negotiation.getGiverRoleID() == RoleType.MAITRE_D_OUVRAGE.getId() && negotiation.getReceiverRoleID() == RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId()).findFirst().orElse(new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(),RoleType.MAITRE_D_OEUVRE.getId(),new Contract(90,130)));


            switch (activityId){
                case 1 :
                    BuyResources buyResources = new BuyResources(RoleType.MAITRE_D_OUVRAGE.getId(),RateResourceWhenBuyingActivity);
                    activities.add(new BuyingResourcesActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId),Arrays.asList(buyResources)));
                    break;
                case 5:
                    List<Negotiation> negotiationsOfActivity5 = new ArrayList<>();
                    Negotiation negotiationBetweenBlueAndGreen = new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(), RoleType.MAITRE_D_OEUVRE.getId(), new Contract(0,0));
                    Negotiation negotiationBetweenBlueAndBlack = new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(), RoleType.BUREAU_DE_CONTROLE.getId(), new Contract(0,0));
                    Negotiation negotiationBetweenBlueAndRed = new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(), RoleType.ENTREPRISE_GROS_OEUVRE.getId(), new Contract(0,0));
                    Negotiation negotiationBetweenBlueAndViolet = new Negotiation(RoleType.MAITRE_D_OUVRAGE.getId(), RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(), new Contract(0,0));
                    Negotiation negotiationBetweenGreenAndYellow = new Negotiation(RoleType.MAITRE_D_OEUVRE.getId(), RoleType.BUREAU_D_ETUDE.getId(), new Contract(0,0));
                    negotiationsOfActivity5.add(negotiationBetweenBlueAndGreen);
                    negotiationsOfActivity5.add(negotiationBetweenBlueAndBlack);
                    negotiationsOfActivity5.add(negotiationBetweenBlueAndRed);
                    negotiationsOfActivity5.add(negotiationBetweenBlueAndViolet);
                    negotiationsOfActivity5.add(negotiationBetweenGreenAndYellow);
                    negotiationForGame.addAll(negotiationsOfActivity5);
                    activities.add(new NegotiationActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId) , negotiationsOfActivity5));
                    break;
                case 6 :
                    List<BuyResources> buyResourcesList = new ArrayList<>();
                    buyResourcesList.add(new BuyResources(RoleType.MAITRE_D_OEUVRE.getId(),RateResourceWhenBuyingActivity));
                    buyResourcesList.add(new BuyResources(RoleType.BUREAU_D_ETUDE.getId(),RateResourceWhenBuyingActivity));
                    buyResourcesList.add(new BuyResources(RoleType.BUREAU_DE_CONTROLE.getId(),RateResourceWhenBuyingActivity));
                    buyResourcesList.add(new BuyResources(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),RateResourceWhenBuyingActivity));
                    buyResourcesList.add(new BuyResources(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),RateResourceWhenBuyingActivity));
                    activities.add(new BuyingResourcesActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), buyResourcesList));
                    break;
                case 7:
                    List<PayContract> payContractListActivity7 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndGreenActivity7 = new PayContract(findNegotiationBetweenBlueAndGreen,15);
                    PayContract payContractBetweenGreenAndYellowActivity7 = new PayContract(findNegotiationBetweenGreenAndYellow,20);

                    payContractListActivity7.add(payContractBetweenBlueAndGreenActivity7);
                    payContractListActivity7.add(payContractBetweenGreenAndYellowActivity7);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity7));
                    break;
                case 9:
                    List<PayContract> payContractListActivity9 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndGreenActivity9 = new PayContract(findNegotiationBetweenBlueAndGreen,15);
                    PayContract payContractBetweenGreenAndYellowActivity9 = new PayContract(findNegotiationBetweenGreenAndYellow,20);
                    PayContract payContractBetweenBlueAndBlackActivity9 = new PayContract(findNegotiationBetweenBlueAndBlack,10);

                    payContractListActivity9.add(payContractBetweenBlueAndGreenActivity9);
                    payContractListActivity9.add(payContractBetweenGreenAndYellowActivity9);
                    payContractListActivity9.add(payContractBetweenBlueAndBlackActivity9);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity9));
                    break;
                case 10:
                case 11:
                    List<PayContract> payContractListActivity10And11 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndGreenActivity10 = new PayContract(findNegotiationBetweenBlueAndGreen,10);
                    PayContract payContractBetweenGreenAndYellowActivity10 = new PayContract(findNegotiationBetweenGreenAndYellow,20);
                    PayContract payContractBetweenBlueAndBlackActivity10 = new PayContract(findNegotiationBetweenBlueAndBlack,10);
                    PayContract payContractBetweenBlueAndRedActivity10 = new PayContract(findNegotiationBetweenBlueAndRed,10);
                    PayContract payContractBetweenBlueAndVioletActivity10 = new PayContract(findNegotiationBetweenBlueAndViolet,10);

                    payContractListActivity10And11.add(payContractBetweenBlueAndGreenActivity10);
                    payContractListActivity10And11.add(payContractBetweenGreenAndYellowActivity10);
                    payContractListActivity10And11.add(payContractBetweenBlueAndBlackActivity10);
                    payContractListActivity10And11.add(payContractBetweenBlueAndRedActivity10);
                    payContractListActivity10And11.add(payContractBetweenBlueAndVioletActivity10);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity10And11));
                    break;
                case 12:
                    List<PayContract> payContractListActivity12 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndBlackActivity12 = new PayContract(findNegotiationBetweenBlueAndBlack,10);
                    PayContract payContractBetweenBlueAndRedActivity12 = new PayContract(findNegotiationBetweenBlueAndRed,20);
                    PayContract payContractBetweenBlueAndVioletActivity12 = new PayContract(findNegotiationBetweenBlueAndViolet,10);

                    payContractListActivity12.add(payContractBetweenBlueAndBlackActivity12);
                    payContractListActivity12.add(payContractBetweenBlueAndRedActivity12);
                    payContractListActivity12.add(payContractBetweenBlueAndVioletActivity12);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity12));
                    break;
                case 13:
                    List<PayContract> payContractListActivity13 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndBlackActivity13 = new PayContract(findNegotiationBetweenBlueAndBlack,10);
                    PayContract payContractBetweenBlueAndGreenActivity13 = new PayContract(findNegotiationBetweenBlueAndGreen,10);
                    PayContract payContractBetweenBlueAndRedActivity13 = new PayContract(findNegotiationBetweenBlueAndRed,20);

                    payContractListActivity13.add(payContractBetweenBlueAndBlackActivity13);
                    payContractListActivity13.add(payContractBetweenBlueAndGreenActivity13);
                    payContractListActivity13.add(payContractBetweenBlueAndRedActivity13);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity13));
                    break;
                case 14:
                    List<PayContract> payContractListActivity14 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndBlackActivity14 = new PayContract(findNegotiationBetweenBlueAndBlack,15);
                    PayContract payContractBetweenBlueAndGreenActivity14 = new PayContract(findNegotiationBetweenBlueAndGreen,10);
                    PayContract payContractBetweenBlueAndRedActivity14 = new PayContract(findNegotiationBetweenBlueAndRed,20);

                    payContractListActivity14.add(payContractBetweenBlueAndBlackActivity14);
                    payContractListActivity14.add(payContractBetweenBlueAndGreenActivity14);
                    payContractListActivity14.add(payContractBetweenBlueAndRedActivity14);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity14));
                    break;
                case 15:
                    List<PayContract> payContractListActivity15 = new ArrayList<>();

                    PayContract payContractBetweenBlueAndGreenActivity15 = new PayContract(findNegotiationBetweenBlueAndGreen,30);
                    PayContract payContractBetweenGreenAndYellowActivity15 = new PayContract(findNegotiationBetweenGreenAndYellow,20);
                    PayContract payContractBetweenBlueAndBlackActivity15 = new PayContract(findNegotiationBetweenBlueAndBlack,35);
                    PayContract payContractBetweenBlueAndRedActivity15 = new PayContract(findNegotiationBetweenBlueAndRed,20);
                    PayContract payContractBetweenBlueAndVioletActivity15 = new PayContract(findNegotiationBetweenBlueAndViolet,70);

                    payContractListActivity15.add(payContractBetweenBlueAndGreenActivity15);
                    payContractListActivity15.add(payContractBetweenGreenAndYellowActivity15);
                    payContractListActivity15.add(payContractBetweenBlueAndBlackActivity15);
                    payContractListActivity15.add(payContractBetweenBlueAndRedActivity15);
                    payContractListActivity15.add(payContractBetweenBlueAndVioletActivity15);
                    activities.add(new PayContractActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId), payContractListActivity15));
                    break;

                default:
                    activities.add(new ClassicActivity(activityId, time, title, description, payResourcesListByActivity, riskAccessor.getNRisksCardByActivityID(GAME_TYPE,numberOfRisks, activityId), qualityAccessor.getNQualityCardsByActivityID(GAME_TYPE, numberOfQualities,activityId)));
                    break;
            }

        }
    }

    @Override
    public int getBudgetByRole(Role role){
        return budgetByRole.get(role.getName());

    }

    @Override
    public int getNumberOfDaysWanted() {
        return 230;
    }

    @Override
    public int getCostWanted() {
        return 200;
    }

    @Override
    public int getNumberOfRisksDrawnWanted() {
        return 20;
    }


}
