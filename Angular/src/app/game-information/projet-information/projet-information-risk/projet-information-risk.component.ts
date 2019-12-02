import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-projet-information-risk',
  templateUrl: './projet-information-risk.component.html',
  styleUrls: ['./projet-information-risk.component.css']
})
export class ProjetInformationRiskComponent implements OnInit {
  list: any = [];
  current = 0;
  constructor() { }

  ngOnInit() {
    for (let i = 1; i < 101; i++) {
      this.list.push(i);
    }
  }

}
