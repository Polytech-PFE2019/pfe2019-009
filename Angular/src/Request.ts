import { Player } from './app/Player';

export class SocketRequest {
    request: string;
    response: string;
    username: string;
    userID: string;
    roomID: string;
    players: [Player]

}