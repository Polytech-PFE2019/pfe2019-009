import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Risks} from '../../model/risks';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-risk-back',
  templateUrl: './risk-back.component.html',
  styleUrls: ['./risk-back.component.css']
})
export class RiskBackComponent implements OnInit {
  @Input() step: number;
  @Output() sendCard = new EventEmitter();
  risks: any = Risks;
  random = 0;
  riskRemainNb = 2;
  cardNb = 0;
  riskUnavailable = (this.riskRemainNb === 0);
  currentRiskTotal = 5; // to be input
  testid = 0;
  test = {
    id: 0,
    step: 0,
    title: '',
    risk: null,
    resource: null,
    day: null,
    money: null,
  };
  constructor(private subService: SubscriptionService) { }
  getRandomFromSon(event) {
    this.random = event;
    this.drawCard();
  }
  drawCard(): void {
    if (this.riskRemainNb !== 0) {
      this.riskRemainNb --;
      this.cardNb++;
    }
    this.riskUnavailable = (this.riskRemainNb === 0);
    this.random = Math.ceil(Math.random() * this.currentRiskTotal);
    console.log(this.random);
    console.log(this.risks);
    this.getId(this.random);
  }

  getId($event) {
    console.log($event);
    this.testid = $event;
    const tmp = this.risks.filter(next => next.id === this.testid )[0];
    console.log(this.risks.filter(next => next.id === this.testid )[0]);
    this.test.id = this.risks.filter(next => next.id === this.testid )[0].id;
    this.test.title = this.risks.filter(next => next.id === this.testid )[0].title;
    this.test.risk = this.risks.filter(next => next.id === this.testid )[0].risk;
    this.test.day = this.risks.filter(next => next.id === this.testid )[0].day;
    this.test.money = this.risks.filter(next => next.id === this.testid )[0].money;
    this.test.resource = this.risks.filter(next => next.id === this.testid )[0].resource;
    this.subService.sendHistory(this.test);
    console.log(this.test);
    console.log(this.subService.riskHistory);
  }
  ngOnInit() {
  }

}
