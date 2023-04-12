import { Component } from '@angular/core';
import {NonNullableFormBuilder, Validators} from "@angular/forms";
import {ReservationService} from "../../service/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent {
  creditCardForm = this.fb.group({
    reservationId: ['', Validators.required],
    creditCardNumber: ['', Validators.required],
    creditCardExpirationDate: ['', Validators.required]
  })

  constructor(private fb: NonNullableFormBuilder, private reservationService: ReservationService, private router:Router) {
  }

  onSubmit() {
    const {reservationId, creditCardNumber, creditCardExpirationDate} = this.creditCardForm.value;

    this.reservationService.payReservationWithCreditCard(reservationId!, creditCardNumber!, creditCardExpirationDate!).subscribe({
      next: () => this.router.navigateByUrl("/list")
    })
  }
}
