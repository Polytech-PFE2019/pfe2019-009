import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-prompt',
  templateUrl: './prompt.component.html',
  styleUrls: ['./prompt.component.css']
})
export class PromptComponent implements OnInit, OnDestroy {
  subCurrentActivity: Subscription;
  currentActivity: any = null;
  myRole: any = null;
  roles: any[] = [];

  constructor(private subscription: SubscriptionService) {
  }

  ngOnInit() {
    this.myRole = this.subscription.myRole;
    this.roles = this.subscription.roles;
    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      console.log(data);
      this.currentActivity = data;
    });

  }

  ngOnDestroy(): void {
    this.subCurrentActivity.unsubscribe();
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }

}
