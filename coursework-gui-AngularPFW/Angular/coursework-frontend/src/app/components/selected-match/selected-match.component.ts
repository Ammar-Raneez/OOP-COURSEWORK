import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FootballMatch } from 'src/app/models/FootballMatch';
import { SelectedMatchService } from 'src/app/services/selected-match/selected-match.service';

@Component({
  selector: 'app-selected-match',
  templateUrl: './selected-match.component.html',
  styleUrls: ['./selected-match.component.css']
})
export class SelectedMatchComponent implements OnInit {
  id : string;
  footballMatch : FootballMatch;
  footerMsg : string;

  constructor(private selectedMatchService : SelectedMatchService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params["id"];
    this.getFootballMatches();
  }

  getFootballMatches() : void {
    this.selectedMatchService.getSelectedMatch(this.id).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  handleSuccessfulResponse(response : any) : void {
    this.footballMatch = response;
    console.log(response);
    this.footerMsg = this.footballMatch.firstTeamSingleMatchStats.goals > this.footballMatch.secondTeamSingleMatchStats.goals ? 
                    this.footballMatch.firstTeam.clubName + " beat "  + this.footballMatch.secondTeam.clubName + ", scoring " + 
                    this.footballMatch.firstTeamSingleMatchStats.goals + " goals with a possession of " + 
                    this.footballMatch.firstTeamSingleMatchStats.possession + "%" : 
                      this.footballMatch.firstTeamSingleMatchStats.goals < this.footballMatch.secondTeamSingleMatchStats.goals ?
                      this.footballMatch.secondTeam.clubName + " beat "  + this.footballMatch.firstTeam.clubName + ", scoring " + 
                      this.footballMatch.secondTeamSingleMatchStats.goals + " goals with a possession of " + 
                      this.footballMatch.secondTeamSingleMatchStats.possession + "%" :  
                        "The match was a draw, with both teams scoring " + this.footballMatch.firstTeamSingleMatchStats.goals + " goals."
  }
  handleErrorResponse(error : any) : void {
    this.footballMatch = error.message;
    console.log(error);
  }
}
