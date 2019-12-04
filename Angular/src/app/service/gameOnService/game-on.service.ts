import {Injectable} from '@angular/core';
import {WebsocketService} from '../webSocketService/websocket.service';
import {SubscriptionService} from '../subscriptionSerivce/subscription.service';
import {Subject} from 'rxjs';
import {SocketRequest} from '../../../Request';
import {URLGame} from '../../model/url';
import {map} from 'rxjs/operators';
import {Activity} from '../../model/activity';
import {ActionSet} from '../../model/action';

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
  history = new Subject<any>();
  history$ = this.history.asObservable();

  constructor(private wsService: WebsocketService,
              private subscription: SubscriptionService) {
    this.messages = wsService
      .connect(URLGame)
      .pipe(
        map((response: MessageEvent): SocketRequest => {
          const data = JSON.parse(response.data);
          console.log(data);
          if (JSON.stringify(data).includes('players')) {

          }
          if (JSON.stringify(data).includes('roomID')) {
            console.log('send roomID');
            if (data.roomID !== undefined) {
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

            const days = {
              minTime: data.project.minTime,
              maxTime: data.project.maxTime
            };
            this.subscription.sendDays(days);
            if (data.player.userID !== undefined) {
              console.log('send userID');
              this.userID = data.player.userID;
              this.subscription.sendUserID(data.player.userID);
            }

            console.log(data);
            this.subscription.sendActivities(data.activities);
            this.currentActivityID = data.currentActivityID;
            console.log('after++++++++++++  ' + data.activities);
            for (const activity of data.activities) {
              this.test = new Activity(activity);
              console.log(this.test);
              this.currentStep.push(this.test);
            }
            this.currentActivity = this.currentStep[0];
            console.log(this.currentStep);
            console.log(this.currentActivity);
            this.subscription.sendCurrentActivity(this.currentActivity);
          }
          if (data.response === 'CHANGE_ACTIVITY') {
            const currentId = data.activityID;
            this.currentActivity = this.currentStep[currentId - 1];
            console.log(this.currentActivity);
            this.subscription.sendCurrentActivity(this.currentActivity);
          }

          if (data.response === 'UPDATE_PAYMENT') {
            const currentId = data.activityID;
            this.currentStep[currentId - 1].history = data;
            console.log(this.currentStep[currentId - 1].history);
          }
          console.log(data);
          this.reponses.next(data);
          return data;
        })) as Subject<SocketRequest>;
  }
}
