import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';

@Component({
  selector: 'app-all-clubs',
  templateUrl: './all-clubs.component.html',
  styleUrls: ['./all-clubs.component.css']
})
export class AllClubsComponent implements OnInit {
  private allClubs : FootballClub[];

  constructor(private allClubsService : AllClubsService) { }

  ngOnInit(): void {
    this.getFootballClubs();
  }

  public getFootballClubs() : void {
    this.allClubsService.getAllFootballClubs().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  private handleSuccessfulResponse(response : any) : void {
    console.log(response)
    this.allClubs = response;
  }
  private handleErrorResponse(error : any) : void {
    this.allClubs = error.message;
    console.log(error);
  }

  public getAllFootballClubs() : FootballClub[] {
    return this.allClubs;
  }
}
