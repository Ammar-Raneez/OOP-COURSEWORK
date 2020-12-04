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
    console.log(response);
  }
  handleErrorResponse(error : any) : void {
    this.selectedClub = error.message;
    console.log(error);
  }
}
