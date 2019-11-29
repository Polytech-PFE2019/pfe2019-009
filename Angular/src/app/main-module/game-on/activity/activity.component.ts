import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output
} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';
import {ActionSet} from '../../../model/action';
import {GameOnService} from "../../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit, OnDestroy {
  @Input() activities: any[] = [];
  isVisible = false;
  resBasic = 1;
  riskReduced = 0;
  totalRes = this.resBasic;
  payForRisk = 0;
  payForDays = 0;
  payment = 0;
  test = [1];
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
  dataResources: any;
  daysReduced = 0;

  constructor(private nzMessageService: NzMessageService,
              private subscription: SubscriptionService,
              private gameSerivce: GameOnService,
              private changeDetector: ChangeDetectorRef,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentResource = this.resourceService.currentMonney$.subscribe(data => {
      this.currentResource = data;
    });

    // this.activities = this.gameSerivce.currentActivity;
    // console.log(this.activities);

    // this.subPayingActions = this.subscription.payingActions$.subscribe(data => {
    //   console.log(data);
    //   this.activities = data;
    // });
    console.log(this.activities);
    console.log(this.activities[0].roleID);

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
    const i = $event;
    switch (i.type) {
      case 'risk':
        this.riskReduced = $event.bonusAmount;
        this.payForRisk = $event.amountToPay;
        break;
      case 'duration':
        this.daysReduced = $event.bonusAmount;
        this.payForDays = $event.amountToPay;
        break;
    }
    this.totalRes = 0;
    this.totalRes = this.payForDays + this.payForRisk + this.resBasic;
  }

  getItemByOayment(payment) {
    return this.payActivity.filter(next => next.payment === payment)[0];
  }

  ngOnDestroy(): void {
    this.subCurrentResource.unsubscribe();
    this.subPayingActions.unsubscribe();
  }

}

