import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';

@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  allClubs : FootballClub[];

  constructor(private allClubsService : AllClubsService) { }

  ngOnInit(): void {
    this.getFootballClubs();
  }

  getFootballClubs() : void {
    this.allClubsService.getAllFootballClubs().subscribe(
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
