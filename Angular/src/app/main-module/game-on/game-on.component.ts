import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Steps} from '../../model/step';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {Router} from '@angular/router';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {BuyResourceService} from '../../service/resources/buy-resource.service';
import {Activity} from '../../model/activity';
import {PlayerdataService} from 'src/app/playerdata.service';

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit, OnDestroy {
  @ViewChild('stepContainers', {static: true}) stepContainer: ElementRef;
  step = 'Étape 1';
  gameId: string;
  buyingActions: any;
  currentStep: Activity[] = [];
  test: Activity;
  testClick = false;
  subPayingActions: Subscription;
  subPlayersWithRoles: Subscription;
  activities: any = null;
  roles: any[] = [];
  currentActivity: any;
  subCurrentActivity: Subscription;
  isChat = false;
  subRisks: Subscription;
  subDays: Subscription;
  listOfDialog: any[] = [];
  subGame: Subscription;

  constructor(private lobbyService: LobbyService,
              private gameService: GameOnService,
              private subscription: SubscriptionService,
              private resourceManager: BuyResourceService,
              private router: Router,
              private playerDataService: PlayerdataService) {
  }

  ngOnInit() {
    console.log(22222222222222);
    this.gameId = this.subscription.gameID;

    this.subPlayersWithRoles = this.subscription.playersWithRoles$.subscribe(data => {
      console.log(data);
      this.roles = data;
    });

    this.subCurrentActivity = this.subscription.currentActivity$.subscribe(data => {
      this.currentActivity = data;
      console.log(this.currentActivity);
    });

    this.subRisks = this.resourceManager.risksReduced$.subscribe(data => {
      console.log(data);
      this.currentActivity.risks = this.currentActivity.risks - data;
    });

    this.subDays = this.resourceManager.daysReduced$.subscribe(data => {
      console.log(data);
      this.currentActivity.numberOfDays = this.currentActivity.numberOfDays - data;
    });

    this.currentStep = this.gameService.currentStep;
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
    });

  }

  getCurrentStep($event: any) {
    this.step = $event;
  }

  closeGame() {
    console.log('Game over');
    const message = {
      request: 'LEAVE_GAME',
      roomID: this.gameService.roomId.toString(),
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
    this.subPlayersWithRoles.unsubscribe();
    this.subCurrentActivity.unsubscribe();
    this.subRisks.unsubscribe();
    this.subDays.unsubscribe();
    this.subGame.unsubscribe();
  }

  initDialog($event: any) {
    this.listOfDialog = [];
  }
}
