/*
 * AllMatchesComponent
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Component, OnInit } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch.model';
import { AllMatchesService } from 'src/app/services/all-matches/all-matches.service';
import { MatchesOnDateService } from 'src/app/services/matches-on-date/matches-on-date.service';
import Swal from 'sweetalert2';

/**
 * AllMatchesComponent class, which will be used to render all the matches
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Component({
  selector: 'app-all-matches',
  templateUrl: './all-matches.component.html',
  styleUrls: ['./all-matches.component.css'],
})
export class AllMatchesComponent implements OnInit {
  //*Regex to validate date input*//
  private dateRegex : RegExp = /^\d{4}-\d{2}-\d{2}$/;
  //*Array of FootballMatch objects to hold all matches*//
  private allMatches : FootballMatch[];
  public date : string;

  constructor(private allMatchesService : AllMatchesService, private matchesByDateService : MatchesOnDateService) { }

  ngOnInit(): void {
    this.getFootballMatches();
  }

  /**
   * Gets the matches that have been played the same date as that has been input
   */
  public getFootballMatchesOnDate() : void {
    if(this.date.match(this.dateRegex)) {
      this.matchesByDateService.getMatchesOnDate(this.date).subscribe(
        response => this.handleSuccessfulResponse(response),
        error => this.handleErrorResponse(error)
      );
    } else {
      Swal.fire('❗❗❗❗', 'Please Specify a Date with Format yyyy-mm-dd', 'error')
    }
  }

  /**
   * Gets all football matches, subscribes to the observable returned by getAllFootballMatches()
   */
  public getFootballMatches() : void {
    //*Resets the date input to blank*//
    this.date = "";
    this.allMatchesService.getAllFootballMatches().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  /**
   * Populates the allMatches array. Successful response
   * @param response - will hold the matches in json format
   */
  private handleSuccessfulResponse(response : any) : void {
    this.allMatches = response;
    if (this.allMatches.length == 0) {
      Swal.fire('🧐🧐🧐', 'Hmm... There appears to be no matches played at this date', 'info')
    }
    console.log(response);
  }
  /**
   * Populates the allMatches array. Error response
   * @param error - will hold an error message
   */
  private handleErrorResponse(error : any) : void {
    this.allMatches = error.message;
    console.log(error);
  }

  /**
   * Getter method to return the array of FootballMatches
   * @return FootballMatch[] - array of football matches
   */
  public getAllFootballMatches() : FootballMatch[] {
    return this.allMatches;
  }
}
