import { Component } from '@angular/core';

@Component({
  //*Tag that will render the template, that is styled with the styleUrls*//
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : string = 'Premier League';
}
