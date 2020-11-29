import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PointsTableComponent } from './components/points-table/points-table.component';
import { HttpClientModule } from '@angular/common/http';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PointsTableComponent,
    AllMatchesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
