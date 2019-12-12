import { Component, Input, OnInit } from '@angular/core';
import { Roles } from '../../../model/roles';
import { SubscriptionService } from 'src/app/service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-contract',
  templateUrl: './contract.component.html',
  styleUrls: ['./contract.component.css']
})
export class ContractComponent implements OnInit {
  contracts = [];
  contractsGiver = [];
  contractsReceiver = [];
  myRoleId;
  isSigne = true;
  isPayment = false;
  isReceive = false;
  roles = Roles;
  amount = 100;
  tabs = ['Total à payer', 'État du paiement'];
  tab = ['Total à recevoir', 'État du paiement'];

  constructor(private subscription: SubscriptionService) {

  }

  ngOnInit() {
    this.myRoleId = this.subscription.myRole.id;
    this.subscription.currentActivity$.subscribe(data => {
      this.contracts = [];
      this.contractsGiver = [];
      this.contractsReceiver = [];
      console.log(data);
      data.contractsGiver.forEach(contract => {

        let contractObject = {
          withRole: contract.receiverID,
          isPay: true,
          amount: contract.amount,
          amountPaid: contract.amountPaid
        }
        this.contracts.push(contractObject)
        this.contractsGiver.push(contractObject)
      });

      data.contractsReceiver.forEach(contract => {

        let contractObject = {
          withRole: contract.giverID,
          isPay: false,
          amount: contract.amount,
          amountPaid: contract.amountPaid

        }
        this.contracts.push(contractObject)
        this.contractsReceiver.push(contractObject)
      });
    })
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
