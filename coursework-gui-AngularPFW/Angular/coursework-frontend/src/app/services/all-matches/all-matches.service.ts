import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballMatch } from 'src/app/models/FootballMatch';
// import { MatchAndClub } from 'src/app/models/MatchAndClub';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesService {
  constructor(private httpClient : HttpClient) { }

  public getAllFootballMatches() : Observable<FootballMatch> { 
    return this.httpClient.get<FootballMatch>(`${API_URL}/allmatches`);
  }
}
