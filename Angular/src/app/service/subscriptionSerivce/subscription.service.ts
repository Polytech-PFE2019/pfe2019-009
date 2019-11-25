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
}
