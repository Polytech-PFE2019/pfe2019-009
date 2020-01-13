import { Component, Input, OnInit } from '@angular/core';
import { SubscriptionService } from 'src/app/service/subscriptionSerivce/subscription.service';

@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent implements OnInit {

  @Input() message: any = '';
  @Input() roleID;
  roleName;
  userName;
  roles;
  constructor(private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.roles = this.subscription.roles;

    if (this.roleID != null) {

      this.roleName = this.getRoleById(this.roleID).title;
    }

  }

  getRoleById(id) {
    return this.roles.find(next => next.id === id);
  }

}
