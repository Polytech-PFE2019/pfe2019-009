package org.polytech.pfe.domego.database.accessor;

import org.polytech.pfe.domego.database.repository.RoleRepository;
import org.polytech.pfe.domego.models.Objective;
import org.polytech.pfe.domego.models.ObjectiveType;
import org.polytech.pfe.domego.models.Role;
import org.polytech.pfe.domego.models.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleAccessor {

    private final RoleRepository roleDB;

    @Autowired
    public RoleAccessor(RoleRepository db) {
        this.roleDB = db;
        initDBRole();
    }

    public List<Role> getAllRoles(){
        return roleDB.findAll();
    }

    public Optional<Role> getSpecificRoleById(int id){
        return roleDB.findRoleById(id);
    }

    void initDBRole(){

            roleDB.deleteAll();
            roleDB.save(new Role(RoleType.NON_DEFINI.getId(),RoleType.NON_DEFINI,"",0,"",new ArrayList<>()));
            roleDB.save(generateMaitreDOuvrage());
            roleDB.save(generateMaitreDOeuvre());
            roleDB.save(generateBureauDEtude());
            roleDB.save(generateBureauDeControle());
            roleDB.save(generateEntrepriseGrosOeuvre());
            roleDB.save(generateEntrepriseCorpsEtatSecondaire());

    }

    Role generateMaitreDOuvrage(){

        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.DAYS,1,true);
        Objective moneyObjective = new Objective(ObjectiveType.MONEY, 1, true);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 1, true);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.MAITRE_D_OUVRAGE.getId(),RoleType.MAITRE_D_OUVRAGE,
                "Le maître d’ouvrage est la personne pour laquelle est réalisée l’ouvrage." +
                        " Le maître d'ouvrage est le porteur du projet, il définit l'objectif du projet," +
                        " son calendrier et le budget. Le résultat attendu du projet est la réalisation" +
                        " et la livraison d'un résultat, appelé l'ouvrage.",
                150,"Budget projet : 135k\n" + "Délai : 380 j\n" + "Défaillance : 20", objectiveList);

    }

    Role generateMaitreDOeuvre(){
        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.DAYS_AND_BUDGET,5,false);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 1, true);
        Objective moneyObjective = new Objective(ObjectiveType.MONEY, 1, true);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.MAITRE_D_OEUVRE.getId(),RoleType.MAITRE_D_OEUVRE,
                "Le maitre d’oeuvre (souvent l’architecte) est le chef de projet de construction," +
                        " la personne physique ou morale qui dirige et vérifie la bonne exécution des travaux." +
                        " En tant qu’architecte, il doit aussi concevoir le projet en répondant aux exigences du maitre d’ouvrage.",
                30,"Objectif négociation :\n" + "Recevoir entre 80k et 115k", objectiveList);
    }

    Role generateBureauDEtude(){
        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.MONEY,2,true);
        Objective moneyObjective = new Objective(ObjectiveType.DAYS, 2, false);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 2, false);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.BUREAU_D_ETUDE.getId(),RoleType.BUREAU_D_ETUDE,
                "Le bureau d’étude doit assister l’architecte sur les spécificités techniques qui relèvent de sa compétence." +
                        " Ils assurent des études techniques spécifiques : étude de la structure, étude de sol," +
                        " étude thermique, étude acoustique, étude des réseaux...",
                20,"Objectif négociation :\n" + "Recevoir entre 10k et 20k", objectiveList);
    }

    Role generateBureauDeControle(){
        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.MONEY,1,true);
        Objective moneyObjective = new Objective(ObjectiveType.DAYS, 1, false);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 5, false);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.BUREAU_DE_CONTROLE.getId(),RoleType.BUREAU_DE_CONTROLE,
                "Le bureau de contrôle juge de la solidité de l'ouvrage," +
                        " et vérifie le respect des normes et des règles de construction – appelées souvent" +
                        " « règles de l'art ». Il a une responsabilité juridique vis à vis du respect des différentes normes et réglementation.",
                20,"Objectif négociation :\n" + "Recevoir entre 10k et 25k", objectiveList);
    }

    Role generateEntrepriseGrosOeuvre(){
        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.MONEY,2,true);
        Objective moneyObjective = new Objective(ObjectiveType.DAYS, 2, false);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 2, false);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.ENTREPRISE_GROS_OEUVRE.getId(),RoleType.ENTREPRISE_GROS_OEUVRE,
                "Ces entreprises ont pour but de bâtir l’ossature de l’ouvrage. Cela comprend les fondations," +
                        " les poutres, les poteaux, les murs, la charpente, le dallage… " +
                        "Ils ont aussi souvent en charge les installations de chantier et le terrassement.",
                30,"Objectif négociation :\n" +  "Recevoir entre 25k et 50k", objectiveList);
    }

    Role generateEntrepriseCorpsEtatSecondaire(){
        List<Objective> objectiveList = new ArrayList<>();
        Objective daysObjective = new Objective(ObjectiveType.MONEY,2,true);
        Objective moneyObjective = new Objective(ObjectiveType.DAYS, 2, false);
        Objective risksObjective = new Objective(ObjectiveType.RISK, 2, false);

        objectiveList.add(daysObjective);
        objectiveList.add(moneyObjective);
        objectiveList.add(risksObjective);
        return new Role(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(),RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE,
                "Leur rôle est de construire tout ce qui n’est pas assuré par le gros oeuvre." +
                        " Il s’agit des cloisons et plâtrerie, de la peinture, de l’électricité, de la ventilation," +
                        " des menuiseries, des revêtements de sol, de la plomberie…",
                30,"Objectif négociation :\n" +  "Recevoir entre 20k et 40k",
        objectiveList);
    }


}
