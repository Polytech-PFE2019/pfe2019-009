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

  public messages: Subject<SocketRequest>;
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
          if (JSON.stringify(data).includes('userID')) {
            if (data.userID !== undefined) {
              console.log('send userID');
              this.userID = data.userID;
              this.subscription.sendUserID(data.userID);
            }
          }
          if (JSON.stringify(data).includes('roomID')) {
            console.log('send roomID');
            if (data.userID !== undefined) {
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
          console.log(data);
          return data;
        })) as Subject<SocketRequest>;
  }
}
