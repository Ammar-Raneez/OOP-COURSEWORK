import { Component, OnInit } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch.model';
import { AllMatchesService } from 'src/app/services/all-matches/all-matches.service';
import { MatchesOnDateService } from 'src/app/services/matches-on-date/matches-on-date.service';

@Component({
  selector: 'app-all-matches',
  templateUrl: './all-matches.component.html',
  styleUrls: ['./all-matches.component.css'],
})
export class AllMatchesComponent implements OnInit {
  private dateRegex : RegExp = /^\d{4}-\d{2}-\d{2}$/;
  private allMatches : FootballMatch[];
  public date : string;

  constructor(private allMatchesService : AllMatchesService, private matchesByDateService : MatchesOnDateService) { }

  ngOnInit(): void {
    this.getFootballMatches();
  }

  public getFootballMatchesOnDate() : void {
    if(this.date.match(this.dateRegex)) {
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
    this.allMatches = response;
    console.log(response);
  }
  private handleErrorResponse(error : any) : void {
    this.allMatches = error.message;
    console.log(error);
  }

  public getAllFootballMatches() : FootballMatch[] {
    return this.allMatches;
  }
}
