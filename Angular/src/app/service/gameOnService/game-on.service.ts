import {Injectable} from '@angular/core';
import {WebsocketService} from '../webSocketService/websocket.service';
import {SubscriptionService} from '../subscriptionSerivce/subscription.service';
import {Subject, Subscription} from 'rxjs';
import {SocketRequest} from '../../../Request';
import {URLGame} from '../../model/url';
import {map} from 'rxjs/operators';
import {Activity} from '../../model/activity';
import {ActionSet} from '../../model/action';
import {BuyResourceService} from '../resources/buy-resource.service';
import {Roles} from '../../model/roles';

@Injectable()
export class GameOnService {

  public messages = new Subject<SocketRequest>();
  reponses = new Subject<any>();
  reponses$ = this.reponses.asObservable();
  test: Activity;
  currentStep: Activity[] = [];
  currentActivityID: any;
  currentActivity: any;
  gameID: any;
  userID: any;
  costProject: any;
  delayProject: any;
  failureProject: any;
  current: any;
  roles = Roles;
  history = new Subject<any>();
  history$ = this.history.asObservable();
  roomId: any;
  subRiskReduced: Subscription;
  riskReduced = 0;
  results: any;

  constructor(private wsService: WebsocketService,
              private resourceManager: BuyResourceService,
              private subscription: SubscriptionService) {
    this.messages = wsService
      .connect(URLGame)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          if (JSON.stringify(data).includes('players')) {

          }
          if (JSON.stringify(data).includes('roomID')) {
            console.log('send roomID');
            if (data.roomID !== undefined) {
              this.roomId = data.roomID;
              this.subscription.sendRoomID(data.roomID);
            }
          }

          if (JSON.stringify(data).includes('gameID')) {
            console.log('send roomID');
            if (data.gameID !== undefined) {
              this.subscription.sendGameId(data.gameID);
              this.gameID = data.gameID;
            }
          }

          if (data.costProject !== undefined) {
            this.costProject = data.costProject;
          }

          if (data.delayProject !== undefined) {
            this.delayProject = data.delayProject;
          }

          if (data.failureProject !== undefined) {
            this.failureProject = data.failureProject;
          }

          if (data.response === 'LAUNCH_GAME') {
            this.userID = data.player.userID;
            this.updateMinAndMax(data.project);
            if (data.player.userID !== undefined) {
              console.log('send userID');
              this.userID = data.player.userID;
              this.subscription.sendUserID(data.player.userID);
            }

            console.log(data);
            this.currentActivityID = data.currentActivityID;
            console.log(this.currentStep);
            this.currentStep = [];
            console.log('after++++++++++++  ' + data.activities);
            for (const activity of data.activities) {
              this.test = new Activity(activity);
              this.currentStep.push(this.test);
            }
            this.subscription.sendActivities(this.currentStep);
            this.currentActivity = this.currentStep[0];
            console.log(this.currentStep);
            console.log(this.currentActivity);
            this.subscription.sendCurrentActivity(this.currentActivity);
          }
          if (data.response === 'CHANGE_ACTIVITY') {
            const currentId = data.activityID;
            this.currentActivity = this.currentStep[currentId - 1];
            this.currentStep[currentId - 1].extraPayment = data.extraPaying;
            this.updateExtraPayment(currentId);
            console.log(this.currentActivity);
            console.log(this.currentStep);
            this.subscription.sendCurrentActivity(this.currentActivity);
          }


          if (data.response === 'UPDATE_PAYMENT') {
            this.updateMinAndMax(data.project);
            const currentId = data.activityID;
            if (this.currentStep[currentId - 1].history === null) {
              this.currentStep[currentId - 1].history = data;
            } else {
              this.currentStep[currentId - 1].history = this.currentStep[currentId - 1].history.concat(data);
            }
            console.log(this.currentStep[currentId - 1].history);
            this.updateInformationAfterPayment(currentId);
            console.log(this.currentStep);
          }

          if (data.response === 'drawRisk') {
            const currentId = data.riskOfActivityId;
            this.currentStep[currentId - 1].riskCards = data.risks;
            console.log(this.currentStep);
            console.log(this.currentStep[currentId - 1].riskCards);
            this.addInformationAfterRiskCards(currentId);
          }
          if (data.response === 'FINISH') {
            this.results = data;
          }
          this.reponses.next(data);
          return data;
        })) as Subject<SocketRequest>;
  }

  addInformationAfterRiskCards(currentId) {
    const currentAc = this.currentStep[currentId - 1];
    let bonus = [];
    for (const i of currentAc.riskCards) {
      bonus = bonus.concat(i.bonus);
    }
    console.log(bonus);
    console.log(this.currentStep);
    for (const b of bonus) {
      switch (b.type) {
        case 'DAYS':
          this.currentStep[b.activityIdAssociate - 1].numberOfDays += b.amount;
          break;
        case 'RISK':
          this.currentStep[b.activityIdAssociate - 1].risks += b.amount;
          break;
      }
    }
    console.log(this.currentStep);
  }


  updateExtraPayment(currentId) {
    const currentAc = this.currentStep[currentId - 1];
    for (const extra of currentAc.extraPayment) {
      for (const payment of currentAc.payingActions) {
        if (payment.roleID === extra.roleID) {
          payment.actions[0].actions[0].amountToPay += extra.amount;
        }
      }
    }
  }

  updateInformationAfterPayment(currentId) {
    const currentAc = this.currentStep[currentId - 1];
    console.log(this.currentStep);
    console.log(currentAc);
    for (const b of currentAc.history.payments) {
      switch (b.type) {
        case 'DAYS':
          this.currentStep[currentId - 1].numberOfDays -= b.bonus;
          break;
        case 'RISKS':
          this.currentStep[currentId - 1].risks -= b.bonus;
          break;
      }
    }
    console.log(this.currentStep);
    this.subscription.sendActivities(this.currentStep);

  }

  updateMinAndMax(project) {
    const failure = {
      minFailure: project.minFailure,
      maxFailure: project.maxFailure
    };
    this.subscription.sendFailures(failure);

    const cost = {
      minCost: project.minCost,
      maxCost: project.maxCost
    };
    this.subscription.sendCosts(cost);

    const days = {
      minTime: project.minTime,
      maxTime: project.maxTime
    };
    this.subscription.sendDays(days);
  }
}
