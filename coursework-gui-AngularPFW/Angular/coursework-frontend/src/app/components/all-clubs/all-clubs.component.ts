import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';

@Component({
  selector: 'app-all-clubs',
  templateUrl: './all-clubs.component.html',
  styleUrls: ['./all-clubs.component.css']
})
export class AllClubsComponent implements OnInit {
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
  }
  handleErrorResponse(error : any) : void {
    this.allClubs = error.message;
    console.log(error);
  }
}
