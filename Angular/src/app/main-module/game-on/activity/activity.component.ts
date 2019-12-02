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
import {GameOnService} from '../../../service/gameOnService/game-on.service';
import {SocketRequest} from '../../../../Request';

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
  subGameId: Subscription;
  gameID: any;
  request = {
    RISKS: null,
    DAYS: null,
    MANDATORY: null
  };
  subUserId: Subscription;
  userID: any;

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
    // this.subGameId = this.subscription.gameID$.subscribe(data => {
    //   this.gameID = data;
    // });
    this.gameID = this.gameSerivce.gameID;
    console.log('game id+++' + this.gameID);
    this.subUserId = this.subscription.userID$.subscribe(data => {
      this.userID = data;
    });
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
    const payment = [];
    payment.push(this.request.RISKS);
    payment.push(this.request.MANDATORY);
    payment.push(this.request.DAYS);
    const req = {
      request: 'PAY_RESOURCES',
      gameID: this.gameID,
      userID: this.userID,
      payments: payment
    };
    this.gameSerivce.messages.next(req as SocketRequest);
    this.request = {
      RISKS: null,
      DAYS: null,
      MANDATORY: null
    };
  }

  handleCancel(): void {
    this.isVisible = false;
    this.nzMessageService.info('Paiement annulé');
  }


  getPaymentActivity($event) {
    const i = $event;
    console.log($event);
    switch (i.type) {
      case 'RISKS':
        this.request.RISKS = i.info;
        this.riskReduced = $event.bonusAmount;
        this.payForRisk = i.info.amount;
        break;
      case 'DAYS':
        this.request.DAYS = i.info;
        this.daysReduced = $event.bonusAmount;
        this.payForDays = i.info.amount;
        break;
      case 'MANDATORY':
        this.request.MANDATORY = i.info;
        this.resBasic = i.info.amount;
        break;
    }
    this.totalRes = 0;
    console.log(this.payForRisk, this.payForDays, this.resBasic);
    this.totalRes = this.payForDays + this.payForRisk + this.resBasic;
  }

  getItemByOayment(payment) {
    return this.payActivity.filter(next => next.payment === payment)[0];
  }

  ngOnDestroy(): void {
    this.subCurrentResource.unsubscribe();
    this.subPayingActions.unsubscribe();
    this.subGameId.unsubscribe();
    this.subUserId.unsubscribe();
  }

}

