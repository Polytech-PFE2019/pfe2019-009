import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {
  @Output() valueChange = new EventEmitter();
  isVisible = false;
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

  popConfirm():void {
    this.isVisible = true;
    this.nzMessageService.info('Paiement réussi');

  }

  handleOk(): void {
    this.payResource();
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Paiement annulé');
  }

  constructor(private nzMessageService: NzMessageService) {}

  ngOnInit() {
  }

}

