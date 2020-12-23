/*
 * SelectedMatchComponent
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FootballMatch } from 'src/app/models/FootballMatch.model';
import { SelectedMatchService } from 'src/app/services/selected-match/selected-match.service';

/**
 * SelectedMatchComponent class, which will be used to render a selected match view
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Component({
  selector: 'app-selected-match',
  templateUrl: './selected-match.component.html',
  styleUrls: ['./selected-match.component.css']
})
export class SelectedMatchComponent implements OnInit {
  //*id to index into the array of all matches in the Play backend*//
  private id : string;
  private footballMatch : FootballMatch;
  //*Render a message highlighting the match*//
  private footerMsg : string;

  constructor(private selectedMatchService : SelectedMatchService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    //*obtain the id passed in the URL as parameter*//
    this.id = this.route.snapshot.params["id"];
    this.getFootballMatch();
  }

  /**
   * Get the selected match
   */
  public getFootballMatch() : void {
    this.selectedMatchService.getSelectedMatch(this.id).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  /**
   * Populates the footballMatch and footerMsg variables. Successful response
   * @param response - will hold the json format of the match data
   */
  private handleSuccessfulResponse(response : any) : void {
    this.footballMatch = response;
    console.log(response);
    //*create a highlight message based on the match winner*//
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
  /**
   * Populates the footballMatch variable. Error response
   * @param error - will hold an error message
   */
  private handleErrorResponse(error : any) : void {
    this.footballMatch = error.message;
    console.log(error);
  }

  /**
   * Returns the selected club
   * @return FootballMatch - the selected club
   */
  public getSelectedMatch() : FootballMatch {
    return this.footballMatch;
  }

  /**
   * Returns the generated footer message
   * @return string - the generated message
   */
  public getFooterMsg() : string {
    return this.footerMsg;
  }
}
