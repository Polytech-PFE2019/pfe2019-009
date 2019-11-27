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
  @Input() type = 'duration';
  @Input() isActivity = false;
  @Output() sendPaymentActivity = new EventEmitter();
  @Input() activitiesBenefits: Action[] = [];
  roleStyle: any;
  roles = Roles;
  value = 0;

  constructor() {
  }

  ngOnInit() {
    console.log(this.role);
    if (this.type === 'basic') {
      this.value = this.activitiesBenefits[0].amountToPay;
    }
    this.roleStyle = this.getStyleById(this.role).style;

  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

  getClickItem(item: any) {
    const i = {
      amountToPay: item.amountToPay,
      type: this.type,
      bonusAmount: item.bonusAmount
    };
    this.sendPaymentActivity.emit(i);

  }
}
