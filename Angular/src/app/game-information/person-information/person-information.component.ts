import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Subscription} from 'rxjs';
import {BuyResourceService} from '../../service/resources/buy-resource.service';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {NzConfigService, NzMessageService, NzNotificationService} from 'ng-zorro-antd';
import {SocketRequest} from '../../../Request';

@Component({
  selector: 'app-person-information',
  templateUrl: './person-information.component.html',
  styleUrls: ['./person-information.component.css']
})
export class PersonInformationComponent implements OnInit, OnDestroy {
  @ViewChild('template', {static: true}) template: TemplateRef<{}>;
  playersWithRoles: any[] = [];
  subResourcesBuyed: Subscription;
  subPayment: Subscription;
  currentMonney: any;
  subReduced: Subscription;
  currentResource = 0;
  subPlayersWithRoles: Subscription;
  userName: string;
  myInformation: any;
  isPointsVistoire = false;
  isSpecial = false;
  isDescription = true;
  isPlan = false;
  subCost: Subscription;
  subDays: Subscription;
  subRisk: Subscription;
  subGame: Subscription;
  cost: any;
  day: any;
  risk: any;
  giverRoleName = '';
  receiverRoleName = '';
  paymentAmount = 0;
  gameID: any;
  userID: any;

  constructor(private resourceService: BuyResourceService,
              private lobbyService: LobbyService,
              private gameService: GameOnService,
              private nzMessageService: NzMessageService,
              private subscription: SubscriptionService,
              private nzConfigService: NzConfigService,
              private notificationService: NzNotificationService) {
  }


  ngOnInit() {
    this.userName = this.lobbyService.username;
    this.playersWithRoles = this.subscription.roles;
    this.gameID = this.subscription.gameID;
    this.userID = this.subscription.userId;

    console.log(this.userName);
    console.log(this.playersWithRoles);
    this.myInformation = this.playersWithRoles.filter(next => next.username === this.userName)[0];
    console.log(this.myInformation);
    this.currentMonney = this.myInformation.money;

    this.subResourcesBuyed = this.resourceService.resources$.subscribe(data => {
      console.log('resourceBuyed' + data);
      this.currentResource = data + this.currentResource;
      this.resourceService.sendCurrentResource(this.currentResource);
    });

    this.subPayment = this.resourceService.payment$.subscribe(data => {
      this.currentMonney = this.currentMonney - data;
      this.resourceService.sendCurrentMonney(this.currentMonney);
    });

    this.subReduced = this.resourceService.reduce$.subscribe(data => {
      this.currentResource = this.currentResource - data;
      this.resourceService.sendCurrentResource(this.currentResource);
    });

    this.subPlayersWithRoles = this.subscription.playersWithRoles$.subscribe(data => {
      console.log(data);
      // this.playersWithRoles = data;
    });

    this.subRisk = this.subscription.failures$.subscribe(data => {
      this.risk = data;
    });

    this.subDays = this.subscription.days$.subscribe(data => {
      this.day = data;
    });

    this.subCost = this.subscription.costs$.subscribe(data => {
      this.cost = data;
    });

    console.log(this.lobbyService.username);

    this.subGame = this.gameService.reponses$.subscribe(data => {
      if (data.response === 'drawRisk') {
        this.currentMonney = data.player.money;
        this.currentResource = data.player.resources;
      }
      if (data.response === 'PAY_CONTRACT') {
        this.currentMonney = data.money;
        this.notificationService.template(this.template);
      }
      if (data.response === 'BANKRUPTCY') {
        this.resourceService.sendCurrentMonney(data.money);
        this.currentMonney = data.money;
      }
    });
  }

  ngOnDestroy(): void {
    this.subResourcesBuyed.unsubscribe();
    this.subPayment.unsubscribe();
    this.subReduced.unsubscribe();
    this.subPlayersWithRoles.unsubscribe();
    this.subCost.unsubscribe();
    this.subDays.unsubscribe();
    this.subRisk.unsubscribe();
    this.subGame.unsubscribe();
  }

  showPointsVistoire() {
    this.isPointsVistoire = true;
    this.isSpecial = false;
    this.isDescription = false;
    this.isPlan = false;
  }

  showSpecial() {
    this.isSpecial = true;
    this.isPointsVistoire = false;
    this.isDescription = false;
    this.isPlan = false;
  }

  showDescription() {
    this.isDescription = true;
    this.isPointsVistoire = false;
    this.isSpecial = false;
    this.isPlan = false;
  }

  showPlan() {
    this.isPlan = true;
    this.isDescription = false;
    this.isPointsVistoire = false;
    this.isSpecial = false;
  }

  cancel(): void {
    this.nzMessageService.info('Annuler!');
  }

  confirm(): void {
    const req = {
      request: 'BANKRUPTCY',
      gameID: this.gameID,
      userID: this.userID
    };
    console.log(req);
    this.gameService.messages.next(req as SocketRequest);
    this.nzMessageService.info('Déclaration de la faillte');
  }

}
