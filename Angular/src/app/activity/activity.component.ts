import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  radioValue = '0';
  res1 = 1;
  res2 = 0;
  res3 = 0;
  risque = 0;
  totalRes = this.res1 + this.res2 + this.res3;

  updateChecked(): void {
    switch (this.radioValue) {
      case '0': this.res2 = 0; this.risque = 0; break;
      case '1': this.res2 = 1; this.risque = 1; break;
      case '2': this.res2 = 3; this.risque = 2; break;
    }
    this.totalRes = this.res1 + this.res2 + this.res3;
  }

  payResource(): void {
    this.valueChange.emit(this.totalRes);
  }
  constructor() { }

  ngOnInit() {
  }

}

