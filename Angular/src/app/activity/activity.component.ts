import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  radioValue = 0;
  resBasic = 1;
  res2 = 0;
  res3 = 0;
  risque = 0;
  riskReduced = 0;
  totalRes = this.resBasic;
  numOfRisks = 3; // canbe input
  payActivity = [{
    monney: 0,
    risk: 0,
  },
    {
      monney: 2,
      risk: 1
    },
    {
      monney: 4,
      risk: 2
    }
  ];

  updateChecked(): void {
    this.totalRes = this.resBasic;
    for (const item of this.payActivity) {
      if (item.monney === this.radioValue) {
        this.totalRes = item.monney + this.resBasic;
        this.riskReduced = item.risk;
      }
    }
  }

  payResource(): void {
    this.valueChange.emit(this.totalRes);
  }

  constructor() {
  }

  ngOnInit() {
  }

}

