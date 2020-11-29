import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballMatch } from 'src/app/models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class AllMatchesService {
  constructor(private httpClient : HttpClient) { }

  getAllFootballMatches() { 
    return this.httpClient.get<FootballMatch>("http://localhost:9000/allmatches");
  }
}
