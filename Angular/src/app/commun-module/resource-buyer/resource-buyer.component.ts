import {Component, Input, OnInit} from '@angular/core';
import {Roles} from '../../model/roles';

@Component({
  selector: 'app-resource-buyer',
  templateUrl: './resource-buyer.component.html',
  styleUrls: ['./resource-buyer.component.css']
})
export class ResourceBuyerComponent implements OnInit {

  @Input() buyer: number;
  roles = Roles;
  buyerStyle: any;
  moneyStyle = {
    backgroundColor: 'white'
  };

  constructor() {
  }

  ngOnInit() {
    this.buyerStyle = this.getStyleById(this.buyer).style;
    this.moneyStyle.backgroundColor = this.buyerStyle.background;
  }

  getStyleById(userId) {
    return this.roles.filter(next => (next.id === userId))[0];
  }


}
