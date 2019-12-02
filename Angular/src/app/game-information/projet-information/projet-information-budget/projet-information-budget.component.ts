import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-projet-information-budget',
  templateUrl: './projet-information-budget.component.html',
  styleUrls: ['./projet-information-budget.component.css']
})
export class ProjetInformationBudgetComponent implements OnInit {

  list: any = [];
  current = 0;
  constructor() { }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }
  }

}
