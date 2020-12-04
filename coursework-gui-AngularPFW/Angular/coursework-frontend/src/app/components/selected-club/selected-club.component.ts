import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FootballClub } from 'src/app/models/FootballClub';
import { SelectedClubService } from 'src/app/services/selected-club/selected-club.service';

@Component({
  selector: 'app-selected-club',
  templateUrl: './selected-club.component.html',
  styleUrls: ['./selected-club.component.css']
})
export class SelectedClubComponent implements OnInit {
  selectedClub : FootballClub;
  clubName : string;
  positionBackgroundColor: string[] = [];
  starRatingCount: number[] = [];
  imageLink: string[] = [];

  constructor(private selectedClubService : SelectedClubService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.clubName = this.route.snapshot.params["clubName"];
    this.getSelectedClub();
  }

  getSelectedClub() : void{
    this.selectedClubService.getSelectedClub(this.clubName).subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    )
  }
  handleSuccessfulResponse(response : any) : void {
    this.selectedClub = response;
    this.determinePositionBackgroundColor();
    this.determineStarRatingCount();
    this.determineImageLink();
    console.log(response);
  }
  handleErrorResponse(error : any) : void {
    this.selectedClub = error.message;
    console.log(error);
  }

  determinePositionBackgroundColor() : void {
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

  determineStarRatingCount() : void {
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

  determineImageLink() : void {
    let threeImagePaths: string[] = ['assets/images/PL-player.png', 'assets/images/PL-player2.png', 'assets/images/PL-player3.png'];
    if(this.selectedClub) {
      for(let player of this.selectedClub.allPlayers) {
        let randomNum = Math.floor(Math.random() * 3);
        this.imageLink.push(threeImagePaths[randomNum]);
      }
    }
  }
}
