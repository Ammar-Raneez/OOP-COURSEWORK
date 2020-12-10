import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesFilterService {
  constructor(private httpClient : HttpClient) { }

  public getMatchesGoalFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable/goalfilter`);
  }

  public getMatchesWinFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable/winfilter`);
  }
}
