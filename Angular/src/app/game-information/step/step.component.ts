import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {Subscription} from 'rxjs';
import {ActionSet} from '../../model/action';

@Component({
  selector: 'app-step',
  templateUrl: './step.component.html',
  styleUrls: ['./step.component.css']
})
export class StepComponent implements OnInit, OnDestroy {
  @Input() step: any = null;
  @Output() currentStep = new EventEmitter();
  buyingActions: any[] = [];
  payingActions: any[] = [];
  dataResources: ActionSet[] = [];
  subActivities: Subscription;
  type: string;
  tmpAction: ActionSet = new ActionSet();

  constructor(private subscription: SubscriptionService) {
  }

  ngOnInit() {
    console.log(this.step);
    for (const item of this.step.payingActions) {
      if (this.dataResources.length === 0) {
        this.tmpAction.id = item.roleID;
        switch (item.payType) {
          case 'RISK':
            this.tmpAction.riskActions = item.actions;
            break;
          case 'MANDATORY':
            this.tmpAction.basicActions = item.actions;
            break;
          case 'DAYS':
            this.tmpAction.durationActions = item.actions;
            break;
        }
        this.dataResources.push(this.tmpAction);
      } else {
        const alreayExiste = this.dataResources.filter(next => next.id === item.roleID)[0];
        switch (item.payType) {
          case 'RISK':
            alreayExiste.riskActions = item.actions;
            break;
          case 'MANDATORY':
            alreayExiste.basicActions = item.actions;
            break;
          case 'DAYS':
            alreayExiste.durationActions = item.actions;
            break;
        }
      }
    }
    console.log(this.dataResources);
    this.subscription.sendPayingActions(this.dataResources);
  }


  sendStep() {
    this.currentStep.emit(this.step.title);
  }


  counter(num) {
    return new Array(num);
  }

  ngOnDestroy(): void {
    this.subActivities.unsubscribe();
  }
}
