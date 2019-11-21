import {Component, OnInit} from '@angular/core';
import {Steps} from '../model/step';

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit {

  steps: any = Steps;
  step = 'Étape 1';

  constructor() {
  }

  ngOnInit() {
  }

  getCurrentStep($event: any) {
    this.step = $event;
  }
}
