import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { MatchAndClub } from 'src/app/models/MatchAndClub';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';
import { AllMatchesFilterService } from 'src/app/services/all-matches-goalfilter/all-matches-filter.service';
import { AllMatchesService } from 'src/app/services/all-matches/all-matches.service';

@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  allClubs : FootballClub[];

  constructor(private allClubsService : AllClubsService, private allClubsFilterService : AllMatchesFilterService,
    private allMatchesService : AllMatchesService
    ) { }
  ngOnInit(): void {
    this.getFootballClubs();
  }

  updateFootballClubsAfterPlay() : void {
    this.allMatchesService.playMatch().subscribe(
      response => this.handleSuccessfulResponseAfterPlay(response),
      error => this.handleErrorResponse(error)
    )
  }
  handleSuccessfulResponseAfterPlay(response : any) : void {
    this.allClubs = response[0];
  }


  getFootballClubsSortedOnWins() : void {
    this.allClubsFilterService.getMatchesWinFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }

  getFootballClubsSortedOnGoals() : void {
    this.allClubsFilterService.getMatchesGoalFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
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
