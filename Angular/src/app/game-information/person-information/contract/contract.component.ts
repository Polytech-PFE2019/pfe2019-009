import { Component, Input, OnInit } from '@angular/core';
import { Roles } from '../../../model/roles';
import { SubscriptionService } from 'src/app/service/subscriptionSerivce/subscription.service';
import { GameOnService } from 'src/app/service/gameOnService/game-on.service';

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

  constructor(private subscription: SubscriptionService, private gameService: GameOnService) {

  }

  ngOnInit() {
    this.myRoleId = this.subscription.myRole.id;

    this.gameService.reponses$.subscribe(data => {
      if (data.response === 'END_NEGOTIATE' || data.response === 'FAIL_NEGOTIATE') {
        let contractObject;
        if (data.giverID === this.myRoleId) {
          contractObject = {
            withRole: data.receiverID,
            isPay: true,
            amount: data.amount,
            amountPaid: 0,
            negoID: data.negociationID
          };
          this.contractsGiver.push(contractObject);

        } else {
          contractObject = {
            withRole: data.giverID,
            isPay: false,
            amount: data.amount,
            amountPaid: 0,
            negoID: data.negociationID
          };
          this.contractsReceiver.push(contractObject);

        }
        this.contracts.push(contractObject);

      } else if (data.response === 'PAY_CONTRACT') {
        console.log(data);
        console.log(this.contractsGiver);
        console.log(this.contractsReceiver);
        console.log(this.myRoleId);

        let contract = this.contractsGiver.find(contracts => contracts.negoID === data.negociationID && data.giverID === this.myRoleId);

        if (contract === undefined) {
          contract = this.contractsReceiver.find(contracts => contracts.negoID === data.negociationID && data.receiverID === this.myRoleId);
        }

        contract.amountPaid += data.amount;
      }

    });
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
