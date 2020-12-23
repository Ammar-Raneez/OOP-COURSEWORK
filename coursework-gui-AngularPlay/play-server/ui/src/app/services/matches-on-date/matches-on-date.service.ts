import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballMatch } from 'src/app/models/FootballMatch.model';

/**
 * MatchesOnDateService class, to be used to fetch all the matches at a specified date
 * will be calling the API URL localhost:9000/specificMatches/:date
 * The Controller method associated with /specificMatches/:date in the Play backend will be called
 */
@Injectable({
  providedIn: 'root'
})
export class MatchesOnDateService {
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all the matches played at a specific date
   * @param date - date of matches played. Will be used to obtain the matches played at the specified date
   * @return Observable (handles Asynchronous communication) with all the specific matches
   */
  public getMatchesOnDate(date : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${BASE_URL}/specificMatches/${date}`)
  }
}
