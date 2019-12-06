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

  constructor(private resourceService: BuyResourceService,
              private subSerive: SubscriptionService) {
  }

  ngOnInit() {
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
