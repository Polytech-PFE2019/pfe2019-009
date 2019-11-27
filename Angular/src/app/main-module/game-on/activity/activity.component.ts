import {AfterViewInit, Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';
import {ActionSet} from '../../../model/action';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit, OnDestroy {
  isVisible = false;
  resBasic = 1;
  riskReduced = 0;
  totalRes = this.resBasic;
  payment = 0;
  payActivity = [{
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
  subCurrentResource: Subscription;
  currentResource: number;
  subPayingActions: Subscription;
  dataResources: ActionSet = new ActionSet();

  constructor(private nzMessageService: NzMessageService,
              private subscription: SubscriptionService,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentResource = this.resourceService.currentMonney$.subscribe(data => {
      this.currentResource = data;
    });

    this.subPayingActions = this.subscription.payingActions$.subscribe(data => {
      console.log(data);
      this.dataResources = data;
      console.log(this.dataResources);
    });

  }

  popConfirm(): void {
    if (this.currentResource >= this.totalRes) {
      this.isVisible = true;
    } else {
      this.nzMessageService.info('Votre ressource ne suffit pas. Il faut acheter la ressource en premier.');
    }
  }

  handleOk(): void {
    // this.payResource();
    this.resourceService.sendResourcesReduced(this.totalRes);
    this.isVisible = false;
    this.nzMessageService.info('Paiement réussi');
    const req = {
      request: 'PAY_RESOURCES',
      // activityID:
    };
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Paiement annulé');
  }


  getPaymentActivity($event) {
    this.payment = $event;
    this.riskReduced = this.getItemByOayment($event).benefits;
    this.totalRes = 0;
    this.totalRes = this.payment + this.resBasic;
  }

  getItemByOayment(payment) {
    return this.payActivity.filter(next => next.payment === payment)[0];
  }

  ngOnDestroy(): void {
    this.subCurrentResource.unsubscribe();
    this.subPayingActions.unsubscribe();
  }

  // ngAfterViewInit(): void {
  //   this.subPayingActions = this.subscription.payingActions$.subscribe(data => {
  //     console.log(data);
  //     this.dataResources = data;
  //     console.log(this.dataResources);
  //   });
  // }
}

