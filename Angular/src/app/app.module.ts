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

registerLocaleData(fr);

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    RoleComponent,
    StepComponent,
    PersonInformationComponent
  ],
  imports: [
    BrowserModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: NZ_I18N, useValue: fr_FR }],
  bootstrap: [AppComponent]
})
export class AppModule { }
