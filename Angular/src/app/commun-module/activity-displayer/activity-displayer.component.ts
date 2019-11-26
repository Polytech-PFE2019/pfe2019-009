import {Component, Input, OnInit} from '@angular/core';
import {Roles} from '../../model/roles';

@Component({
  selector: 'app-activity-displayer',
  templateUrl: './activity-displayer.component.html',
  styleUrls: ['./activity-displayer.component.css']
})
export class ActivityDisplayerComponent implements OnInit {
  @Input() role: number;
  @Input() type = 'duration';
  @Input() activitiesDuration = [
    {
      payment: 0,
      duration: 0
    },
    {
      payment: 2,
      duration: 1
    },
    {
      payment: 4,
      duration: 2
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

}
