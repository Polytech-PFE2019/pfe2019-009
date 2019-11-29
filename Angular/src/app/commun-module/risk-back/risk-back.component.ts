import {Component, Input, OnInit} from '@angular/core';
import {Risks} from '../../model/risks';

@Component({
  selector: 'app-risk-back',
  templateUrl: './risk-back.component.html',
  styleUrls: ['./risk-back.component.css']
})
export class RiskBackComponent implements OnInit {
  @Input() step: number;
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
    risk: [1, 2]
  };
  constructor() { }
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
  }
  ngOnInit() {
  }

}
