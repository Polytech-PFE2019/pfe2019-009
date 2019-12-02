import {
  ChangeDetectorRef,
  Component,
  Input,
  OnDestroy,
  OnInit,
  Output
} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {BuyResourceService} from '../../../service/resources/buy-resource.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../../service/subscriptionSerivce/subscription.service';
import {GameOnService} from '../../../service/gameOnService/game-on.service';
import {SocketRequest} from '../../../../Request';
import {LobbyService} from '../../../service/lobbyService/lobby.service';

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
  test = [1];
  subCurrentResource: Subscription;
  currentResource: number;
  subPayingActions: Subscription;
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
  currentActivity: any;
  userName: any;
  myInformation: any;
  roles: any[];
  subCurrentActivity: Subscription;
  myDataSource: any[] = [];

  constructor(private nzMessageService: NzMessageService,
              private subscription: SubscriptionService,
              private gameSerivce: GameOnService,
              private lobbyService: LobbyService,
              private changeDetector: ChangeDetectorRef,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentResource = this.resourceService.currentMonney$.subscribe(data => {
      this.currentResource = data;
    });

    this.userName = this.lobbyService.username;
    this.roles = this.subscription.roles;
    this.myInformation = this.roles.filter(next => next.username === this.userName)[0];
    console.log(this.myInformation);
    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      this.currentActivity = data;
      this.myDataSource = [];
      console.log(this.currentActivity);
      if (this.currentActivity.rolesID.includes(this.myInformation.id)) {
        const tmp = (this.currentActivity.payingActions.filter(next =>
          next.roleID === this.myInformation.id)[0]);
        this.myDataSource.push(tmp);
        console.log(this.myDataSource);
      }
    });
    this.gameID = this.subscription.gameID;
    console.log('game id+++' + this.gameID);
    this.userID = this.subscription.userId;
    console.log(this.activities);

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
    if (this.request.RISKS !== null) {
      payment.push(this.request.RISKS);
    }
    if (this.request.MANDATORY !== null) {
      payment.push(this.request.MANDATORY);
    }
    if (this.request.DAYS !== null) {
      payment.push(this.request.DAYS);
    }
    const req = {
      request: 'PAY_RESOURCES',
      gameID: this.gameID,
      userID: this.userID,
      payments: payment
    };
    console.log(req);
    this.gameSerivce.messages.next(req as SocketRequest);
    this.request = {
      RISKS: null,
      DAYS: null,
      MANDATORY: null
    };
    this.totalRes = 0;
    this.payForDays = 0;
    this.payForRisk = 0;
    this.daysReduced = 0;
    this.riskReduced = 0;
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

  ngOnDestroy(): void {
    this.subCurrentResource.unsubscribe();
    this.subPayingActions.unsubscribe();
    this.subGameId.unsubscribe();
    this.subUserId.unsubscribe();
    this.subCurrentActivity.unsubscribe();
  }

}

