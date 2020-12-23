/*
 * AllMatchesService
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballMatch } from 'src/app/models/FootballMatch.model';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';

/**
 * AllMatchesService class, to be used to fetch all the matches, will be calling the API URL localhost:9000/allmatches
 * The Controller method associated with /pointstable in the Play backend will be called
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Injectable({
  providedIn: 'root'
})
export class AllMatchesService {
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all matches
   * @return Observable (handles Asynchronous communication) with all matches
   */
  public getAllFootballMatches() : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${BASE_URL}/allmatches`);
  }
}
