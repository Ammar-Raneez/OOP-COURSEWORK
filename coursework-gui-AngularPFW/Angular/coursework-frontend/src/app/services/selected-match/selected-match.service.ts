import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constants';
import { FootballMatch } from '../../models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class SelectedMatchService {
  constructor(private httpClient : HttpClient) { }

  public getSelectedMatch(id : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${API_URL}/allmatches/${id}`);
  }
}
