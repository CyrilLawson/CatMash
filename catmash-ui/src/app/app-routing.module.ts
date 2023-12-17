import { NgModule } from '@angular/core';
import { CutestCatComponent } from './views/pages/cutest-cat/cutest-cat.component';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './views/pages/home/home.component';

const routes: Routes = [
  {
    path: "cats/cutest", component: CutestCatComponent
  },
  {
    path: '', component: HomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
