import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  radioValue = '0';
  res_1 = 1;
  res_2 = 0;
  res_3 = 0;
  risque = 0;
  total_res = this.res_1 + this.res_2 + this.res_3;
  updateChecked(): void {
    switch (this.radioValue) {
      case '0': this.res_2 = 0; this.risque = 0; break;
      case '1': this.res_2 = 1; this.risque = 1; break;
      case '2': this.res_2 = 3; this.risque = 2; break;
    }
    this.total_res = this.res_1 + this.res_2 + this.res_3;
  }
  payResource(): void {
    this.valueChange.emit(this.total_res);
  }
  constructor() { }

  ngOnInit() {
  }

}

