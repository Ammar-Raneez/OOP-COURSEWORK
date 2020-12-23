/*
 * AllClubsComponent
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub.model';
import { AllClubsService } from 'src/app/services/all-clubs/all-clubs.service';

@Component({
  selector: 'app-all-clubs',
  templateUrl: './all-clubs.component.html',
  styleUrls: ['./all-clubs.component.css']
})

/**
 * AllClubsComponent class, which will be used to render all the clubs
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
export class AllClubsComponent implements OnInit {
  //*Create an array of FootballClub objects to hold all Football clubs*//
  private allClubs : FootballClub[];

  //*create an instance of AllClubsService via Dependency Injection*//
  constructor(private allClubsService : AllClubsService) { }

  //*Once component is mounted get all the football clubs*//
  ngOnInit(): void {
    this.getFootballClubs();
  }

  /**
   * Gets all the footballClubs, by subscribing to the Observable returned in getAllFootballClubs()
   */
  public getFootballClubs() : void {
    this.allClubsService.getAllFootballClubs().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }

  /**
   * Populates the array of clubs
   * @param response - successful response (holds all club data in json format)
   */
  private handleSuccessfulResponse(response : any) : void {
    console.log(response)
    this.allClubs = response;
  }
  /**
   * Populates array of clubs
   * @param error - error response
   */
  private handleErrorResponse(error : any) : void {
    this.allClubs = error.message;
    console.log(error);
  }

  /**
   * Getter method, for the template file to get access to the array of football clubs
   * @return FootballClub[] - array of football clubs
   */
  public getAllFootballClubs() : FootballClub[] {
    return this.allClubs;
  }
}
