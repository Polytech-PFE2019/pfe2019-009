import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GameRoomComponent} from './game-room/game-room.component';
import {HomePageComponent} from './home-page/home-page.component';


const routes: Routes = [
  {
    path: '', redirectTo: '', pathMatch: 'full', component: HomePageComponent
  },
  {path: 'gameroom', component: GameRoomComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
