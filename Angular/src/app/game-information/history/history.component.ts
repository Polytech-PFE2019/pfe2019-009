import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {BuyResourceService} from '../../service/resources/buy-resource.service';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  @Input() listOfData: any;
  @Input() getCardHistory: any;
  @Input() riskCards: any;
  @Input() activityID = 0;
  resourceBuyed = 0;
  data: any[] = [];
  total = 0;
  subResource: Subscription;
  titleList: any[] = [];
  riskList: any[] = [];
  riskAmount = 0;
  daysAmount = 0;
  basicAmount = 0;
  riskBonus = 0;
  daysBonus = 0;

  constructor(private resourceService: BuyResourceService,
              private subSerive: SubscriptionService) {
  }

  ngOnInit() {
    this.resourceBuyed = this.resourceService.money;
    this.data = [];
    this.total = 0;
    console.log(this.listOfData);
    for (const item of this.listOfData) {
      switch (item.type) {
        case 'RISKS':
          this.riskAmount += item.amount;
          this.riskBonus += item.bonus;
          break;
        case 'MANSATORY':
          this.basicAmount += item.amount;
          break;
        case 'DAYS':
          this.daysAmount += item.amount;
          this.daysBonus += item.bonus;
          break;
      }
    }

    const basic = {
      activity: 'Resource(s) obligatoire(s)',
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
}
