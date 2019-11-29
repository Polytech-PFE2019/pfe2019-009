import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Steps} from '../../model/step';
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
  @Output() sendTestClick = new EventEmitter();
  @Output() sendStepTest = new EventEmitter();
  buyingActions: any[] = [];
  payingActions: any[] = [];
  dataResources: ActionSet[] = [];
  subActivities: Subscription;
  test = [1];
  type: string;
  tmpAction: ActionSet = new ActionSet();

  constructor(private subscription: SubscriptionService) {
  }

  ngOnInit() {
    console.log(this.step);
  }


  sendStep() {
   // this.currentStep.emit(this.step.title);
    this.sendTestClick.emit(true);
    const test = [{
      roleID: 1,
      actions: [
        {
          roleID: 1,
          payType: 'MANDATORY',
          status: false,
          amountPaid: 0,
          bonusGiven: 0,
          actions: [{amountToPay: 1, bonusAmount: 1}]
        },
        {
          roleID: 1,
          payType: 'RISKS',
          status: false,
          amountPaid: 0,
          bonusGiven: 0,
          actions: [{amountToPay: 1, bonusAmount: 1}, {amountToPay: 4, bonusAmount: 2}]
        },
        {
          roleID: 1,
          payType: 'DAYS',
          status: false,
          amountPaid: 0,
          bonusGiven: 0,
          actions: [{amountToPay: 1, bonusAmount: 2}]
        },
      ]
    }
    ];
    // this.subscription.sendPayingActions(test);
    // this.sendStepTest.emit(test);
    this.sendStepTest.emit(this.step.payingActions);
    // this.subscription.sendPayingActions(this.step);
  }


  counter(num) {
    return new Array(num);
  }

  ngOnDestroy(): void {
    this.subActivities.unsubscribe();
  }
}
