import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  isInitial: any = null;
  isVersionInitial: any = null;
  roomID = new Subject<any>();
  roomID$ = this.roomID.asObservable();

  userID = new Subject<any>();
  userID$ = this.userID.asObservable();
  userId: any;

  userName = new Subject<any>();
  userName$ = this.userName.asObservable();

  gameID: any;
  activites = new Subject<any>();
  activites$ = this.activites.asObservable();

  payingActions = new Subject<any>();
  payingActions$ = this.payingActions.asObservable();

  playersWithRoles = new Subject<any>();
  playersWithRoles$ = this.playersWithRoles.asObservable();

  roles: any[] = [];

  currentActivity = new Subject<any>();
  currentActivity$ = this.currentActivity.asObservable();

  riskHistory: any[] = [];
  days = new Subject<any>();
  days$ = this.days.asObservable();

  costs = new Subject<any>();
  costs$ = this.costs.asObservable();

  failures = new Subject<any>();
  failures$ = this.failures.asObservable();

  myHistories = new Subject<any>();
  myHistories$ = this.myHistories.asObservable();

  myRole: any;
  current: any;

  currentActivityID = new Subject<any>();
  currentActivityID$ = this.currentActivityID.asObservable();

  costInital: any = null;


  constructor() {
  }

  sendHistory(risk) {
    this.riskHistory = this.riskHistory.concat(risk);
    console.log(this.riskHistory);
  }

  sendUserID(message) {
    console.log('subscription userID' + message);
    this.userID.next(message);
    this.userId = message;
  }

  sendRoomID(message) {
    console.log('subscription roomID' + message);
    this.roomID.next(message);
  }

  sendUserName(message) {
    console.log('subscription userName' + message);
    this.userName.next(message);
  }

  sendGameId(message) {
    this.gameID = message;
  }

  sendActivities(message) {
    console.log('subscription activities' + message);
    this.activites.next(message);
  }

  sendPayingActions(message) {
    console.log('subscription activities' + message);
    this.payingActions.next(message);
  }

  sendPlayersWithRoles(msg) {
    this.roles = msg;
    console.log('subscription players with role' + msg);
    this.payingActions.next(msg);
  }

  sendCurrentActivity(msg) {
    console.log('sendCurrentActivity' + msg);
    this.currentActivity.next(msg);
    this.currentActivityID.next(msg.title);
  }

  sendCosts(msg) {
    console.log('sendCosts' + msg);
    this.costs.next(msg);
  }

  sendDays(msg) {
    console.log('sendDays' + msg);
    this.days.next(msg);
  }

  sendFailures(msg) {
    console.log('sendFailures' + msg);
    this.failures.next(msg);
  }

  sendHistories(msg) {
    console.log('send' + msg);
    this.myHistories.next(msg);
  }
}
