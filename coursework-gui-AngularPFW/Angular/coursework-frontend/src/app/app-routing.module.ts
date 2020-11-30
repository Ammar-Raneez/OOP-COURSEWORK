import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';
import { PointsTableComponent } from './components/points-table/points-table.component';
import { SelectedMatchComponent } from './components/selected-match/selected-match.component';

const routes: Routes = [
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
    component: PointsTableComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
