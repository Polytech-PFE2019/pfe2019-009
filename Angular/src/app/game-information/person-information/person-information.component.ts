import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit {
  baseRes = 18;
  addRes = 0;
  pay = 0;
  baseMoney = 30;
  @Input() moneyPayResource = 0;
  @Input() payActivity = 0;

  constructor() {
  }

  ngOnInit() {
  }

}
