/*
 * AllClubsService
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballClub } from 'src/app/models/FootballClub.model';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';

/**
 * AllClubsService class, to be used to fetch all the clubs, will be calling the API URL localhost:9000/pointstable
 * The Controller method associated with /pointstable in the Play backend will be called
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Injectable({
  providedIn: 'root'
})
export class AllClubsService {
  //*Specifying an object of HttpClient for REST API accessibility. Is accessible throughout*//
  constructor(private httpClient : HttpClient) { }

  /**
   * Sends a GET request to the url and Returns an Observable holding the Json data of all clubs
   * @return Observable (handles Asynchronous communication) with all FootballClubs
   */
  public getAllFootballClubs() : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`${BASE_URL}/pointstable`);
  }
}
