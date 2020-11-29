import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllMatchesComponent } from './components/all-matches/all-matches.component';
import { PointsTableComponent } from './components/points-table/points-table.component';

const routes: Routes = [
  {
    path: "",
    component: PointsTableComponent
  },
  {
    path: "matches",
    component: AllMatchesComponent
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
