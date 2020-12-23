import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballClub } from 'src/app/models/FootballClub.model';

/**
 * SelectedClubService class, to be used to fetch a specific club
 * will be calling the API URL localhost:9000/clubs/:clubName
 * The Controller method associated with /clubs/:clubName in the Play backend will be called
 */
@Injectable({
  providedIn: 'root'
})
export class SelectedClubService {
  constructor(private httpClient : HttpClient) {  }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of the specific club
   * @param clubName - clubs name. Will be used to get the specific club, based on club name equality
   * @return Observable (handles Asynchronous communication) with the specific club
   */
  public getSelectedClub(clubName : string) : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/clubs/${clubName}`);
  }
}
