import { Component, OnInit } from '@angular/core';
import { GameOnService } from '../service/gameOnService/game-on.service';
import { PlayerdataService } from '../playerdata.service';
import { SocketRequest } from 'src/Request';
import { Router } from '@angular/router';


@Component({
  selector: 'app-loading-page',
  templateUrl: './loading-page.component.html',
  styleUrls: ['./loading-page.component.css']
})
export class LoadingPageComponent implements OnInit {

  constructor(private gameService: GameOnService, private playerDataService: PlayerdataService, private router: Router) {
  }

  ngOnInit() {
    this.gameService.messages.subscribe(data => {
      console.log(data);
      if (data.numberOfPlayersConnected == 6) {
        this.router.navigate(['gameon']);
      }
    });

    const player = this.playerDataService.player;

    const req = {
      request: 'JOIN_GAME',
      userID: player.playerID.toString(),
      gameID: player.gameID
    };

    this.gameService.messages.next(req as SocketRequest);
  }

}
