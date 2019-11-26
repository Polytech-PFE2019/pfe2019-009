import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NgZorroAntdModule, NZ_I18N, fr_FR} from 'ng-zorro-antd';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {registerLocaleData} from '@angular/common';
import fr from '@angular/common/locales/fr';
import {HomePageComponent} from './home-page/home-page.component';
import {RoleComponent} from './game-information/role/role.component';
import {StepComponent} from './game-information/step/step.component';
import {PersonInformationComponent} from './game-information/person-information/person-information.component';
import {GameCreatorComponent} from './modal-module/game-creator/game-creator.component';
import {GameRoomComponent} from './main-module/game-room/game-room.component';
import {AppRoutingModule} from './app-routing.module';
import {ConfirmRoleComponent} from './modal-module/role-confirm/confirm-role/confirm-role.component';
import {GameOnComponent} from './main-module/game-on/game-on.component';
import {RoleChoiceDirective} from './directives/role-choice.directive';
import {ProjetInformationComponent} from './game-information/projet-information/projet-information.component';
import {ProjetInformationDurationComponent} from
    './game-information/projet-information/projet-information-duration/projet-information-duration.component';
import {ProjetInformationBudgetComponent} from
    './game-information/projet-information/projet-information-budget/projet-information-budget.component';
import {ProjetInformationRiskComponent} from './game-information/projet-information/projet-information-risk/projet-information-risk.component';
import {Globals} from './globals';
import {BuyResourcesComponent} from './main-module/game-on/buy-resources/buy-resources.component';
import {ActivityComponent} from './main-module/game-on/activity/activity.component';
import { NegociationComponent } from './commun-module/negociation/negociation.component';
import { ResourceBuyerComponent } from './commun-module/resource-buyer/resource-buyer.component';
import { ActivityDisplayerComponent } from './commun-module/activity-displayer/activity-displayer.component';
import { RiskCardComponent } from './commun-module/risk-card/risk-card.component';

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
    RiskCardComponent
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [{provide: NZ_I18N, useValue: fr_FR}, Globals],
  bootstrap: [AppComponent]
})

export class AppModule {
}
