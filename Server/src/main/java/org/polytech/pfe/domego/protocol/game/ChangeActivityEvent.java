package org.polytech.pfe.domego.protocol.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.business.Messenger;
import org.polytech.pfe.domego.models.Player;
import org.polytech.pfe.domego.models.activity.negotiation.Negotiation;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.protocol.EventProtocol;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChangeActivityEvent implements EventProtocol {

    private Game game;
    private final Logger logger = Logger.getGlobal();

    public ChangeActivityEvent(Game game) {
        this.game = game;
    }

    @Override
    public void processEvent() {
        if (game.getCurrentActivity().getId() == game.getActivities().size()){
            new FinishGameEvent(game).processEvent();
        }
        else{
            game.goToTheNextActivity();
            new PayContractEvent(game).processEvent();
            logger.log(Level.INFO, "ChangeActivityEvent : In game : {0}, the current activity is now {1}", new Object[]{game.getId(), game.getCurrentActivity().getId()});
            game.getPlayers().parallelStream().forEach(player -> new Messenger(player.getSession()).sendSpecificMessageToAUser(createJsonResponse(player).toString()));
        }
    }

    private JsonObject createJsonResponse(Player player){
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,GameResponseKey.CHANGE_ACTIVITY.key);
        response.addProperty(GameResponseKey.COST_PROJECT.key,game.getProject().getCost());
        response.addProperty(GameResponseKey.DELAY_PROJECT.key,game.getProject().getDays());
        response.addProperty(GameResponseKey.FAILURE_PROJECT.key,game.getProject().getRisks());
        response.addProperty(GameResponseKey.ACTIVITY_ID.key,game.getCurrentActivity().getId());
        response.addProperty(GameResponseKey.INITIAL_DAYS.key, game.getCurrentActivity().getInitialNumberOfDays());
        response.addProperty(GameResponseKey.INITIAL_RISKS.key, game.getCurrentActivity().getInitialNumberOfRisks());
        response.addProperty(GameResponseKey.ACTUAL_DAYS.key, game.getCurrentActivity().getNumberOfDays());
        response.addProperty(GameResponseKey.ACTUAL_RISKS.key, game.getCurrentActivity().getRiskCardList().size());
        JsonArray extraPaymentArray = new JsonArray();
        for (PayResources payResources : game.getCurrentActivity().getPayResourcesList()) {
            if (payResources.isExtraPayment()){
                JsonObject extraPayment = new JsonObject();
                for (Integer amount : payResources.getPriceAndBonusMap().keySet()) {
                    extraPayment.addProperty(GameResponseKey.ROLE_ID.key, payResources.getRoleID());
                    extraPayment.addProperty(GameResponseKey.AMOUNT.key, amount);
                }

                extraPaymentArray.add(extraPayment);
            }
        }
        response.add(GameResponseKey.EXTRA_PAYING.key, extraPaymentArray);
        response.add(GameResponseKey.CONTRACTS_GIVER.key, createJsonContractGiverArray(player));
        response.add(GameResponseKey.CONTRACTS_RECEIVER.key, createJsonContractReceiverArray(player));

        return response;
    }

    private JsonArray createJsonContractGiverArray(Player player){

        JsonArray contractsArrayGiver = new JsonArray();
        List<Negotiation> contractsGiver = game.getContracts().stream().filter(contract -> contract.getGiverRoleID() == player.getRole().getId() && contract.getAmountNegotiated() != 0).collect(Collectors.toList());
        contractsGiver.forEach(contract-> {
            JsonObject contractJson = createJsonContract(contract);
            contractsArrayGiver.add(contractJson);
        });
        return contractsArrayGiver;
    }

    private JsonArray createJsonContractReceiverArray(Player player){
        JsonArray contractsArrayReceiver= new JsonArray();

        List<Negotiation> contractsReceiver = game.getContracts().stream().filter(contract -> contract.getReceiverRoleID() == player.getRole().getId() && contract.getAmountNegotiated() > 0).collect(Collectors.toList());

        contractsReceiver.forEach(contract-> {
            JsonObject contractJson = createJsonContract(contract);
            contractsArrayReceiver.add(contractJson);
        });
        return contractsArrayReceiver;

    }

    private JsonObject createJsonContract(Negotiation contract){
        JsonObject contractJson = new JsonObject();
        contractJson.addProperty(GameResponseKey.GIVERID.key, contract.getGiverRoleID());
        contractJson.addProperty(GameResponseKey.RECEIVERID.key, contract.getReceiverRoleID());
        contractJson.addProperty(GameResponseKey.AMOUNT.key, contract.getAmountNegotiated());
        contractJson.addProperty(GameResponseKey.AMOUNT_PAID.key, contract.getAmountNegotiated() - contract.getAmountLeftToPay());
        return contractJson;
    }
}
