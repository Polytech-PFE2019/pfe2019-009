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
import {History} from '../../model/history';

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
  groupChatMessages = [];
  mesHistories: History[] = [];

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
            this.subscription.isInitial = data.gameType === 'INITIAL';
            this.userID = data.player.userID;
            this.updateMinAndMax(data);
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
            this.subscription.current = this.currentActivity;
          }
          if (data.response === 'CHANGE_ACTIVITY') {
            const currentId = data.activityID;
            this.currentActivity = this.currentStep[currentId - 1];
            this.currentStep[currentId - 1].extraPayment = data.extraPaying;
            this.currentStep[currentId - 1].contractsGiver = data.contractsGiver;
            this.currentStep[currentId - 1].contractsReceiver = data.contractsReceiver;
            this.updateExtraPayment(currentId);
            console.log(this.currentActivity);
            console.log(this.currentStep);
            this.subscription.sendCurrentActivity(this.currentActivity);
            this.subscription.current = this.currentActivity;
          }


          if (data.response === 'UPDATE_PAYMENT') {
            this.updateMinAndMax(data);
            const currentId = data.activityID;
            this.currentStep[currentId - 1].history = data.payments;

            console.log(this.currentStep[currentId - 1].history);
            this.updateInformationAfterPayment(currentId);
            this.getMyHistory(data);
            console.log(this.currentStep);
          }
          if (data.response === 'BUY_RESOURCES'){
            this.resourceManager.sendResourcesBuying(data.buyingResources);
            this.resourceManager.sendPayment(data.price);
          }

          if (data.response === 'drawRisk') {
            const currentId = data.riskOfActivityId;
            this.currentStep[currentId - 1].riskCards = data.risks;
            console.log(this.currentStep);
            console.log(this.currentStep[currentId - 1].riskCards);
            this.addInformationAfterRiskCards(currentId);
            this.updateMinAndMax(data);
          }
          if (data.response === 'FINISH') {
            this.results = data;
          }

          if (data.response === 'MSG_GROUP_CHAT') {
            this.groupChatMessages.push(data);
          }
          if (data.response === 'KO'){
            alert(data.reason)
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
    for (const b of currentAc.history) {
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

  updateMinAndMax(data) {
    const failure = {
      minFailure: data.project.minFailure,
      maxFailure: data.project.maxFailure
    };
    this.subscription.sendFailures(failure);

    const cost = {
      minCost: data.project.minCost,
      maxCost: data.project.maxCost
    };
    this.subscription.sendCosts(cost);
    this.subscription.costInital = cost;

    const days = {
      minTime: data.project.minTime,
      maxTime: data.project.maxTime
    };
    this.subscription.sendDays(days);
  }

  getMyHistory(data) {
    const currentId = data.activityID;
    const currentAc = this.currentStep[currentId - 1];
    console.log(this.currentStep);
    console.log(currentAc);
    let historyTmp = null;
    let isTest = true;
    const role = this.subscription.myRole.id;
    for (const b of currentAc.history) {
      console.log(b, role);
      if (role === b.roleID && isTest) {
        historyTmp = new History(currentId, this.subscription.myRole.id);
        historyTmp.payments.push(b);
        isTest = false;
      } else if (role === b.roleID && !isTest && historyTmp !== null) {
        historyTmp.payments.push(b);
      }
    }
    if (historyTmp !== null) {
      this.mesHistories.push(historyTmp);
    }
    console.log(this.mesHistories);
    this.subscription.sendHistories(this.mesHistories);
  }
}
