import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { BuyResourceService } from '../../service/resources/buy-resource.service';
import { SubscriptionService } from '../../service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @Input() listOfData = [];
  @Input() getCardHistory: any;
  @Input() riskCards: any;
  @Input() activityID = 0;
  resourceBuyed = 0;
  data: any[] = [];
  total = 0;
  riskAmount = 0;
  daysAmount = 0;
  basicAmount = 0;
  riskBonus = 0;
  daysBonus = 0;
  riskDetail: any[] = [];
  dayDetail: any[] = [];
  basicDetail: any[] = [];
  totalDetail: any[] = [];
  roles: any[] = [];

  constructor(private resourceService: BuyResourceService,
    private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.roles = this.subscription.roles;
    this.resourceBuyed = this.resourceService.money;
    this.data = [];
    this.total = 0;
    this.totalDetail = [];
    console.log(this.listOfData);
    for (const item of this.listOfData) {
      if (item.amount !== 0) {
        switch (item.type) {
          case 'RISKS':
            this.riskDetail.push({
              type: 'bonus',
              player: this.getRoleById(item.roleID).title,
              amount: item.amount,
              bonus: item.bonus,
            });
            this.riskAmount += item.amount;
            this.riskBonus += item.bonus;
            break;
          case 'MANDATORY':
            this.basicDetail.push({
              type: 'basic',
              player: this.getRoleById(item.roleID).title,
              amount: item.amount,
              bonus: '',
            });
            this.basicAmount += item.amount;
            break;
          case 'DAYS':
            this.dayDetail.push({
              type: 'bonus',
              player: this.getRoleById(item.roleID).title,
              amount: item.amount,
              bonus: item.bonus,
            });
            this.daysAmount += item.amount;
            this.daysBonus += item.bonus;
            break;
        }
      }

    }

    const basic = {
      activity: 'Ressource(s) obligatoire(s)',
      money: this.basicAmount,
      src: '../../../../assets/icons/person.png',
      result: '-'
    };

    const risk = {
      activity: 'Defaillances',
      money: this.riskAmount,
      src: '../../../../assets/icons/faillante.png',
      result: '-' + this.riskBonus + ' risques'
    };

    const day = {
      activity: 'Durées',
      money: this.daysAmount,
      src: '../../../../assets/icons/duration.png',
      result: '-' + this.daysBonus + ' délai'
    };
    this.data.push(day);
    this.data.push(risk);
    this.data.push(basic);
    console.log(this.data);
    this.total = this.daysAmount + this.basicAmount + this.riskAmount;
    const total = {
      activity: 'Total',
      money: this.total,
      result: '-',
      src: '../../../../assets/icons/sum-2.png',
    };
    this.data.push(total);
    console.log(this.data);
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }

  getDetailByType(type) {
    switch (type) {
      case 'Defaillances':
        return this.riskDetail;
      case 'Durées':
        return this.dayDetail;
      case 'Ressource(s) obligatoire(s)':
        return this.basicDetail;
      default:
        if (this.totalDetail.length === 0) {
          this.totalDetail.push({
            type: 'total',
            hint: 'Paiement total: ' + this.total,
          });
        }
        return this.totalDetail;
    }
  }


}
