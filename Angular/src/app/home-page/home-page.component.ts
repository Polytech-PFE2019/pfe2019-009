import {Component, OnInit, ViewChild} from '@angular/core';
import {GameCreatorComponent} from "../modal-module/game-creator/game-creator.component";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  @ViewChild(GameCreatorComponent, {static: true})
  gameCreator;

  constructor() {
  }

  ngOnInit() {
  }

  createASalon() {
    this.gameCreator.isCreatingSalon = true;
  }
}
