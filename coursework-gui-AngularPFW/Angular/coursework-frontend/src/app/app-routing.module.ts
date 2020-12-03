import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllClubsComponent } from './components/all-clubs/all-clubs.component';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PointsTableComponent } from './components/points-table/points-table.component';
import { SelectedMatchComponent } from './components/selected-match/selected-match.component';

const routes: Routes = [
  {
    path: "",
    component: HomePageComponent
  },
  {
    path: "clubs",
    component: AllClubsComponent
  },
  {
    path: "standings",
    component: PointsTableComponent
  },
  {
    path: "matches",
    component: AllMatchesComponent
  },
  {
    path: "matches/:id",
    component: SelectedMatchComponent
  },
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
