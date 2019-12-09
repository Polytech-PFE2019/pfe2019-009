import {AfterContentInit, Component, ElementRef, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {GameOnService} from "../../../service/gameOnService/game-on.service";
import {SubscriptionService} from "../../../service/subscriptionSerivce/subscription.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-projet-information-risk',
  templateUrl: './projet-information-risk.component.html',
  styleUrls: ['./projet-information-risk.component.css']
})
export class ProjetInformationRiskComponent implements OnInit, OnDestroy, AfterContentInit {
  @Input() currentRisk = 0;
  @Input() risk: any;
  current = 0;
  list: any = [];
  risks = 0;
  subRisk: Subscription;
  minRisk = 0;
  maxRisk = 0;

  constructor(private gameService: GameOnService,
              private subscription: SubscriptionService) {
  }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }

    this.risks = this.gameService.failureProject;
    console.log(this.risks);

    this.subRisk = this.subscription.failures$.subscribe(data => {
      this.minRisk = data.minFailure;
      this.maxRisk = data.maxFailure;
    });


    // if (this.maxRisk - this.currentRisk <= 5) {
    //   this.signal.nativeElement.style.backgroundColor = '#C50E05';
    // } else if (this.maxRisk - this.currentRisk > 15) {
    //   this.signal.nativeElement.style.backgroundColor = '#008000';
    // } else {
    //   this.signal.nativeElement.style.backgroundColor = '#F68C12';
    // }
    // this.signal.nativeElement.querySelector('signal').addEventListener('mouseover',
    //   this.test());

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
    this.subRisk.unsubscribe();
  }

  ngAfterContentInit(): void {

  }

  test() {
    // console.log('test');
  }
}
