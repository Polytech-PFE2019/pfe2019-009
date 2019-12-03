import {AfterContentInit, Component, ElementRef, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {GameOnService} from '../../../service/gameOnService/game-on.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-projet-information-budget',
  templateUrl: './projet-information-budget.component.html',
  styleUrls: ['./projet-information-budget.component.css']
})
export class ProjetInformationBudgetComponent implements OnInit, OnDestroy, AfterContentInit {
  @Input() currentCost = 0;
  @Input() cost: any;
  @ViewChild('currentItem', {static: true}) el: ElementRef;
  current = 56;
  list: any = [];

  money = 0;
  minCost = 0;
  maxCost = 0;
  subCost: Subscription;

  constructor(private gameService: GameOnService,
              private subscription: SubscriptionService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    this.money = this.gameService.costProject;
    console.log(this.money);

    this.subCost = this.subscription.costs$.subscribe(data => {
      console.log(data);
      this.minCost = data.minCost;
      this.current = data.minCost;
      this.maxCost = data.maxCost;
    });

    this.addItemsInList(this.currentCost);
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
    this.subCost.unsubscribe();
  }

  ngAfterContentInit(): void {
    if (this.el !== undefined) {
      console.log(this.el.nativeElement);
      this.el.nativeElement.focus();
    }
  }
}
