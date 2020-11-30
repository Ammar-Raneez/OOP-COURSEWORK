import { Component, OnInit } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch';
import { AllMatchesService } from 'src/app/services/all-matches/all-matches.service';

@Component({
  selector: 'app-all-matches',
  templateUrl: './all-matches.component.html',
  styleUrls: ['./all-matches.component.css']
})
export class AllMatchesComponent implements OnInit {
  allMatches : FootballMatch[]

  constructor(private allMatchesService : AllMatchesService) { }

  ngOnInit(): void {
    this.getFootballMatches();
  }

  getFootballMatches() : void {
    this.allMatchesService.getAllFootballMatches().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  handleSuccessfulResponse(response : any) : void {
    this.allMatches = response;

    for(let match of this.allMatches) {
      let month = parseInt(match.matchDate[1]) < 10? "0" + match.matchDate[1] : match.matchDate[2];
      let day = parseInt(match.matchDate[2]) < 10? "0" + match.matchDate[2] : match.matchDate[2];
      match.matchDate = match.matchDate[0] + "-" + month + "-" + day;
      console.log(match.matchDate)
    }

    console.log(response);
  }
  handleErrorResponse(error : any) : void {
    this.allMatches = error.message;
    console.log(error);
  }
}
