import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyResourceService {
  resources = new Subject<any>();
  resources$ = this.resources.asObservable();

  reduce = new Subject<any>();
  reduce$ = this.reduce.asObservable();

  payment = new Subject<any>();
  payment$ = this.payment.asObservable();
  money: any;

  currentMonney = new Subject<any>();
  currentMonney$ = this.currentMonney.asObservable();

  currentResource = new Subject<any>();
  currentResource$ = this.currentResource.asObservable();

  risksReduced = new Subject<any>();
  risksReduced$ = this.risksReduced.asObservable();

  daysReduced = new Subject<any>();
  daysReduced$ = this.daysReduced.asObservable();

  constructor() {
  }

  sendResourcesBuying(message) {
    console.log('subscription resources buying' + message);
    this.resources.next(message);
  }

  sendResourcesReduced(message) {
    console.log('subscription resources reduced' + message);
    this.reduce.next(message);
  }

  sendPayment(message) {
    console.log('subscription payment' + message);
    this.payment.next(message);
    this.money = message;
  }

  sendCurrentMonney(msg) {
    console.log('subscription current money' + msg);
    this.currentMonney.next(msg);
  }

  sendCurrentResource(msg) {
    console.log('subscription current resource' + msg);
    this.currentResource.next(msg);
  }

  sendReducedRisk(msg) {
    console.log('sendReducedRisk' + msg);
    this.risksReduced.next(msg);
  }

  sendDaysReduced(msg) {
    console.log('sendDaysReduced' + msg);
    this.daysReduced.next(msg);
  }
}
