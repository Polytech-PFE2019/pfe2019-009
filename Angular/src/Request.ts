import {Player} from './app/Player';
import {Activity} from './app/model/activity';

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
  currentActivityID: any;
  payments: any;
  numberOfPlayersConnected;
  costProject: any;
  delayProject: any;
  failureProject: any;
  project: any;
  message: any;
  negotiationID: string;
  USERID: any;
  negociationID: string;
  gameType: string;
  days: any;
  cost: any;
  reason : string;

}
