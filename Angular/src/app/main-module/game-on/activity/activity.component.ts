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
  numOfRisks = 3; // canbe input
  payActivity = [{
    id: 0,
    monney: 0,
    risk: 0,
  },
    {
      id: 1,
      monney: 2,
      risk: 1
    },
    {
      id: 2,
      monney: 4,
      risk: 2
    }
  ];

  updateChecked(): void {
    this.totalRes = this.resBasic;
    for (const item of this.payActivity) {
      if (item.id === this.radioValue) {
        this.totalRes = item.monney + this.resBasic;
        this.riskReduced = item.risk;
      }
    }
  }

  popConfirm(): void {
    if(this.resourceRemain>=this.totalRes){
      this.isVisible = true;
    }else{
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

}

