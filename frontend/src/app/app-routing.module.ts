import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {MakeReservationComponent} from "./reservation/make-reservation/make-reservation.component";
import {ListComponent} from "./reservation/list/list.component";
import {PayComponent} from "./reservation/pay/pay.component";
import {PaypalComponent} from "./reservation/pay/paypal/paypal.component";
import {CreditCardComponent} from "./reservation/pay/credit-card/credit-card.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'make-reservation', component: MakeReservationComponent},
  {path: 'list', component: ListComponent},
  {path: 'pay', component: PayComponent, children :[
    {path: 'paypal', component: PaypalComponent},
    {path: 'credit-card', component: CreditCardComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
