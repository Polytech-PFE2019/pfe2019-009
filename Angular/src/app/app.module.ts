import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NgZorroAntdModule, NZ_I18N, fr_FR, NzConfigService, NzConfig, NZ_CONFIG } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { registerLocaleData } from '@angular/common';
import fr from '@angular/common/locales/fr';
import { HomePageComponent } from './home-page/home-page.component';
import { RoleComponent } from './game-information/role/role.component';
import { StepComponent } from './game-information/step/step.component';
import { PersonInformationComponent } from './game-information/person-information/person-information.component';
import { GameCreatorComponent } from './modal-module/game-creator/game-creator.component';
import { GameRoomComponent } from './main-module/game-room/game-room.component';
import { AppRoutingModule } from './app-routing.module';
import { ConfirmRoleComponent } from './modal-module/role-confirm/confirm-role/confirm-role.component';
import { GameOnComponent } from './main-module/game-on/game-on.component';
import { RoleChoiceDirective } from './directives/role-choice.directive';
import { ProjetInformationComponent } from './game-information/projet-information/projet-information.component';
import { ProjetInformationDurationComponent } from './game-information/projet-information/projet-information-duration/projet-information-duration.component';
import { ProjetInformationBudgetComponent } from './game-information/projet-information/projet-information-budget/projet-information-budget.component';
import { ProjetInformationRiskComponent } from './game-information/projet-information/projet-information-risk/projet-information-risk.component';
import { Globals } from './globals';
import { BuyResourcesComponent } from './main-module/game-on/buy-resources/buy-resources.component';
import { ActivityComponent } from './main-module/game-on/activity/activity.component';
import { NegociationComponent } from './commun-module/negociation/negociation.component';
import { ResourceBuyerComponent } from './commun-module/resource-buyer/resource-buyer.component';
import { ActivityDisplayerComponent } from './commun-module/activity-displayer/activity-displayer.component';

import { LobbyService } from './service/lobbyService/lobby.service';
import { WebsocketService } from './service/webSocketService/websocket.service';
import { GameOnService } from './service/gameOnService/game-on.service';
import { RiskBackComponent } from './commun-module/risk-back/risk-back.component';
import { LoadingComponent } from './loading/loading.component';
import { LoadingPageComponent } from './loading-page/loading-page.component';
import { PromptComponent } from './game-information/prompt/prompt.component';
import { WaitComponent } from './game-information/wait/wait.component';
import { HistoryComponent } from './game-information/history/history.component';
import { ChatListComponent } from './chat-module/chat-list/chat-list.component';
import { ChatDialogueComponent } from './chat-module/chat-dialogue/chat-dialogue.component';
import { ChatReceiverComponent } from './chat-module/chat-receiver/chat-receiver.component';
import { ChatMessageComponent } from './chat-module/chat-message/chat-message.component';

import { CountdownModule } from 'ngx-countdown';
import { ResultComponent } from './game-information/result/result.component';
import { PaymentContractComponent } from './commun-module/payment-contract/payment-contract.component';
import { ListPlayersComponent } from './list-players/list-players.component';
import { ChatGroupComponent } from './chat-module/chat-group/chat-group.component';
import { ContractComponent } from './game-information/person-information/contract/contract.component';
import { ListHistoryComponent } from './list-history/list-history.component';
import { MatIconModule } from '@angular/material/icon';
import { MatBadgeModule } from '@angular/material/badge';


const ngZorroConfig: NzConfig = {
  notification: { nzDuration: 8000 }
};

registerLocaleData(fr);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    RoleComponent,
    StepComponent,
    PersonInformationComponent,
    GameCreatorComponent,
    GameRoomComponent,
    ConfirmRoleComponent,
    GameOnComponent,
    RoleChoiceDirective,
    ProjetInformationComponent,
    ProjetInformationDurationComponent,
    ProjetInformationBudgetComponent,
    ProjetInformationRiskComponent,
    BuyResourcesComponent,
    ActivityComponent,
    NegociationComponent,
    ResourceBuyerComponent,
    ActivityDisplayerComponent,
    RiskBackComponent,
    LoadingComponent,
    LoadingPageComponent,
    PromptComponent,
    WaitComponent,
    HistoryComponent,
    ChatListComponent,
    ChatDialogueComponent,
    ChatReceiverComponent,
    ChatMessageComponent,
    ResultComponent,
    PaymentContractComponent,
    ListPlayersComponent,
    ChatGroupComponent,
    ContractComponent,
    ListHistoryComponent
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CountdownModule,
    MatIconModule,
    MatBadgeModule
  ],
  providers: [{ provide: NZ_I18N, useValue: fr_FR },
    Globals, LobbyService,
  { provide: NZ_CONFIG, useValue: ngZorroConfig },
    WebsocketService, GameOnService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
