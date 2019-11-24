import {Component, OnInit} from '@angular/core';
import {Steps} from '../model/step';
import {LobbyService} from "../service/lobby.service";
import {SocketRequest} from "../../Request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit {

  steps: any = Steps;
  step = 'Étape 1';

  constructor(private lobbyService: LobbyService,
              private router: Router) {
    lobbyService.messages.subscribe(data => {
      console.log(data);
    });
  }

  ngOnInit() {
  }

  getCurrentStep($event: any) {
    this.step = $event;
  }

  closeGame() {
    console.log('Game over');
    const message = {
      request: 'LEAVE_GAME',
      roomID: '0',
      userID: '2'
    };
    this.lobbyService.messages.next(message as SocketRequest);
    this.router.navigate(['']);
  }
}
