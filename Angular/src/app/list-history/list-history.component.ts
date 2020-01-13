import {Component, OnDestroy, OnInit} from '@angular/core';
import {GameOnService} from '../service/gameOnService/game-on.service';
import {History} from '../model/history';
import {SubscriptionService} from '../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-list-history',
  templateUrl: './list-history.component.html',
  styleUrls: ['./list-history.component.css']
})
export class ListHistoryComponent implements OnInit, OnDestroy {
  histories: History[] = [];
  totalResourcePayment = 0;
  totalMoneyPayment = 0;
  subHistoty: Subscription;

  constructor(private gameService: GameOnService,
              private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.totalResourcePayment = 0;
    this.totalMoneyPayment = 0;
    this.subHistoty = this.subscription.myHistories$.subscribe(data => {
      this.histories = data;
      console.log(this.histories);
    });
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
