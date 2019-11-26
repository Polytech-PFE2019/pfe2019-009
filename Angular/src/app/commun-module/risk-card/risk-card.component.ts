import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-risk-card',
  templateUrl: './risk-card.component.html',
  styleUrls: ['./risk-card.component.css']
})
export class RiskCardComponent implements OnInit {
  random = 0;
  constructor() { }
  randomCard(): void {
    this.random = Math.ceil(Math.random() * 10);
  }
  ngOnInit() {
  }

}
