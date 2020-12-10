import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/app.constants';
import { FootballMatch } from '../../models/FootballMatch';

@Injectable({
  providedIn: 'root'
})
export class SelectedMatchService {
  constructor(private httpClient : HttpClient) { }

  public getSelectedMatch(id : string) : Observable<FootballMatch> {
    return this.httpClient.get<FootballMatch>(`${BASE_URL}/allmatches/${id}`);
  }
}
