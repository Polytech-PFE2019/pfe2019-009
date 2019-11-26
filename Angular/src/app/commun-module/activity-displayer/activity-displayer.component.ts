import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Roles} from '../../model/roles';

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
  @Input() activitiesBenefits: any[] = [
    {
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
  roleStyle: any;
  roles = Roles;

  constructor() {
  }

  ngOnInit() {
    this.roleStyle = this.getStyleById(this.role).style;
  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

  getClickItem(item: any) {
    this.sendPaymentActivity.emit(item.payment);

  }
}
