import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesFilterService {
  constructor(private httpClient : HttpClient) { }

  getMatchesGoalFilter() {
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable/goalfilter");
  }

  getMatchesWinFilter() {
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable/winfilter");
  }
}
