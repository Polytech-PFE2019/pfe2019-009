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
  @Input() addResource = 0;
  @Output() sendMoney = new EventEmitter();
  @Output() sendResource = new EventEmitter();
  moneyNb = this.baseMoney - this.moneyPayResource;
  resourceNb = this.addResource - this.payActivity;
  constructor() {
  }

  ngOnInit() {
    this.sendMoney.emit(this.moneyNb);
    this.sendResource.emit(this.resourceNb);
  }



}
