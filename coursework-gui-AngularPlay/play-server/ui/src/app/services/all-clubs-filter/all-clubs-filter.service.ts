import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballClub } from 'src/app/models/FootballClub.model';

/**
 * AllClubsFilterService class, to be used to fetch all the clubs filtered by a specific filter
 * will be calling the API URL localhost:9000/pointstable/filterType
 * The Controller method associated with /pointstable/filterType in the Play backend will be called
 */
@Injectable({
  providedIn: 'root'
})
export class AllClubsFilterService {
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all clubs, sorted by goals for
   * @return Observable (handles Asynchronous communication) with all the sorted FootballClubs
   */
  public getClubsGoalFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable/goalfilter`);
  }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all clubs, sorted by wins
   * @return Observable (handles Asynchronous communication) with all the sorted FootballClubs
   */
  public getClubsWinFilter() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable/winfilter`);
  }
}
