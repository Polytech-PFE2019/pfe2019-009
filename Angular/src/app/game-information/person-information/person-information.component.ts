import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit {

  baseMoney = 30;
  @Input() moneyPayResource = 0;
  @Input() payActivity = 0;
  @Output() sendMoney = new EventEmitter();
  moneyNb = this.baseMoney - this.moneyPayResource;

  constructor() {
  }

  ngOnInit() {
    this.sendMoney.emit(this.moneyNb);
  }

}
