import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SubscriptionService} from "../service/subscriptionSerivce/subscription.service";

@Component({
  selector: 'app-list-players',
  templateUrl: './list-players.component.html',
  styleUrls: ['./list-players.component.css']
})
export class ListPlayersComponent implements OnInit {
  @Output() close = new EventEmitter();
  roles: any[] = [];

  constructor(private subscription: SubscriptionService) { }

  ngOnInit() {
    this.roles = this.subscription.roles;
  }

  closeList() {
    this.close.emit(false);
  }
}
