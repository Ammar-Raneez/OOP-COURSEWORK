import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class PointsTableService {
  constructor(private httpClient : HttpClient) { }

  getAllFootballClubs() { 
    return this.httpClient.get<FootballClub>("http://localhost:9000/pointstable");
  }
}
