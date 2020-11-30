import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class MatchesOnDateService {
  constructor(private httpClient : HttpClient) { }

  getMatchesOnDate(date : string) {
    return this.httpClient.get<FootballMatch>(`http://localhost:9000/specificMatches/${date}`)
  }
}
