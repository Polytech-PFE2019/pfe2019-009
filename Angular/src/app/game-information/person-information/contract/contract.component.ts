import {Component, Input, OnInit} from '@angular/core';
import {Roles} from '../../../model/roles';

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit {
  @Input() withRole = 1;
  @Input() isPay = false;
  @Input() data = [
    {
      withRole: 1,
      isPay: false,
      amount: 80
    },
    {
      withRole: 4,
      isPay: true,
      amount: 30
    }, {
      withRole: 5,
      isPay: true,
      amount: 25
    },
  ];
  @Input() payments = [
    {
      withRole: 3,
      amount: 10,
    },
    {
      withRole: 4,
      amount: 15,
    },
    {
      withRole: 5,
      amount: 10,
    }
    ]
  ;
  total = 100;
  payment = 50;

  myRoleId = 2;
  isSigne = true;
  isPayment = false;
  isReceive = false;
  roles = Roles;
  amount = 100;
  tabs = ['Contrat à payer', 'Contrat payé'];
  tab = ['Contrat à receivoir', 'Contrat reçu'];

  constructor() {
  }

  ngOnInit() {

  }

  showPayment() {
    this.isPayment = true;
    this.isSigne = false;
    this.isReceive = false;
  }

  showSign() {
    this.isSigne = true;
    this.isPayment = false;
    this.isReceive = false;
  }

  showReceive() {
    this.isReceive = true;
    this.isSigne = false;
    this.isPayment = false;
  }

  getRoleById(id) {
    return this.roles.find(next => next.id === id);
  }

}
