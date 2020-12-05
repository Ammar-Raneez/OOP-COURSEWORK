import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FootballMatch } from '../../models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class SelectedMatchService {
  constructor(private httpClient : HttpClient) { }

  public getSelectedMatch(id : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`http://localhost:9000/allmatches/${id}`);
  }
}
