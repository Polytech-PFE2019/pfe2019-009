import { Component } from '@angular/core';
import { WebsocketService } from "./websocket.service";
import { LobbyService } from "./lobby.service";
import { SocketRequest } from 'src/Request';

import {Component, ViewChild} from '@angular/core';
import {GameCreatorComponent} from "./modal-module/game-creator/game-creator.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [WebsocketService, LobbyService]
})
export class AppComponent {



  constructor(private lobbyService: LobbyService) {
    lobbyService.messages.subscribe(msg => {
      console.log("Response: " + msg.response)

      // Implement here onMessage methods

      switch (msg.response) {
        case "UPDATE": this.updateRoom();
          break;
      }


    });
  }

  updateRoom() {

  }



  sendRequest() {
    let message = {
      request: "CREATE_GAME",
      username: "TEST"
    };
    this.lobbyService.messages.next(message as SocketRequest);
  }
}
