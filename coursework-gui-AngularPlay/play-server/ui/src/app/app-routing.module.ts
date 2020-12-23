import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllClubsComponent } from './components/all-clubs/all-clubs.component';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PointsTableComponent } from './components/points-table/points-table.component';
import { SelectedClubComponent } from './components/selected-club/selected-club.component';
import { SelectedMatchComponent } from './components/selected-match/selected-match.component';

//*Define all routes navigable*//
const routes: Routes = [
  //* path '/' *//
  {
    path: "",
    component: HomePageComponent
  },
  //* path '/clubs' *//
  {
    path: "clubs",
    component: AllClubsComponent
  },
  //* path '/clubs/clubParam' *//
  {
    path: "clubs/:clubName",
    component: SelectedClubComponent
  },
  //* path '/standings' *//
  {
    path: "standings",
    component: PointsTableComponent
  },
  //* path '/matches' *//
  {
    path: "matches",
    component: AllMatchesComponent
  },
  //* path '/matches/matchParam' *//
  {
    path: "matches/:id",
    component: SelectedMatchComponent
  },
  //* wildcard, will match any url (used at the end, to avoid error 404 pages) *//
  {
    path: "**",
    component: HomePageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
