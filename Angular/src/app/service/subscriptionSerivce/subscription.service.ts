import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  roomID = new Subject<any>();
  roomID$ = this.roomID.asObservable();

  userID = new Subject<any>();
  userID$ = this.userID.asObservable();

  userName = new Subject<any>();
  userName$ = this.userName.asObservable();

  gameID = new Subject<any>();
  gameID$ = this.gameID.asObservable();

  activites = new Subject<any>();
  activites$ = this.activites.asObservable();

  payingActions = new Subject<any>();
  payingActions$ = this.payingActions.asObservable();

  playersWithRoles = new Subject<any>();
  playersWithRoles$ = this.playersWithRoles.asObservable();

  roles: any[] =[];

  constructor() {
  }

  sendUserID(message) {
    console.log('subscription userID' + message);
    this.userID.next(message);
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
    console.log('subscription gameID' + message);
    this.gameID.next(message);
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
}
