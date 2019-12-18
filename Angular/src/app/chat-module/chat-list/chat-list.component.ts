import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Roles} from '../../model/roles';
import {SubscriptionService} from "../../service/subscriptionSerivce/subscription.service";

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit {
  @Input() isChat = false;
  @Output() close = new EventEmitter();
  isDialog = false;
  roles = Roles;
  myRoles: any;
  title = '';
  data = [
    {
      id: 1,
    },
    {
      id: 2,
    },
    {
      id: 3,
    },
    {
      id: 4,
    },
    {
      id: 5,
    },
    {
      id: 6,
    },
  ];
  withRoles: any[] = [];

  constructor(private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.withRoles = [];
    this.myRoles = this.subscription.myRole;
    for (const item of this.data) {
      if (item.id !== 1) {
        this.withRoles.push(this.getRoleById(item.id));
      }
    }
  }

  closeChat() {
    this.isChat = false;
    this.close.emit(false);
  }

  getChat(t) {
    console.log('getChat');
    this.title = t;
    this.isDialog = true;
  }

  ifCloseDialog($event: any) {
    this.isDialog = $event;
  }

  getRoleById(id) {
    return this.roles.find(next => next.id === id);
  }
}
