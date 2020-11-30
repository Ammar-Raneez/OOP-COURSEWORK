import { Component, OnInit } from '@angular/core';
import { fadeInDownAnimation, fadeInUpAnimation } from 'angular-animations-library';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  animations: [
    fadeInDownAnimation()
  ]
})
export class HomePageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
