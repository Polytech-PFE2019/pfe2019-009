import { Component, ElementRef, OnDestroy, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { LobbyService } from '../../service/lobbyService/lobby.service';
import { SocketRequest } from '../../../Request';
import { Router } from '@angular/router';
import { GameOnService } from '../../service/gameOnService/game-on.service';
import { Subscription } from 'rxjs';
import { SubscriptionService } from '../../service/subscriptionSerivce/subscription.service';
import { BuyResourceService } from '../../service/resources/buy-resource.service';
import { Activity } from '../../model/activity';
import { PlayerdataService } from 'src/app/playerdata.service';
import { NzNotificationService } from 'ng-zorro-antd';

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit, OnDestroy {
  @ViewChild('stepContainers', { static: true }) stepContainer: ElementRef;
  @ViewChild('template', { static: true }) template: TemplateRef<{}>;
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
  currentPlayer: any = null;

  totalScrollHeight = 0;

  constructor(private lobbyService: LobbyService,
    private gameService: GameOnService,
    private subscription: SubscriptionService,
    private resourceManager: BuyResourceService,
    private router: Router,
    private notification: NzNotificationService,
    private playerDataService: PlayerdataService) {
  }

  ngOnInit() {
    console.log(22222222222222);
    this.gameId = this.subscription.gameID;


    // this.subPlayersWithRoles = this.subscription.playersWithRoles$.subscribe(data => {
    //   console.log(data);
    //   this.roles = data;
    // });
    this.roles = this.subscription.roles;

    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      this.currentActivity = data;
      const previousActivityID = parseInt(this.currentActivity.title) - 1;
      let previousActivityElement = document.getElementById(previousActivityID.toString());
      if (previousActivityElement != null) {
        this.totalScrollHeight += previousActivityElement.offsetHeight;
        document.getElementById("stepsContainer").scrollTop = this.totalScrollHeight;

      }
      console.log(this.currentActivity);
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
        this.listOfDialog.push(data);
        console.log(this.listOfDialog);
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

      if (data.response === 'UPDATE_PAYMENT') {
        console.log(data);
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

  initDialog($event: any) {
    this.listOfDialog = [];
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
}
