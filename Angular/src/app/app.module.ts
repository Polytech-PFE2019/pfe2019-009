import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NgZorroAntdModule, NZ_I18N, fr_FR } from 'ng-zorro-antd';
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
import { ProjetInformationDurationComponent } from
  './game-information/projet-information/projet-information-duration/projet-information-duration.component';
import { ProjetInformationBudgetComponent } from
  './game-information/projet-information/projet-information-budget/projet-information-budget.component';
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
import { RiskContainerComponent } from './commun-module/risk-back/risk-container/risk-container.component';
import { RiskBackComponent } from './commun-module/risk-back/risk-back.component';
import { LoadingComponent } from './loading/loading.component';
import { LoadingPageComponent } from './loading-page/loading-page.component';
import { PromptComponent } from './game-information/prompt/prompt.component';
import { WaitComponent } from './game-information/wait/wait.component';
import { HistoryComponent } from './game-information/history/history.component';
import { ChatListComponent } from './chat-list/chat-list.component';
import { ChatDialogueComponent } from './chat-dialogue/chat-dialogue.component';
import { ChatReceiverComponent } from './chat-receiver/chat-receiver.component';
import { ChatMessageComponent } from './chat-message/chat-message.component';

import { CountdownModule } from 'ngx-countdown';
import { ResultComponent } from './result/result.component';


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
    RiskContainerComponent,
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
    ResultComponent
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CountdownModule
  ],
  providers: [{ provide: NZ_I18N, useValue: fr_FR }, Globals, LobbyService, WebsocketService, GameOnService],
  bootstrap: [AppComponent]
})

export class AppModule {
}
