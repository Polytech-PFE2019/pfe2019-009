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
import { StepComponent } from './step/step.component';
import { PersonInformationComponent } from './game-information/person-information/person-information.component';
import { GameCreatorComponent } from './modal-module/game-creator/game-creator.component';
import { GameRoomComponent } from './game-room/game-room.component';
import { AppRoutingModule } from './app-routing.module';
import { ConfirmRoleComponent } from './modal-module/role-confirm/confirm-role/confirm-role.component';
import { GameOnComponent } from './game-on/game-on.component';
import { RoleChoiceDirective } from './directives/role-choice.directive';


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
    Activity1Component,
    Activity2Component
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [{ provide: NZ_I18N, useValue: fr_FR }, Globals],
  bootstrap: [AppComponent]
})

export class AppModule { }
