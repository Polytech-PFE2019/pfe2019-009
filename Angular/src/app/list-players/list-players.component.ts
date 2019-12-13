import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { SubscriptionService } from '../service/subscriptionSerivce/subscription.service';
import { History } from '../model/history';
import { GameOnService } from '../service/gameOnService/game-on.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list-players',
  templateUrl: './list-players.component.html',
  styleUrls: ['./list-players.component.css']
})
export class ListPlayersComponent implements OnInit, OnDestroy {
  @Output() close = new EventEmitter();
  histories: History[] = [];
  // @Input() getHistory = [];
  getHistory: any;
  roles: any[] = [];
  userID: any;
  totalResourcePayment: number;
  totalMoneyPayment: number;
  payments: any[] = [];
  subHistoty: Subscription;

  constructor(private subscription: SubscriptionService,
    private gameService: GameOnService) { }

  ngOnInit() {
    this.payments = [];
    this.totalResourcePayment = 0;
    this.totalMoneyPayment = 0;
    this.histories = [];
    this.roles = this.subscription.roles;
    // this.userID = this.subscription.userId;
    this.userID = 1;
    this.subHistoty = this.subscription.myHistories$.subscribe(data => {
      this.histories = data;
      console.log(this.histories);
    });
  }

  closeList() {
    this.close.emit(false);
  }


  getType(type) {
    switch (type) {
      case 'MANDATORY':
        return 'Ressource(s) obligatoire(s)';
      case 'RISKS':
        return 'Defaillance(s)';
      case 'DAYS':
        return 'Durée(s)';
    }
  }

  getBonus(type, bonus) {
    switch (type) {
      case 'MANDATORY':
        return bonus;
      case 'RISKS':
        return '-' + bonus + 'R';
      case 'DAYS':
        return '-' + bonus + 'J';
    }
  }

  ngOnDestroy(): void {
    this.subHistoty.unsubscribe();
  }
}
