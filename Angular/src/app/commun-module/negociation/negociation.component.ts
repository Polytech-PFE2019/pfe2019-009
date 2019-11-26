import {Component, Input, OnInit, Output} from '@angular/core';
import {Roles} from "../../model/roles";

@Component({
  selector: 'app-negociation',
  templateUrl: './negociation.component.html',
  styleUrls: ['./negociation.component.css']
})
export class NegociationComponent implements OnInit {
  @Input() fromUser: number;
  @Input() toUser: number;

  roles = Roles;
  fromUserStyle: any;
  toUserStyle: any;

  constructor() {
  }

  ngOnInit() {
    this.fromUserStyle = this.getStyleById(this.fromUser).style;
    this.toUserStyle = this.getStyleById(this.toUser).style;
  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }

}
