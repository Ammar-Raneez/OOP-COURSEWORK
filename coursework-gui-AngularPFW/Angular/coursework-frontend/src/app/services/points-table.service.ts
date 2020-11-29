import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { PointsTable } from '../models/PointsTable';

@Injectable({
  providedIn: 'root'
})
export class PointsTableService {
  constructor(private httpClient : HttpClient) { }

  executeTestPlayFramework() { 
    return this.httpClient.get<PointsTable>("http://localhost:9000/test");
  }
}
