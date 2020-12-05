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
import { HomePageComponent } from './components/home-page/home-page.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AllClubsComponent } from './components/all-clubs/all-clubs.component';
import { SelectedClubComponent } from './components/selected-club/selected-club.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PointsTableComponent,
    AllMatchesComponent,
    SelectedMatchComponent,
    HomePageComponent,
    AllClubsComponent,
    SelectedClubComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxSpinnerModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
