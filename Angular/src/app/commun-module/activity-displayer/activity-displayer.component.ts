import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Roles} from '../../model/roles';
import {Action} from '../../model/action';

@Component({
  selector: 'app-activity-displayer',
  templateUrl: './activity-displayer.component.html',
  styleUrls: ['./activity-displayer.component.css']
})
export class ActivityDisplayerComponent implements OnInit {
  @Input() role: number;
  @Input() type = 'DAYS';
  @Input() isActivity = false;
  @Output() sendPaymentActivity = new EventEmitter();
  @Input() activitiesBenefits: any[] = [];
  @Input() extraPaying: any[] = [];
  @Input() isInter = false;
  roleStyle: any;
  roles = Roles;
  value = 0;
  previous = null;
  total = 0;

  constructor() {
  }

  ngOnInit() {
    console.log(this.role);
    if (this.type === 'MANDATORY') {
      this.value = this.activitiesBenefits[0].amountToPay;
      console.log(this.activitiesBenefits);
      const i = {
        info: {
          amount: this.activitiesBenefits[0].amountToPay,
          type: this.type,
        },
        type: this.type,
        bonusAmount: 0
      };
      console.log(i);
      this.sendPaymentActivity.emit(i);
    }
    this.roleStyle = this.getStyleById(this.role).style;

  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

  getClickItem(item: any) {
    this.getChangeValue(item);
    if (this.value !== null) {
      const i = {
        info: {
          amount: item.amountToPay,
          type: this.type,
        },
        type: this.type,
        bonusAmount: item.bonusAmount
      };
      this.sendPaymentActivity.emit(i);
    } else {
      const i = {
        info: {
          amount: 0,
          type: this.type,
        },
        type: this.type,
        bonusAmount: 0
      };
      this.sendPaymentActivity.emit(i);
    }

  }

  getChangeValue(item) {
    if (item !== this.previous) {
      this.previous = item;
      console.log(this.previous, this.value);
    } else {
      this.value = null;
      this.previous = null;
      console.log('11111111', this.previous, this.value);
    }
  }
}
