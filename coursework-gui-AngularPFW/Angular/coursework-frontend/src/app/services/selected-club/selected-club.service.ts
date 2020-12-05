import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FootballClub } from 'src/app/models/FootballClub';

@Injectable({
  providedIn: 'root'
})
export class SelectedClubService {
  constructor(private httpClient : HttpClient) {  }
  
  public getSelectedClub(clubName : string) : Observable<FootballClub> {
    return this.httpClient.get<FootballClub>(`http://localhost:9000/clubs/${clubName}`);
  }
}
