package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.components.calculator.InfoProjectGameCalculator;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.Project;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FinishGameEvent implements EventProtocol {

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Game game;

    public FinishGameEvent(Game game) {
        this.game = game;
    }


    @Override
    public void processEvent() {

        logger.log(Level.INFO,"Game : The game {0} is now Finished", this.game.getId());
        this.game.getPlayers().forEach(player -> {
            player.calculateObjectivesVictoryPoints(game.getDelayDelta(),game.getRisksDelta(),game.getBudgetDelta());
            player.addObjectivesVictoryPoints();
        });

        List<Player> rankedList = rankPlayers(this.game.getPlayers());

        this.game.getPlayers().parallelStream().forEach(player -> {
            new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse(player,rankedList).toString());
        }
        );

    }

    private List<Player> rankPlayers(List<Player> playerList){
        Comparator<Player> comparator = Comparator.comparing(Player::getVictoryPoints);
        playerList.sort(Collections.reverseOrder(comparator));
        return playerList;
    }

    private JsonObject createJsonResponse(Player player, List<Player> rankedList) {
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.FINISH.key);
        response.add(GameResponseKey.PROJECT.key, createProjectObject(game.getProject()));
        response.add(GameResponseKey.INFORMATION.key, createInformationObject(player));
        JsonArray ranking = new JsonArray();
        for (Player rankPlayer : rankedList) {
            JsonObject rankingPlayer = new JsonObject();
            rankingPlayer.addProperty(GameResponseKey.NOVP.key,rankPlayer.getVictoryPoints());
            rankingPlayer.addProperty(GameResponseKey.RANK.key,rankPlayer.getRole().getId());
            JsonObject playerJSON = new JsonObject();
            playerJSON.addProperty(GameResponseKey.USERNAME.key, rankPlayer.getName());
            playerJSON.addProperty(GameResponseKey.ROLE_ID.key, rankPlayer.getRole().getName().getId());
            rankingPlayer.add(GameResponseKey.PLAYER.key,playerJSON);
            ranking.add(rankingPlayer);
        }
        response.add(GameResponseKey.RANKING.key, ranking);
        return response;
    }

    private JsonElement createProjectObject(Project project) {
        JsonObject projectJson = new JsonObject();
        projectJson.addProperty(GameResponseKey.COST_PROJECT.key, project.getCost());
        projectJson.addProperty(GameResponseKey.DAYS.key, project.getDays());
        projectJson.addProperty(GameResponseKey.RISKS.key, project.getRisks());
        return projectJson;
    }

    private JsonElement createInformationObject(Player player){
        JsonObject informationJson = new JsonObject();
        informationJson.addProperty("moneyOriginal", player.getRole().getBudget());
        informationJson.addProperty("moneyRemain", player.getMoney());
        informationJson.addProperty("moneyPaid", player.getRole().getBudget() - player.getMoney());
        informationJson.addProperty("resourcesPaid", player.getResourcesAmount());
        informationJson.addProperty("resourcesRemain", player.getResourcesAmount());
        informationJson.addProperty("riskReduced", 1);
        informationJson.addProperty("dayReduced", 2);
        informationJson.addProperty("contractNegotiated",3);
        return informationJson;

    }
}
