import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SubscriptionService} from "../service/subscriptionSerivce/subscription.service";
import {History} from "../model/history";

@Component({
  selector: 'app-list-players',
  templateUrl: './list-players.component.html',
  styleUrls: ['./list-players.component.css']
})
export class ListPlayersComponent implements OnInit {
  @Output() close = new EventEmitter();
  // @Input() getHistory = [];
  getHistory: any;
  roles: any[] = [];
  histories: History[] = [];
  userID: any;
  totalResourcePayment: number;
  totalMoneyPayment: number;
  payments:any[] =[];

  constructor(private subscription: SubscriptionService) { }

  ngOnInit() {
    this.payments =[];
    this.totalResourcePayment = 0;
    this.totalMoneyPayment = 0;
    this.histories = [];
    this.getHistory = {
      activityID: 1,
      roleID: 1,
      payments: [
        {
          amount: 3,
          type: 'DAYS',
          bonus: 2,
          extraPaying: true,
        },
        {
          amount: 2,
          type: 'MANDATORY',
          bonus: 0,
          extraPaying: false,
        },
        ],
    };
    this.roles = this.subscription.roles;
    // this.userID = this.subscription.userId;
    this.userID = 1;

    if (this.getHistory.roleID === this.userID) {
      for (const p of this.getHistory.payments){
        this.totalResourcePayment += p.amount;
        switch (p.type) {
          case 'MANDATORY':
            const basic = {
              activity: 'Resource(s) obligatoire(s)',
              amount: p.amount,
              result: ''
            };
            this.payments.push(basic);
            break;
          case 'DAYS':
            const day = {
              activity: 'Durées',
              amount: p.amount,
              result: '-' + p.bonus + ' délai'
            };
            this.payments.push(day);
            break;
          case 'RISKS':
            const risk = {
              activity: 'Defaillances',
              amount: p.amount,
              result: '-' + p.bonus + ' risques'
            };
            this.payments.push(risk);
            break;
        }
      }
      const total = {
        activity: 'Total',
        amount: this.totalResourcePayment,
      };
      this.payments.push(total);
      this.histories.push({
        activityID: this.getHistory.activityID,
        payments: this.payments,
      });
    }
  }

  closeList() {
    this.close.emit(false);
  }
}
