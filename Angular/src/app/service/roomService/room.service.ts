import {Injectable} from '@angular/core';
import {Server} from 'ws';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  roomSocket: WebSocket;

  constructor() {
  }

  connect(url: string, data): Observable<any> {
    this.roomSocket = new WebSocket(url);
   // this.roomSocket.send(data);
    return new Observable(
      observer => {
        this.roomSocket.onmessage = (event) => {
          console.log(event);
          observer.next(event);
        };
        this.roomSocket.onerror = (event) => observer.error(event);
        this.roomSocket.onclose = (event) => observer.complete();
      }
    );
  }

  // rivate create(url): Rx.Subject<MessageEvent> {
  //   this.ws = new WebSocket(url);
  //
  //   let observable = Rx.Observable.create((obs: Rx.Observer<MessageEvent>) => {
  //     this.ws.onmessage = obs.next.bind(obs);
  //     this.ws.onerror = obs.error.bind(obs);
  //     this.ws.onclose = obs.complete.bind(obs);
  //     return this.ws.close.bind(this.ws);
  //   });
  //   let observer = {
  //     next: (data: Object) => {
  //       if (this.ws.readyState === WebSocket.OPEN) {
  //         this.ws.send(JSON.stringify(data));
  //       }
  //     }
  //   };
  //   return Rx.Subject.create(observer, observable);
  // }

  send(data) {
    this.roomSocket.send(data);
  }
}
