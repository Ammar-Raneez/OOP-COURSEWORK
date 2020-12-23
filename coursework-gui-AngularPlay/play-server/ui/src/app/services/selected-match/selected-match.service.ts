import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballMatch } from '../../models/FootballMatch.model';

/**
 * SelectedMatchService class, to be used to fetch a specific match
 * will be calling the API URL localhost:9000/allmatches/:id
 * The Controller method associated with /allmatches/:id in the Play backend will be called
 */
@Injectable({
  providedIn: 'root'
})
export class SelectedMatchService {
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of the specific match
   * @param id - match id. Will be used to index into the list of matches, to return the match at the specified index
   * @return Observable (handles Asynchronous communication) with the specific match
   */
  public getSelectedMatch(id : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${BASE_URL}/allmatches/${id}`);
  }
}
