import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class SelectedClubService {
  constructor(private httpClient : HttpClient) {  }
  
  getSelectedClub(clubName : string) {
    return this.httpClient.get<FootballClub>(`http://localhost:9000/allmatches/${clubName}`);
  }
}
