import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-projet-information',
  templateUrl: './projet-information.component.html',
  styleUrls: ['./projet-information.component.css']
})
export class ProjetInformationComponent implements OnInit, OnDestroy {
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
  subGame: Subscription;
  previousRisk = 0;
  previousDay = 0;
  previousCost = 0;
  changeTypes = [];

  constructor(private gameService: GameOnService) {
  }

  ngOnInit() {
    this.subGame = this.gameService.reponses$.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'LAUNCH_GAME':
          this.changeTypes = [];
          this.currentCost = data.costProject;
          this.currentTime = data.delayProject;
          this.currentFailure = data.failureProject;
          if (this.ifChanged(this.previousRisk, this.currentFailure)) {
            this.changeTypes.push('risk');
          }
          if (this.ifChanged(this.previousDay, this.currentTime)) {
            this.changeTypes.push('day');
          }
          if (this.ifChanged(this.previousCost, this.currentCost)) {
            this.changeTypes.push('cost');
          }
          this.previousCost = this.currentCost;
          this.previousDay = this.currentTime;
          this.previousRisk = this.currentFailure;
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
          console.log(this.changeTypes);
          break;
        case 'CHANGE_ACTIVITY':
          this.changeTypes = [];
          this.currentCost = data.costProject;
          this.currentTime = data.delayProject;
          this.currentFailure = data.failureProject;
          if (this.ifChanged(this.previousRisk, this.currentFailure)) {
            this.changeTypes.push('risk');
          }
          if (this.ifChanged(this.previousDay, this.currentTime)) {
            this.changeTypes.push('day');
          }
          if (this.ifChanged(this.previousCost, this.currentCost)) {
            this.changeTypes.push('cost');
          }
          this.previousCost = this.currentCost;
          this.previousDay = this.currentTime;
          this.previousRisk = this.currentFailure;
          console.log(this.changeTypes);
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

  ifChanged(previous, current) {
    return previous !== current;
  }

  ngOnDestroy(): void {
    this.subGame.unsubscribe();
  }

}
