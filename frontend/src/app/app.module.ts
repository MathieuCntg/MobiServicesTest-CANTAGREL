import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { MakeReservationComponent } from './reservation/make-reservation/make-reservation.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { ListComponent } from './reservation/list/list.component';
import { ReservationComponent } from './reservation/reservation.component';
import { PayComponent } from './reservation/pay/pay.component';
import { PaypalComponent } from './reservation/pay/paypal/paypal.component';
import { CreditCardComponent } from './reservation/pay/credit-card/credit-card.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MakeReservationComponent,
    ListComponent,
    ReservationComponent,
    PayComponent,
    PaypalComponent,
    CreditCardComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
