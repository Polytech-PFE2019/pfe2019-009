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
  resourceBuyed = 0;
  cardHistory = {
    risk: null,
    resource: null,
    day: null,
    money: null,
  };
  @Input() listOfData: any;
  @Input() getCardHistory: any;
  data: any[] = [];
  total = 0;
  subResource: Subscription;
  titleList: any[] = [];
  riskList: any[] = [];

  constructor(private resourceService: BuyResourceService,
              private subSerive: SubscriptionService) {
  }

  ngOnInit() {
    this.titleList = [];
    this.riskList = this.subSerive.riskHistory;
    for (const i of this.riskList) {
      this.titleList.push(i.title);
    }
    console.log(this.titleList);
    console.log(this.getCardHistory.title);
    this.titleList.push(this.getCardHistory.title);
    if (this.getCardHistory.risk !== null) {
      this.cardHistory.risk = this.getCardHistory.risk;
    }
    if (this.getCardHistory.resource !== null) {
      this.cardHistory.resource = this.getCardHistory.resource;
    }
    if (this.getCardHistory.day !== null) {
      this.cardHistory.day = this.getCardHistory.day;
    }
    if (this.getCardHistory.money !== null) {
      this.cardHistory.money = this.getCardHistory.money;
    }

    this.resourceBuyed = this.resourceService.money;
    this.data = [];
    this.total = 0;
    console.log(this.listOfData);
    for (const item of this.listOfData.payments) {
      switch (item.type) {
        case 'RISKS':
          const risk = {
            activity: 'Defaillances',
            money: item.amount,
            src: '../../../../assets/icons/faillante.png',
            result: '-' + item.bonus + ' risques'
          };
          this.total = this.total + item.amount;
          this.data.push(risk);
          break;
        case 'MANSATORY':
          const basic = {
            activity: 'Resource(s) obligatoire(s)',
            money: item.amount,
            src: '../../../../assets/icons/person.png',
            result: '-'
          };
          this.total = this.total + item.amount;
          this.data.push(basic);
          break;
        case 'DAYS':
          const day = {
            activity: 'Durées',
            money: item.amount,
            src: '../../../../assets/icons/duration.png',
            result: '-' + item.bonus + ' délai'
          };
          this.total = this.total + item.amount;
          this.data.push(day);
          break;
      }
    }
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
