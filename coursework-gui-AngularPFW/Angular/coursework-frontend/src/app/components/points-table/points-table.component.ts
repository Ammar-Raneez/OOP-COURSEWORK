import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { MatchAndClub } from 'src/app/models/MatchAndClub';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';
import { AllMatchesFilterService } from 'src/app/services/all-matches-filter/all-matches-filter.service';
import { PlayMatchService } from 'src/app/services/play-match/play-match.service';
import { NgxSpinnerService } from "ngx-spinner";


@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  allClubs : FootballClub[];

  constructor(private allClubsService : AllClubsService, private allClubsFilterService : AllMatchesFilterService,
    private playMatchService : PlayMatchService, private spinner: NgxSpinnerService
    ) { }
  ngOnInit(): void {
    this.spinner.show();
    this.getFootballClubs();

    if(this.allClubs) {
      this.spinner.hide();
    }
  }

  updateFootballClubsAfterPlay() : void {
    this.playMatchService.playMatch().subscribe(
      response => this.handleSuccessfulResponseAfterPlay(response),
      error => this.handleErrorResponse(error)
    )
  }
  handleSuccessfulResponseAfterPlay(response : any) : void {
    MatchAndClub.setClubs(response[0]);
    MatchAndClub.setMatches(response[1]);
    this.allClubs = MatchAndClub.clubs;
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
