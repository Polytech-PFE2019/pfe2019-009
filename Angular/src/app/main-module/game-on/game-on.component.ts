import {Component, OnInit} from '@angular/core';
import {Steps} from '../../model/step';
import {LobbyService} from '../../service/lobbyService/lobby.service';
import {SocketRequest} from '../../../Request';
import {Router} from '@angular/router';
import {GameOnService} from "../../service/gameOnService/game-on.service";

@Component({
  selector: 'app-game-on',
  templateUrl: './game-on.component.html',
  styleUrls: ['./game-on.component.css']
})
export class GameOnComponent implements OnInit {

  steps: any = Steps;
  step = 'Étape 1';
  getDataFromParent = 0;
  getDataFromActivity = 0;
  getMoneyFromPerson = 0;

  constructor(private lobbyService: LobbyService,
              private gameService: GameOnService,
              private router: Router) {
  }

  ngOnInit() {
    this.gameService.messages.subscribe(data => {
        console.log(data);
    });
  }

  getResource(event) {
    console.log('payresource ' + event);
    this.getDataFromParent = event;
  }

  getPayment(event) {
    console.log('payactivity ' + event);
    this.getDataFromActivity = event;
  }

  getMoney(event) {
    this.getMoneyFromPerson = event;
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
