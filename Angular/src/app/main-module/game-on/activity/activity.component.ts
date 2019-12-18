import {
  ChangeDetectorRef,
  Component, EventEmitter,
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
  @Input() tabs: any[] = [];
  @Output() sendInitDialog = new EventEmitter();
  @Output() sendBuy = new EventEmitter();
  isVisible = false;
  resBasic = 1;
  riskReduced = 0;
  totalRes = this.resBasic;
  payForRisk = 0;
  payForDays = 0;
  test = [1];
  subCurrentResource: Subscription;
  currentResource: number;
  daysReduced = 0;
  gameID: any;
  request = {
    RISKS: null,
    DAYS: null,
    MANDATORY: null
  };
  userID: any;
  currentActivity: any;
  userName: any;
  myInformation: any;
  roles: any[];
  subCurrentActivity: Subscription;
  myDataSource: any[] = [];
  hasNegotiation = false;
  negotiationIDs: string[] = [];
  isFinishedMine = false;
  selectedIndex = 0;
  isBuyed = false;


  constructor(private nzMessageService: NzMessageService,
              private subscription: SubscriptionService,
              private gameService: GameOnService,
              private lobbyService: LobbyService,
              private changeDetector: ChangeDetectorRef,
              private resourceService: BuyResourceService) {
  }

  ngOnInit() {
    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      console.log(data);

      this.currentActivity = data;
      this.myDataSource = [];
      this.isFinishedMine = false;
      console.log(this.currentActivity);

      this.negotiationIDs = [];
      this.sendInitDialog.emit(true);
      // this.currentActivity.negotiationActions.forEach(nego => {
      //   if (nego.giverID === this.myInformation.id) {
      //     this.hasNegotiation = true;
      //     this.negotiationIDs.push(nego.negotiationID);
      //   }
      // });

      if (this.currentActivity.rolesID.includes(this.myInformation.id)) {
        const tmp = (this.currentActivity.payingActions.filter(next =>
          next.roleID === this.myInformation.id)[0]);
        this.myDataSource.push(tmp);
        console.log(this.myDataSource);
        this.selectedIndex = 0;
      }

    });

    this.subCurrentResource = this.resourceService.currentResource$.subscribe(data => {
      this.currentResource = data;
    });

    this.userName = this.lobbyService.username;
    this.roles = this.subscription.roles;
    this.myInformation = this.roles.filter(next => next.username === this.userName)[0];
    console.log(this.myInformation);
    this.gameID = this.subscription.gameID;
    console.log('game id+++' + this.gameID);
    this.userID = this.subscription.userId;
    console.log(this.activities);

    this.gameService.messages.subscribe(data => {
      switch (data.response) {
        case 'START_NEGOTIATE':
          // POP UP THE Negociation component
          // with negotiation id in it
          // data.negotiationID
          break;
      }
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
    this.sendBuy.emit(true);
    // this.payResource();
    this.isFinishedMine = true;
    this.resourceService.sendResourcesReduced(this.totalRes);
    this.resourceService.sendReducedRisk(this.riskReduced);
    this.resourceService.sendDaysReduced(this.daysReduced);
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
    this.gameService.messages.next(req as SocketRequest);
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
    this.subCurrentActivity.unsubscribe();
  }

  launchNegotiation() {
    console.log(this.negotiationIDs);
    this.negotiationIDs.forEach(negoID => {
      const request = {
        request: 'START_NEGOTIATE',
        negotiationID: negoID,
        gameID: this.gameID
      } as SocketRequest;

      this.gameService.messages.next(request);
    });

  }

  getBuy($event) {
    this.isBuyed = $event;
    this.selectedIndex = 1;
  }

  printIndex() {
    console.log(this.selectedIndex);
  }
}

