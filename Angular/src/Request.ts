import { Player } from './app/Player';

export class SocketRequest {
    request: string;
    response: string;
    username: string;
    userID: string;
    roomID: string;
    roleID: string;
    players: [Player];
    hostID: string;
    gameID: string;
    amount: string;
    player: any;
    activities: any;

}
