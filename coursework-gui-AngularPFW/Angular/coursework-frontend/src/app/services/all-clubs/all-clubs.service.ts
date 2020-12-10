import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballClub } from 'src/app/models/FootballClub';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class AllClubsService {
  constructor(private httpClient : HttpClient) { }

  public getAllFootballClubs() : Observable<FootballClub> { 
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable`);
  }
}
