import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub.model';
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
  private allClubs : FootballClub[];

  constructor(private allClubsService : AllClubsService, private allClubsFilterService : AllMatchesFilterService,
    private playMatchService : PlayMatchService, private ngxSpinnerService : NgxSpinnerService) { }

  ngOnInit(): void {
    this.getFootballClubs();

    //show a spinner until the data has been retrieved.
    if(this.allClubs) {
      this.ngxSpinnerService.hide();
    } else {
      this.ngxSpinnerService.show();
    }
  }

  public updateFootballClubsAfterPlay() : void {
    this.playMatchService.playMatch().subscribe(
      // response => this.handleSuccessfulResponseAfterPlay(response),
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }
  // handleSuccessfulResponseAfterPlay(response : any) : void {
  //   MatchAndClub.setClubs(response[0]);
  //   MatchAndClub.setMatches(response[1]);
  //   this.allClubs = MatchAndClub.clubs;
  // }
  public getFootballClubsSortedOnWins() : void {
    this.allClubsFilterService.getMatchesWinFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  public getFootballClubsSortedOnGoals() : void {
    this.allClubsFilterService.getMatchesGoalFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  public getFootballClubs() : void {
    this.allClubsService.getAllFootballClubs().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  private handleSuccessfulResponse(response : any) : void {
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
