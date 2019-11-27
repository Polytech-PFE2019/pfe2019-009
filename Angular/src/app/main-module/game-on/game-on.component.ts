import {Component, OnDestroy, OnInit} from '@angular/core';
import {Steps} from '../../model/step';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {Router} from '@angular/router';
import {GameOnService} from '../../service/gameOnService/game-on.service';
import {Subscription} from 'rxjs';
import {SubscriptionService} from '../../service/subscriptionSerivce/subscription.service';
import {BuyResourceService} from '../../service/resources/buy-resource.service';
import {Activity} from '../../model/activity';

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit, OnDestroy {

  steps: any = Steps;
  step = 'Étape 1';
  getDataFromParent = 0;
  getPriceFromParent = 0;
  getDataFromActivity = 0;
  getMoneyFromPerson = 0;
  getResourceFromPerson = 0;
  subGameId: Subscription;
  gameId: string;
  currentResource: number;
  currentMonney: number;
  buyingActions: any;
  activites: any;
  currentStep: Activity[] = [];
  test: Activity;

  constructor(private lobbyService: LobbyService,
              private gameService: GameOnService,
              private subscription: SubscriptionService,
              private resourceManager: BuyResourceService,
              private router: Router) {
  }

  ngOnInit() {
    this.subGameId = this.subscription.gameID$.subscribe(id => {
      this.gameId = id;
    });

    this.gameService.messages.subscribe(data => {
      console.log(data);
      switch (data.response) {
        case 'UPDATE':
          console.log(data);
          this.resourceManager.sendCurrentResource(data.player.resources);
          this.resourceManager.sendCurrentMonney(data.player.money);
          this.subscription.sendActivities(data.activities);
          const testss = JSON.parse(data.activities);
          for (const activity of testss) {
            this.test = new Activity(activity);
            console.log(this.test);
            this.currentStep.push(this.test);
            console.log(this.currentStep);
          }
      }
    });

    // const testdata = [
    //   {
    //     activityID: 1,
    //     playersID: [1, 2],
    //     risks: 3,
    //     numberOfDays: 100,
    //     status: 'FINISHED',
    //     description: 'text',
    //     buyingActions: [{
    //       status: false,
    //       amount: 0,
    //       roleID: 1
    //     }],
    //     payingActions: [{
    //       status: false,
    //       roleID: 1,
    //       payType: 'RISK',
    //       bonusGiven: 0,
    //       amountPaid: 0,
    //       actions: [
    //         {amountToPay: 1, bonusAmount: 1},
    //         {amountToPay: 4, bonusAmount: 2}
    //       ]
    //     },
    //       {
    //         status: false,
    //         roleID: 1,
    //         payType: 'DAYS',
    //         bonusGiven: 0,
    //         amountPaid: 0,
    //         actions: [
    //           {amountToPay: 2, bonusAmount: 1},
    //           {amountToPay: 4, bonusAmount: 2}
    //         ]
    //       },
    //       {
    //         status: false,
    //         roleID: 1,
    //         payType: 'MANDATORY',
    //         bonusGiven: 0,
    //         amountPaid: 0,
    //         actions: [
    //           {amountToPay: 1, bonusAmount: 0},
    //         ]
    //       },
    //     ]
    //   },
    // ];
    //
    // this.test = new Activity(testdata[0]);
    // console.log(this.test);
    // this.currentStep.push(this.test);
    // console.log(this.currentStep);
  }

  getResource(event) {
    console.log('payresource ' + event);
    this.getDataFromParent = event;
  }

  // the price paid by user
  getPrice(event) {
    console.log('price is in ' + event);
    this.getPriceFromParent = event;
  }

  // how many resource user has to pay
  getPayment(event) {
    console.log('payactivity ' + event);
    this.getDataFromActivity = event;
  }

  // how many money user remains
  getMoney(event) {
    this.getMoneyFromPerson = event;
  }

  // how many resource user remains
  getRemainResource(event) {
    this.getResourceFromPerson = event;
  }

  getCurrentStep($event: any) {
    this.step = $event;
  }

  closeGame() {
    console.log('Game over');
    const message = {
      request: 'LEAVE_GAME',
      roomID: '0',
      userID: '2'
    };
    this.lobbyService.messages.next(message as SocketRequest);
    this.router.navigate(['']);
  }

  ngOnDestroy(): void {
    this.subGameId.unsubscribe();
  }
}
