import {Component, Input, OnInit} from '@angular/core';
import {Roles} from "../../model/roles";

@Component({
  selector: 'app-payment-contract',
  templateUrl: './payment-contract.component.html',
  styleUrls: ['./payment-contract.component.css']
})
export class PaymentContractComponent implements OnInit {
  @Input() giverID = 0;
  @Input() receiverID = 0;
  @Input() percent = '';
  roles = Roles;
  giverStyle: any = null;
  receiverStyle: any = null;

  constructor() {
  }

  ngOnInit() {
    this.giverStyle = this.getStyleById(this.giverID).style;
    this.receiverStyle = this.getStyleById(this.receiverID).style;
    console.log(this.giverID, this.receiverID, this.percent);
  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }
}
