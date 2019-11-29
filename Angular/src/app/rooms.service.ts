import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Url} from './model/url';
import {Rooms} from "./rooms";

@Injectable({
  providedIn: 'root'
})
export class RoomsService {

  constructor(private http: HttpClient) {
  }

  getRoomsInfos() {
    return this.http.get<Rooms[]>(Url);
  }
}

