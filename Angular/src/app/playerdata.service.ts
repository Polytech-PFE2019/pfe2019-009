import { Injectable } from '@angular/core';
import { Player } from './Player';

@Injectable({
  providedIn: 'root'
})
export class PlayerdataService {

  public player: Player
  constructor() { }
}
