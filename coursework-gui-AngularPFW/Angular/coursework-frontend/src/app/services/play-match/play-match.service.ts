import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constants';
import { FootballClub } from 'src/app/models/FootballClub';
// import { MatchAndClub } from 'src/app/models/MatchAndClub';

@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {
  constructor(private httpClient : HttpClient) { }

  public playMatch() : Observable<FootballClub> {
    // return this.httpClient.get<MatchAndClub>("http://localhost:9000/pointstable/playmatch");
    return this.httpClient.get<FootballClub>(`${API_URL}/pointstable/playmatch`);
  }
}
