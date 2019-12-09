import {Component, Input, OnInit} from '@angular/core';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Roles} from '../../model/roles';
import {Router} from "@angular/router";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  @Input() pdv = [];
  @Input() detail = [];
  // @Input() projectInfo = [];
  duration = 0;
  failure = 0;
  cost = 0;
  results = [];
  myRole: any;
  roles = Roles;

  data: any[] = [];

  constructor(private gameOnService: GameOnService,
              private router: Router,
              private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.myRole = this.subscription.myRole;
    this.cost = this.gameOnService.results.project.costProject;
    this.failure = this.gameOnService.results.project.risks;
    this.duration = this.gameOnService.results.project.days;
    this.results = this.gameOnService.results.ranking;
    this.loadData();
  }

  loadData(): void {
    for (const item of this.results) {
      console.log(item);
      const detail = {
        money: item.information.moneyOriginal,
        moneyRemain: item.information.moneyRemain,
        moneyPayed: item.information.moneyPaid,
        resourcePayed: item.information.resourcesPaid,
        resourceReamin: item.information.resourcesRemain,
        riskReduced: item.information.riskReduced,
        dayReduced: item.information.dayReduced,
        contactNegociated: item.information.contractNegotiated
      };
      const tmp = {
        title: this.getRoleById(item.player.roleID).title,
        user: item.player.username,
        id: item.player.roleID,
        point: item.NumberOfVictoryPoints,
        details: detail,
        rank: item.rank,
        src: this.getRoleById(item.player.roleID).src
      };
      this.data.push(tmp);
    }
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }


  quit() {
    console.log('quit');
    this.router.navigate(['']);
  }
}
