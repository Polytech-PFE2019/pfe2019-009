import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  @Input() resourceRemain: number;
  isVisible = false;
  radioValue = 0;
  resBasic = 1;
  res2 = 0;
  res3 = 0;
  risque = 0;
  riskReduced = 0;
  totalRes = this.resBasic;
  payment = 0;
  numOfRisks = 3; // canbe input
  payActivity = [{
    payment: 0,
    benefits: 0
  },
    {
      payment: 2,
      benefits: 1
    },
    {
      payment: 4,
      benefits: 2
    },
  ];

  updateChecked(): void {
    this.totalRes = this.resBasic;
    for (const item of this.payActivity) {
      if (item.payment === this.radioValue) {
        this.totalRes = item.payment + this.resBasic;
        this.riskReduced = item.benefits;
      }
    }
  }

  popConfirm(): void {
    if (this.resourceRemain >= this.totalRes) {
      this.isVisible = true;
    } else {
      this.nzMessageService.info('Votre ressource ne suffit pas. Il faut acheter la ressource en premier.');
    }


  }

  handleOk(): void {
    this.payResource();
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Paiement annulé');
  }

  payResource(): void {
    this.valueChange.emit(this.totalRes);
    this.nzMessageService.info('Paiement réussi');

  }

  constructor(private nzMessageService: NzMessageService) {
  }

  ngOnInit() {
  }

  getPaymentActivity($event) {
    this.payment = $event;
    this.totalRes = 0;
    this.totalRes = this.payment + this.resBasic;
  }
}

