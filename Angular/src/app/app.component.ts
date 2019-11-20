import {Component, ViewChild} from '@angular/core';
import {GameCreatorComponent} from "./modal-module/game-creator/game-creator.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  @ViewChild(GameCreatorComponent, {static: true})
  gameCreator;
  title = 'Angular';

  createASalon() {
    this.gameCreator.isCreatingSalon = true;
  }
}
