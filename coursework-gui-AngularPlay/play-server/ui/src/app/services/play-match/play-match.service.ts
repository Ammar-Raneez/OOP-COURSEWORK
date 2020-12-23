import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballClub } from 'src/app/models/FootballClub.model';

/**
 * PlayMatchService class, to be used to fetch the updated matches upon playing a match
 * will be calling the API URL localhost:9000/playmatch
 * The Controller method associated with /playmatch in the Play backend will be called
 */
@Injectable({
  providedIn: 'root'
})
export class PlayMatchService {
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all the updated matches
   * @return Observable (handles Asynchronous communication) with all the updated matches
   */
  public playMatch() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable/playmatch`);
  }
}
