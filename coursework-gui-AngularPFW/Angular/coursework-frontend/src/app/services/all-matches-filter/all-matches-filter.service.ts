import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesFilterService {
  constructor(private httpClient : HttpClient) { }

  public getMatchesGoalFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable/goalfilter");
  }

  public getMatchesWinFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable/winfilter");
  }
}
