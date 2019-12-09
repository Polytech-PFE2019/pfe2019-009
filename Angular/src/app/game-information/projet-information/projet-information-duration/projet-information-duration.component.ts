import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {GameOnService} from "../../../service/gameOnService/game-on.service";
import {SubscriptionService} from "../../../service/subscriptionSerivce/subscription.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-projet-information-duration',
  templateUrl: './projet-information-duration.component.html',
  styleUrls: ['./projet-information-duration.component.css']
})
export class ProjetInformationDurationComponent implements OnInit, OnDestroy {
  @Input() currentDay = 0;
  // @Input() day: any;
  current = 56;
  list: any = [];
  days = 0;
  minDays = 0;
  maxDays = 0;
  subDays: Subscription;

  constructor(private gameService: GameOnService,
              private subscription: SubscriptionService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    // this.days = this.gameService.delayProject;
    // console.log(this.days);
    //
    this.subDays = this.subscription.days$.subscribe(data => {
      console.log(data);
      this.minDays = data.minTime;
      this.current = data.minTime;
      this.maxDays = data.maxTime;
    });
    this.addItemsInList(this.currentDay);
    console.log(this.list.length);
    console.log(this.list[this.list.length - 1]);
  }

  addItemsInList(c) {
    const length = this.list.length;
    if (c > this.list[length - 1]) {
      for (let i = length; i <= c + 50; i++) {
        this.list.push(i);
      }
    }
  }

  ngOnDestroy(): void {
    this.subDays.unsubscribe();
  }
}
