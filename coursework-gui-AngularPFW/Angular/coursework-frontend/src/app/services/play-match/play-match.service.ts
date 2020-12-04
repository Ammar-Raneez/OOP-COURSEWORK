import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatchAndClub } from 'src/app/models/MatchAndClub';

@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {

  constructor(private httpClient : HttpClient) { }

  playMatch() {
    return this.httpClient.get<MatchAndClub>("http://localhost:9000/pointstable/playmatch");
  }
}
