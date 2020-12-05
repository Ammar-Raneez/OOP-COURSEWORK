import { Component, OnInit } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch';
// import { MatchAndClub } from 'src/app/models/MatchAndClub';
import { AllMatchesService } from 'src/app/services/all-matches/all-matches.service';
import { MatchesOnDateService } from 'src/app/services/matches-on-date/matches-on-date.service';

@Component({
  selector: 'app-all-matches',
  templateUrl: './all-matches.component.html',
  styleUrls: ['./all-matches.component.css'],
})
export class AllMatchesComponent implements OnInit {
  private static dateRegex : RegExp = /^\d{4}-\d{2}-\d{2}$/;
  private static allMatches : FootballMatch[];
  public date : string;

  constructor(private allMatchesService : AllMatchesService, private matchesByDateService : MatchesOnDateService) { }
  ngOnInit(): void {
    this.getFootballMatches();

    // if (MatchAndClub.matches) {
    //   this.allMatches = MatchAndClub.matches;
    // }
  }

  public getFootballMatchesOnDate() : void {
    if(this.date.match(AllMatchesComponent.dateRegex)) {
      this.matchesByDateService.getMatchesOnDate(this.date).subscribe(
        response => this.handleSuccessfulResponse(response),
        error => this.handleErrorResponse(error)
      );
    } else {
      window.alert("Invalid Date Format!")
    }
  }

  public getFootballMatches() : void {
    this.date = "";
    this.allMatchesService.getAllFootballMatches().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  private handleSuccessfulResponse(response : any) : void {
    AllMatchesComponent.allMatches = response;
    for(let match of AllMatchesComponent.allMatches) {
      let month = parseInt(match.matchDate[1]) < 10? "0" + match.matchDate[1] : match.matchDate[2];
      let day = parseInt(match.matchDate[2]) < 10? "0" + match.matchDate[2] : match.matchDate[2];
      match.matchDate = match.matchDate[0] + "-" + month + "-" + day;
    }
    console.log(response);
  }
  private handleErrorResponse(error : any) : void {
    AllMatchesComponent.allMatches = error.message;
    console.log(error);
  }

  public getAllFootballMatches() : FootballMatch[] {
    return AllMatchesComponent.allMatches;
  }
}
