import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameRoomComponent } from './main-module/game-room/game-room.component';
import { HomePageComponent } from './home-page/home-page.component';
import { GameOnComponent } from './main-module/game-on/game-on.component';
import { LoadingPageComponent } from './loading-page/loading-page.component';


const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full', component: HomePageComponent },
  { path: 'gameroom', component: GameRoomComponent },
  { path: 'gameon', component: GameOnComponent },
  { path: 'loading', component: LoadingPageComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
