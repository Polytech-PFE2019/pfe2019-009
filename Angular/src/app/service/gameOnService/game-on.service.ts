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
            }
          }

          if (data.response === 'UPDATE') {
            console.log(data);
            this.subscription.sendActivities(data.activities);
            this.currentActivityID = data.currentActivityID;
            console.log('after++++++++++++  ' + data.activities);
            for (const activity of data.activities) {
              this.test = new Activity(activity);
              console.log(this.test);
              this.currentStep.push(this.test);
              console.log(this.currentStep);
              this.currentActivity = this.currentStep[activity.activityID - 1];
              console.log(activity);
            }

          }
          console.log(data);
          return data;
        })) as Subject<SocketRequest>;
  }
}
