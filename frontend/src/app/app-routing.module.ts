import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {MakeReservationComponent} from "./reservation/make-reservation/make-reservation.component";
import {ListComponent} from "./reservation/list/list.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'make-reservation', component: MakeReservationComponent},
  {path: 'list', component: ListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
