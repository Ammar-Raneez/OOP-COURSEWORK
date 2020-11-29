import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { PointsTableService } from 'src/app/services/points-table/points-table.service';

@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  allClubs : FootballClub[];

  constructor(private pointsTableService : PointsTableService) { }

  ngOnInit(): void {
  }

  getFootballClubs() : void {
    this.pointsTableService.getAllFootballClubs().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  handleSuccessfulResponse(response : any) : void {
    this.allClubs = response;
    console.log(response);
  }
  handleErrorResponse(error : any) : void {
    this.allClubs = error.message;
    console.log(error);
  }
}
