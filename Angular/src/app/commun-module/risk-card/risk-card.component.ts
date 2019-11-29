import {Component, Input, OnInit} from '@angular/core';
import {Risks} from '../../model/risks';

@Component({
  selector: 'app-risk-card',
  templateUrl: './risk-card.component.html',
  styleUrls: ['./risk-card.component.css']
})
export class RiskCardComponent implements OnInit {
  @Input() step: number;
  risks: any = Risks;
  random = 0;
  riskRemainNb = 2;
  cardNb = 0;
  testid = 0;
  test = {
    id: 0,
    step: 0,
    title: '',
    risk: [1, 2]
  };
  riskUnavailable = (this.riskRemainNb === 0);
  constructor() { }
  drawCard(): void {
    if (this.riskRemainNb !== 0) {
      this.riskRemainNb --;
      this.cardNb++;
    }
    this.riskUnavailable = (this.riskRemainNb === 0);
    this.random = Math.ceil(Math.random() * 5);
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
