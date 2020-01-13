package org.polytech.pfe.domego;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.polytech.pfe.domego.components.business.Game;
import org.polytech.pfe.domego.components.statefull.GameInstance;
import org.polytech.pfe.domego.database.accessor.RoleAccessor;
import org.polytech.pfe.domego.generator.GameType;
import org.polytech.pfe.domego.generator.initial.InitialGameGenerator;
import org.polytech.pfe.domego.models.*;
import org.polytech.pfe.domego.models.activity.*;
import org.polytech.pfe.domego.models.activity.Activity;
import org.polytech.pfe.domego.models.activity.buying.BuyResources;
import org.polytech.pfe.domego.models.activity.buying.BuyingResourcesActivity;
import org.polytech.pfe.domego.models.activity.pay.PayResources;
import org.polytech.pfe.domego.protocol.game.LaunchGameEvent;
import org.polytech.pfe.domego.protocol.game.key.GameResponseKey;
import org.polytech.pfe.domego.services.sockets.game.GameRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameRequestHandlerTest {
    private final RoleAccessor roleAccessor;

    @Mock
    WebSocketSession sessionPlayerTest = mock(WebSocketSession.class);

    @Autowired
    GameRequestHandlerTest(RoleAccessor roleAccessor) {
        this.roleAccessor = roleAccessor;
    }

    GameRequestHandler handler;

    Game game;

    @BeforeEach
    @Test
    public void setUp() {
        handler = new GameRequestHandler();


        when(sessionPlayerTest.isOpen()).thenReturn(true);

        InitialGameGenerator initialGameGenerator = new InitialGameGenerator();
        game = new Game(UUID.randomUUID().toString(),new ArrayList<>(), initialGameGenerator.getAllActivitiesOfTheGame(), initialGameGenerator.getCostWanted(), initialGameGenerator.getNumberOfDaysWanted(), initialGameGenerator.getNumberOfRisksDrawnWanted(), GameType.INITIAL);

        GameInstance gameInstance = GameInstance.getInstance();
        gameInstance.addGame(game);

        List<Player> playerList = createPlayers();
        game.setPlayers(playerList);

        assertEquals(game,gameInstance.getSpecificGameByID(game.getId()).get());

    }

    @Test
    public void testJoiningGame() throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("request","JOIN_GAME");
        request.addProperty("gameID", game.getId());
        request.addProperty("userID", game.getPlayers().get(0).getID());

        Map<String,String> value = new Gson().fromJson(request, Map.class);

        handler.handleRequest(sessionPlayerTest, value);

        assertEquals(1,game.getPlayersPresent().size());
        Player player = game.getPlayersPresent().get(0);
        assertEquals(sessionPlayerTest, player.getSession());

        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key,"OK");
        response.addProperty(GameResponseKey.USER_ID.key, player.getID());
        response.addProperty(GameResponseKey.GAME_ID.key,game.getId());
        response.addProperty(GameResponseKey.NOPC.key, game.getPlayersPresent().size());



        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));
    }

    @Test
    public void testLaunchGame() throws Exception {
        for (Player player : game.getPlayers() ) {
            JsonObject request = new JsonObject();
            request.addProperty("request","JOIN_GAME");
            request.addProperty("gameID", game.getId());
            request.addProperty("userID", player.getID());

            Map<String,String> value = new Gson().fromJson(request, Map.class);

            handler.handleRequest(sessionPlayerTest, value);

        }

        assertEquals(game.getPlayers().size(),game.getPlayersPresent().size());
        for (Player player : game.getPlayers()) {
            assertEquals(sessionPlayerTest, player.getSession());
            verify(sessionPlayerTest, times(1)).sendMessage(
                    new TextMessage(new LaunchGameEvent(game).createUpdateResponse(player)));
        }



    }

    @Test
    public void testBuyResource() throws Exception {

        Player player = game.getPlayers().get(0);
        JsonObject request = new JsonObject();
        request.addProperty("request","BUY_RESOURCES");
        request.addProperty("gameID", game.getId());
        request.addProperty("userID", player.getID());
        request.addProperty("amount", "1");

        Map<String,String> value = new Gson().fromJson(request, Map.class);

        double moneyPlayer = player.getMoney();
        int resourcesPlayer = player.getResourcesAmount();

        handler.handleRequest(sessionPlayerTest, value);

        //The first activity must be of this type. //If not, set the current activity as the one you want.
        BuyingResourcesActivity activity = (BuyingResourcesActivity) game.getCurrentActivity();
        //BuyResource of the activity must be for roleID Maitre D'ouvrage.
        //Rate must be 1
        BuyResources buyResources = activity.getBuyResourcesByRoleID(RoleType.MAITRE_D_OUVRAGE.getId());
        assertTrue(buyResources.hasPaid());
        assertEquals(1,buyResources.getAmountPaid());
        assertEquals(1,buyResources.getResourcesGiven());

        assertEquals(moneyPlayer-1,player.getMoney());
        assertEquals(resourcesPlayer+1,player.getResourcesAmount());

        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "BUY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        response.addProperty(GameResponseKey.BUYING_RESOURCES.key,1);
        response.addProperty(GameResponseKey.MONEY.key, player.getMoney());
        response.addProperty(GameResponseKey.PRICE.key, 1);
        response.addProperty(GameResponseKey.ROLE_ID.key, player.getRole().getId());

        //test response
        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));

    }

    //TODO Prendre en compte les cartes risks
    public void testPayResources() throws Exception {

        setSessionToPlayers();
        Player player = game.getPlayers().get(0);
        JsonObject request = new JsonObject();
        request.addProperty("request","PAY_RESOURCES");
        request.addProperty("gameID", game.getId());
        request.addProperty("userID", player.getID());

        JsonArray payments = new JsonArray();
        JsonObject paymentMandatory = new JsonObject();
        paymentMandatory.addProperty("amount", "1");
        paymentMandatory.addProperty("type", "MANDATORY");
        payments.add(paymentMandatory);
        request.add("payments",payments);

        Activity activity = game.getCurrentActivity();

        Map<String,?> value = new Gson().fromJson(request, Map.class);

        double moneyPlayer = player.getMoney();
        int resourcesPlayer = player.getResourcesAmount();

        handler.handleRequest(sessionPlayerTest, value);

        //The first activity must be of this type. //If not, set the current activity as the one you want.

        //One of the PayResource of the activity must be for roleID Maitre D'ouvrage.
        //and amount needed must be one for mandatory type

        Optional<PayResources> payResourcesOpt = activity.getPayResourcesList().stream().filter(payResource -> ((payResource.getRoleID() == RoleType.MAITRE_D_OUVRAGE.getId()) && payResource.getPayResourceType().equals(PayResourceType.MANDATORY))).findAny();

        assertTrue(payResourcesOpt.isPresent());

        PayResources payResources = payResourcesOpt.get();

        assertTrue(payResources.hasPaid());
        assertEquals(1,payResources.getAmountPaid());
        assertEquals(0,payResources.getBonusGiven());

        assertEquals(resourcesPlayer-1,player.getResourcesAmount());

        //test response
        JsonObject response = new JsonObject();
        response.addProperty(GameResponseKey.RESPONSE.key, "PAY_RESOURCES");
        response.addProperty(GameResponseKey.RESOURCES.key, player.getResourcesAmount());
        //response.addProperty(GameResponseKey.BONUSTYPE.key, "MANDATORY");

        verify(sessionPlayerTest, times(1)).sendMessage(
                new TextMessage(response.toString()));
    }

    private List<Player> createPlayers(){
        List<Player> playerList = new ArrayList<>();
        for(int i=0; i<6; i++){
            Player player = new Player(sessionPlayerTest,"name");
            player.addMoney(30000);
            player.addResources(10);

            Objective objective = new Objective(ObjectiveType.DAYS,1,false);
            List<Objective> objectiveList = new ArrayList<>();
            objectiveList.add(objective);

            Role role;
            switch(i){
                case 0 :
                    role = new Role(RoleType.MAITRE_D_OUVRAGE.getId(), RoleType.MAITRE_D_OUVRAGE, "description",30000,"special",objectiveList);
                    break;
                case 1 :
                    role = new Role(RoleType.MAITRE_D_OEUVRE.getId(), RoleType.MAITRE_D_OEUVRE, "description",30000,"special", objectiveList);
                    break;
                case 2 :
                    role = new Role(RoleType.BUREAU_D_ETUDE.getId(), RoleType.BUREAU_D_ETUDE, "description",30000,"special", objectiveList);
                    break;
                case 3 :
                    role = new Role(RoleType.BUREAU_DE_CONTROLE.getId(), RoleType.BUREAU_DE_CONTROLE, "description",30000,"special", objectiveList);
                    break;
                case 4 :
                    role = new Role(RoleType.ENTREPRISE_GROS_OEUVRE.getId(), RoleType.ENTREPRISE_GROS_OEUVRE, "description",30000,"special", objectiveList);
                    break;
                case 5 :
                    role = new Role(RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE.getId(), RoleType.ENTREPRISE_CORPS_ETAT_SECONDAIRE, "description",30000,"special", objectiveList);
                    break;
                default:
                    role = new Role();
            }
            player.setRole(role);
            playerList.add(new Player(player));
        }

        return playerList;

    }

    //For the update response
    private void setSessionToPlayers(){
        game.getPlayers().get(0).setSession(sessionPlayerTest);
        for(int i=1; i<6;i++){
            WebSocketSession session = mock(WebSocketSession.class);

            game.getPlayers().get(i).setSession(session);
        }

    }
}