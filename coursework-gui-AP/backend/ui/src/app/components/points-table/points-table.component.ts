/*
 * PointsTableComponent
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub.model';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';
import { AllClubsFilterService } from 'src/app/services/all-clubs-filter/all-clubs-filter.service';
import { PlayMatchService } from 'src/app/services/play-match/play-match.service';
import { NgxSpinnerService } from "ngx-spinner";
import Swal from 'sweetalert2';

/**
 * PointsTableComponent class, which will be used to render the points table view
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  private allClubs : FootballClub[];

  //*instances of required Services injected via dependency injection*//
  constructor(private allClubsService : AllClubsService, private allClubsFilterService : AllClubsFilterService,
    private playMatchService : PlayMatchService, private ngxSpinnerService : NgxSpinnerService) { }

  ngOnInit(): void {
    this.getFootballClubs();

    //*show a spinner until the data has been retrieved.*//
    if(this.allClubs) {
      this.ngxSpinnerService.hide();
    } else {
      this.ngxSpinnerService.show();
    }
  }

  /**
   * Updates the list of footballClubs upon clicking the play match button
   */
  public updateFootballClubsAfterPlay() : void {
    this.playMatchService.playMatch().subscribe(
      response => this.handleSuccessfulResponseAfterPlay(response),
      error => this.handleErrorResponse(error)
    )
  }
  /**
   * Handles the subscribe of Play match. Successful response (Error response is same as for other services)
   * Throws an alert, if all matches have been played, else simply updates the standings
   * @param response - false/the clubs on whether a match has actually been played or not in the backend
   */
  handleSuccessfulResponseAfterPlay(response : any) : void {
    console.log(response);
    if (!response) {
      Swal.fire('❗❗❗❗', 'All Playable Matches have Already been Played!', 'error')
    }
    else {
      this.allClubs = response;
    }
  }

  /**
   * Gets the json format of the football clubs sorted based on wins in descending order
   */
  public getFootballClubsSortedOnWins() : void {
    this.allClubsFilterService.getClubsWinFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  /**
   * Gets the json format of the football clubs sorted based on goals for in descending order
   */
  public getFootballClubsSortedOnGoals() : void {
    this.allClubsFilterService.getClubsGoalFilter().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  /**
   * Gets the json format of all the football clubs (Is called by ngOnInit cuz the regular array is to be displayed first)
   */
  public getFootballClubs() : void {
    this.allClubsService.getAllFootballClubs().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  /**
   * Handles the subscribe. Successful response
   * @param response - will hold the json format of the clubs
   */
  private handleSuccessfulResponse(response : any) : void {
    this.allClubs = response;
  }
  /**
   * Handles the subscribe. Error response
   * @param error - will hold an error
   */
  private handleErrorResponse(error : any) : void {
    this.allClubs = error.message;
    console.log(error);
  }

  /**
   * Getter method for the template file to get access to the array of clubs
   */
  public getAllFootballClubs() : FootballClub[] {
    return this.allClubs;
  }
}
