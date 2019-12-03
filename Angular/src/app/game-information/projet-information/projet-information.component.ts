import {Component, OnInit} from '@angular/core';
import {GameOnService} from "../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-projet-information',
  templateUrl: './projet-information.component.html',
  styleUrls: ['./projet-information.component.css']
})
export class ProjetInformationComponent implements OnInit {
  currentTime = 0;
  currentCost = 0;
  currentFailure = 0;
  tabs = [
    {
      active: true,
      name: 'La durée',
      icon: 'calendar',
      id: 1
    },
    {
      active: false,
      name: 'La defaillance',
      icon: 'warning',
      id: 2
    },
    {
      active: false,
      name: 'Le cout',
      icon: 'money-collect',
      id: 3
    },
  ];
  costs: any;
  days: any;
  risks: any;

  constructor(private gameService: GameOnService) {
  }

  // costProject: 0
  // currentActivityID: 1
  // delayProject: 0
  // failureProject: 0

  ngOnInit() {
    this.gameService.messages.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'LAUNCH_GAME':
          this.currentCost = data.costProject;
          this.currentTime = data.delayProject;
          this.currentFailure = data.failureProject;
          this.costs = {
            minCost: data.project.minCost,
            maxCost: data.project.maxCost
          };
          this.days = {
            minTime: data.project.minTime,
            manTime: data.project.maxTime
          };
          this.risks = {
            minFailure: data.project.minFailure,
            maxFailure: data.project.maxFailure
          };
          break;
        case 'CHANGE_ACTIVITY':
          this.currentCost = data.costProject;
          this.currentTime = data.delayProject;
          this.currentFailure = data.failureProject;
          break;
        case 'UPDATE_PAYEMENT':
          this.costs = {
            minCost: data.project.minCost,
            maxCost: data.project.maxCost
          };
          this.days = {
            minTime: data.project.minTime,
            manTime: data.project.maxTime
          };
          this.risks = {
            minFailure: data.project.minFailure,
            maxFailure: data.project.maxFailure
          };
          break;
      }
    });

  }

}
