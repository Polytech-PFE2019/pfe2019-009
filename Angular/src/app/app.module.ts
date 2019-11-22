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
import { RoleComponent } from './role/role.component';
import { StepComponent } from './step/step.component';
import { PersonInformationComponent } from './person-information/person-information.component';
import { GameCreatorComponent } from './modal-module/game-creator/game-creator.component';
import { GameRoomComponent } from './game-room/game-room.component';
import { AppRoutingModule } from './app-routing.module';
import { ConfirmRoleComponent } from './modal-module/role-confirm/confirm-role/confirm-role.component';
import { GameOnComponent } from './game-on/game-on.component';
import { RoleChoiceDirective } from './directives/role-choice.directive';
import { ProjetInformationComponent } from './projet-information/projet-information.component';
import { ProjetInformationDurationComponent } from './projet-information/projet-information-duration/projet-information-duration.component';
import { ProjetInformationBudgetComponent } from './projet-information/projet-information-budget/projet-information-budget.component';
import { ProjetInformationRiskComponent } from './projet-information/projet-information-risk/projet-information-risk.component';
import { ActivitiesComponent } from './activities/activities.component';

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
    ActivitiesComponent
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [{ provide: NZ_I18N, useValue: fr_FR }],
  bootstrap: [AppComponent]
})
export class AppModule { }
