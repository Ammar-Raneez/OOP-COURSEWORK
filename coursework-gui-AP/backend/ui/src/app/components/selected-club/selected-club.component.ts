/*
 * SelectedClubComponent
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FootballClub } from 'src/app/models/FootballClub.model';
import { SelectedClubService } from 'src/app/services/selected-club/selected-club.service';

/**
 * SelectedClubComponent class, which will be used to render a selected club view
 * @version 1.x December 5th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@Component({
  selector: 'app-selected-club',
  templateUrl: './selected-club.component.html',
  styleUrls: ['./selected-club.component.css']
})
export class SelectedClubComponent implements OnInit {
  private selectedClub : FootballClub;
  private clubName : string;

  //*UI only based arrays*//
  private positionBackgroundColor: string[] = [];
  private starRatingCount: number[] = [];
  private playerImageLink: string[] = [];

  constructor(private selectedClubService : SelectedClubService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    //*Obtain the clubName url parameter passed in the URL, this can be then used to obtain the specific club*//
    this.clubName = this.route.snapshot.params["clubName"];
    this.getSelectedClub();
  }

  /**
   * Get the selected club
   */
  public getSelectedClub() : void {
    this.selectedClubService.getSelectedClub(this.clubName).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }
  /**
   * Populates the selectedClub. Successful response
   * @param response - will contain the Json format of the selectedClub
   */
  private handleSuccessfulResponse(response : any) : void {
    this.selectedClub = response;
    this.determinePositionBackgroundColor();
    this.determineStarRatingCount();
    this.determinePlayerImageLink();
    console.log(response);
  }
  /**
   * Populates the selectedClub. Error response
   * @param error - an error message
   */
  private handleErrorResponse(error : any) : void {
    this.selectedClub = error.message;
    console.log(error);
  }

  /**
   * Place a background border color for a player based on their pitch position
   */
  private determinePositionBackgroundColor() : void {
    const goldenRod = [ 'SW', 'LM', 'RM', 'CM', 'DM', 'AM' ];
    const red = ['CF', 'SS', 'S', 'WF'];
    const green = ['LB', 'RB', 'CB', 'LWB', 'RWB'];
    // const purple = ['GK']

    if (this.selectedClub) {
      for(let player of this.selectedClub.allPlayers) {
        if(goldenRod.includes(player.position)) {
          this.positionBackgroundColor.push('goldenrod');
        } else if(red.includes(player.position)) {
          this.positionBackgroundColor.push('red');
        } else if(green.includes(player.position)) {
          this.positionBackgroundColor.push('green');
        } else {
          this.positionBackgroundColor.push('purple');
        }
      }
    }
  }
  /**
   * Place a star rating count for a player based on their overall stat
   */
  private determineStarRatingCount() : void {
    if(this.selectedClub) {
      for(let player of this.selectedClub.allPlayers) {
        if(player.playerStats.overall < 50) {
          this.starRatingCount.push(3);
        } else if(player.playerStats.overall < 75) {
          this.starRatingCount.push(4);
        } else {
          this.starRatingCount.push(5);
        }
      }
    }
  }
  /**
   * Place a background image for a player randomly
   */
  private determinePlayerImageLink() : void {
    let imagePaths: string[] = ['assets/images/PL-player.png', 'assets/images/PL-player2.png', 'assets/images/PL-player3.png', 'assets/images/PL-player4.png', 'assets/images/PL-player5.png', 'assets/images/PL-player6.png', 'assets/images/PL-player7.png', 'assets/images/PL-player8.png'];
    if(this.selectedClub) {
      for(let _ of this.selectedClub.allPlayers) {
        let randomNum = Math.floor(Math.random() * imagePaths.length);
        this.playerImageLink.push(imagePaths[randomNum]);
      }
    }
  }

  /**
   * Return the selectedClub
   * @return FootballClub - the selected club
   */
  public getTheSelectedClub() : FootballClub {
    return this.selectedClub;
  }

  /**
   * Return the bgColor array
   * @return string[] - array of bg colors
   */
  public getPositionBgColor() : string[] {
    return this.positionBackgroundColor;
  }

  /**
   * Return the imageLink array
   * @return string[] - array of image links
   */
  public getImageLink() : string[] {
    return this.playerImageLink;
  }

  /**
   * Return the starRating array
   * @return number[] - array of star rating counts
   */
  public getStarRatingCount() : number[] {
    return this.starRatingCount;
  }
}
