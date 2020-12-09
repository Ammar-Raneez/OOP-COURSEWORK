import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constants';
import { FootballMatch } from 'src/app/models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class MatchesOnDateService {
  constructor(private httpClient : HttpClient) { }

  public getMatchesOnDate(date : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${API_URL}/specificMatches/${date}`)
  }
}
