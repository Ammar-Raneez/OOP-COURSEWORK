import { Component, OnInit } from '@angular/core';
import { FootballClub } from 'src/app/models/FootballClub';
import { Player } from 'src/app/models/Player';
import { PointsTableService } from 'src/app/services/points-table.service';

@Component({
  selector: 'app-points-table',
  templateUrl: './points-table.component.html',
  styleUrls: ['./points-table.component.css']
})
export class PointsTableComponent implements OnInit {
  playMessage : Player[];

  constructor(private pointsTableService : PointsTableService) { }

  ngOnInit(): void {
  }

  displayWelcomeMessage() : void {
    this.pointsTableService.executeTestPlayFramework().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error)
    );
  }
  handleSuccessfulResponse(response : any) : void {
    this.playMessage = response[0].allPlayers;
    console.log(response);
  }
  handleErrorResponse(error : any) : void {
    this.playMessage = error.message;
    console.log(error);
  }
}
