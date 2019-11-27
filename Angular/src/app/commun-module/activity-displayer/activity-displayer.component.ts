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
  @Input() activitiesBenefits: Action[] = [
    {
      amountToPay: 0,
      bonusAmount: 0
    },
    {
      amountToPay: 2,
      bonusAmount: 1
    },
    {
      amountToPay: 4,
      bonusAmount: 2
    },
  ];
  roleStyle: any;
  roles = Roles;

  constructor() {
  }

  ngOnInit() {
    console.log(this.role);
    this.roleStyle = this.getStyleById(this.role).style;
  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

  getClickItem(item: any) {
    this.sendPaymentActivity.emit(item.amountToPay);

  }
}
