import { Component, OnInit } from '@angular/core';
import { FootballMatch } from 'src/app/models/FootballMatch';
import { SelectedMatchService } from 'src/app/services/selected-match.service';

@Component({
  selector: 'app-selected-match',
  templateUrl: './selected-match.component.html',
  styleUrls: ['./selected-match.component.css']
})
export class SelectedMatchComponent implements OnInit {
  id : string;
  footballMatch : FootballMatch;

  constructor(private selectedMatchService : SelectedMatchService) { }

  ngOnInit(): void {
    
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
  }
  handleErrorResponse(error : any) : void {
    this.footballMatch = error.message;
    console.log(error);
  }
}
