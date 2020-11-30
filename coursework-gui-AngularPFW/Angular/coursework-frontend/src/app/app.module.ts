import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PointsTableComponent } from './components/points-table/points-table.component';
import { HttpClientModule } from '@angular/common/http';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';
import { SelectedMatchComponent } from './components/selected-match/selected-match.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PointsTableComponent,
    AllMatchesComponent,
    SelectedMatchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
