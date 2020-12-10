import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballMatch } from 'src/app/models/FootballMatch.model';
// import { MatchAndClub } from 'src/app/models/MatchAndClub';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesService {
  constructor(private httpClient : HttpClient) { }

  public getAllFootballMatches() : Observable<FootballMatch> { 
    return this.httpClient.get<FootballMatch>(`${BASE_URL}/allmatches`);
  }
}
