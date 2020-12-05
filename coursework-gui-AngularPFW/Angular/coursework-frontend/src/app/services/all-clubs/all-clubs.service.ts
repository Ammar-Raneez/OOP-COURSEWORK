import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballClub } from 'src/app/models/FootballClub';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AllClubsService {
  constructor(private httpClient : HttpClient) { }

  public getAllFootballClubs() : Observable<FootballClub> { 
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable");
  }
}
