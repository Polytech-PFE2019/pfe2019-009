import {Component, ElementRef, OnDestroy, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {Router} from '@angular/router';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {BuyResourceService} from '../../service/resources/buy-resource.service';
import {Activity} from '../../model/activity';
import {PlayerdataService} from 'src/app/playerdata.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {swing} from 'ng-animate';
import {transition, trigger, useAnimation} from '@angular/animations';
import {ChatGroupComponent} from '../../chat-module/chat-group/chat-group.component';


@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css'],
  animations: [
    trigger('swing', [transition('* => *', useAnimation(swing))])
  ]
})

export class GameOnComponent implements OnInit, OnDestroy {
  @ViewChild('stepContainers', {static: true}) stepContainer: ElementRef;
  @ViewChild('template', {static: true}) template: TemplateRef<{}>;
  @ViewChild('groupChat', {static: true}) groupChat: ChatGroupComponent;
  step = 'Étape 1';
  gameId: string;
  buyingActions: any;
  currentStep: Activity[] = [];
  test: Activity;
  testClick = false;
  subPayingActions: Subscription;
  activities: any = null;
  roles: any[] = [];
  currentActivity: any;
  subCurrentActivity: Subscription;
  listOfDialog: any[] = [];
  subGame: Subscription;
  isRiskCard = false;
  riskCard: any[] = [];
  riskOfActivityId = 0;
  roleId: any;
  subSteps: Subscription;
  isPlayers = false;
  riskReduced = 0;
  daysReduced = 0;
  totalAmount = 0;
  hasBadge = false;
  currentPlayer: any = null;
  isGroupChat = false;
  myRole: any = null;
  swing = false;
  negotiationIDs: any[] = [];
  hasNegotiation = false;
  isDiabled = false;
  isShow = false;
  isLoading = false;
  showGroupChat = false;
  totalScrollHeight = 0;
  isMinused = false;
  isTest = false;
  nego = 0;
  headerStyle = {
    height: '50vh'
  };

  myStyle = {
    height: '50vh'
  };

  isTutorial = false;
  tabs = [
    {
      id: 0,
      name: 'Descriptif de l\'étape',
      icon: 'book',
    },
    {
      id: 1,
      name: 'Acheter des ressources',
      icon: 'shopping-cart',
    },
    {
      id: 2,
      name: 'Alouer des ressources',
      icon: 'pay-circle'
    },
  ];

  establish = {
    visible: false,
    name: '',
    negoID: ''
  };

  index = 0;
  disable = false;

  constructor(private lobbyService: LobbyService,
              private gameService: GameOnService,
              private subscription: SubscriptionService,
              private resourceManager: BuyResourceService,
              private router: Router,
              private notification: NzNotificationService,
              private playerDataService: PlayerdataService) {
  }

  ngOnInit() {
    setTimeout(() => {
      this.isLoading = true;
      this.isTutorial = true;
    }, 4000);
    console.log(22222222222222);
    this.gameId = this.subscription.gameID;

    this.myRole = this.subscription.myRole;
    // this.subPlayersWithRoles = this.subscription.playersWithRoles$.subscribe(data => {
    //   console.log(data);
    //   this.roles = data;
    // });
    this.roles = this.subscription.roles;

    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      this.currentActivity = data;
      const previousActivityID = parseInt(this.currentActivity.title, 10) - 1;
      const previousActivityElement = document.getElementById(previousActivityID.toString());
      if (previousActivityElement != null) {
        this.totalScrollHeight += previousActivityElement.offsetHeight;
        document.getElementById('stepsContainer').scrollTop = this.totalScrollHeight;
      }
      console.log(this.currentActivity);
      this.negotiationIDs = [];
      this.initDialog();
      this.currentActivity.negotiationActions.forEach(nego => {
        if (nego.giverID === this.myRole.id) {
          this.negotiationIDs.push(nego.negotiationID);
        }
      });
      if (this.negotiationIDs.length > 0) {
        this.isShow = true;
        setInterval(() => {
          this.swingAnimation();
        }, 1000);
      }
    });

    this.subSteps = this.subscription.activites$.subscribe(data => {
      this.currentStep = data;

      console.log(data);
    });

    console.log(this.currentStep);

    this.subPayingActions = this.subscription.payingActions$.subscribe(data => {
      console.log(data);
      this.activities = data;
      console.log(this.activities.actions);
    });

    this.subGame = this.gameService.reponses$.subscribe(data => {
      console.log(data);
      if (data.response === 'START_NEGOTIATE') {
        data.started = false;
        this.establish.name = data.otherUserName;
        this.establish.negoID = data.negociationID;
        this.listOfDialog.push(data);
        console.log(this.listOfDialog);

        if (data.receiverID === this.myRole.id) {
          this.establish.visible = true;
        }

      } else if (data.response === 'MSG_GROUP_CHAT') {
        if (!this.showGroupChat) {
          this.hasBadge = true;
        }

      }


      if (data.response === 'ESTABLISH_NEGOTIATE') {
        this.listOfDialog.find(dialog => data.negociationID === dialog.negociationID).started = true;
      }
      if (data.response === 'drawRisk') {
        if (data.risks.length > 0) {
          this.isRiskCard = true;
          this.riskCard = data.risks;
          this.riskOfActivityId = data.riskOfActivityId;
        }
      }
      if (data.response === 'FINISH') {
        this.router.navigate(['result']);
      }

      if (data.response === 'CHANGE_ACTIVITY') {
        // this.notification.create(
        //   'info',
        //   'Fin de l\'étape ' + (data.activityID - 1),
        //   'L\'étape ' + data.activityID + ' commence',
        //   {nzDuration: 8000}
        // );
      }

      if (data.response === 'UPDATE_PAYMENT') {
        console.log(data);
        this.totalAmount = 0;
        this.daysReduced = 0;
        this.riskReduced = 0;
        if (data.payments.length > 0) {
          this.currentPlayer = this.getRoleById(data.payments[0].roleID);
        }
        for (const p of data.payments) {
          switch (p.type) {
            case 'DAYS':
              this.totalAmount += p.amount;
              this.daysReduced += p.bonus;
              break;
            case 'RISKS':
              this.totalAmount += p.amount;
              this.riskReduced += p.bonus;
              break;
          }
        }
        this.notification.template(this.template);
      }
    });

  }

  openGroupChat() {
    this.showGroupChat = !this.showGroupChat;
    this.hasBadge = false;

  }

  getCurrentStep($event: any) {
    this.step = $event;
  }

  closeGame() {
    console.log('Game over');
    const message = {
      request: 'LEAVE_GAME',
      roomID: this.gameService.roomId,
      userID: this.subscription.userId
    };
    this.lobbyService.messages.next(message as SocketRequest);
    this.router.navigate(['']);
  }

  getClickTest($event: any) {
    this.testClick = $event;
  }

  getStepTest($event: any) {
    this.activities = $event;
    console.log(this.activities);
    console.log(this.activities[0].actions[0].actions);
  }

  ngOnDestroy(): void {
    this.subPayingActions.unsubscribe();
    this.subCurrentActivity.unsubscribe();
    this.subGame.unsubscribe();
    this.subSteps.unsubscribe();
  }

  initDialog() {
    this.listOfDialog = [];
    this.isShow = false;
  }

  closeRiskCard() {
    this.isRiskCard = false;
  }

  getRoleById(id) {
    return this.roles.filter(next => next.id === id)[0];
  }

  showPlayers() {
    this.isPlayers = true;
  }

  closePlayers() {
    this.isPlayers = false;
  }

  swingAnimation() {
    this.swing = !this.swing;
  }

  launchNegotiation() {
    this.isDiabled = true;
    this.isShow = false;
    console.log(this.negotiationIDs);
    this.negotiationIDs.forEach(negoID => {
      const request = {
        request: 'START_NEGOTIATE',
        negotiationID: negoID,
        gameID: this.gameId
      } as SocketRequest;

      this.gameService.messages.next(request);
    });
    // this.showGroupChat = true;
    // this.groupChat.openGroupChat();

  }

  establishNegotiation(negoID) {
    this.establish.visible = false;
    const request = {
      request: 'ESTABLISH_NEGOTIATE',
      negotiationID: negoID,
      gameID: this.gameId
    } as SocketRequest;

    this.gameService.messages.next(request);
    // this.showGroupChat = true;
    // this.groupChat.openGroupChat();

  }

  closeGroupChat(): void {
    this.showGroupChat = false;
    // this.isDiabled = false;
    // this.isShow = true;
  }

  getBuy($event: any) {
    // this.tabs = [
    //   {
    //     id: 0,
    //     name: 'Payer des resources',
    //     icon: 'pay-circle'
    //   },
    //   {
    //     id: 1,
    //     name: 'Acheter des resources',
    //     icon: 'shopping-cart',
    //   },
    // ];
  }

  minusDialogue($event: any) {
    if ($event) {
      this.headerStyle.height = '6vh';
      console.log(this.headerStyle);
      this.isMinused = true;
    }
  }

  openMinusedDialogue($event) {
    if ($event && this.isMinused) {
      this.headerStyle.height = '50vh';
      this.isMinused = false;
      console.log(this.headerStyle);
    }
  }

  getOpenDialoue($event: any) {
    if ($event && this.isTest) {
      this.myStyle.height = '50vh';
      this.isTest = false;
    }
  }

  closeChatDialogue($event: any) {
    if ($event) {
      this.headerStyle.height = '6vh';
      console.log(this.headerStyle);
      this.isTest = true;
    }
  }

  closeTutorial() {
    this.isTutorial = false;
  }

  onIndexChange(index: number): void {
    this.index = index;
  }

  onNegoChange(nego: number): void {
    this.nego = nego;
  }

  openTotu() {
    this.isTutorial = true;
  }
}
