import { Component } from '@angular/core';
import {ReservationService} from "../../service/reservation.service";
import {Router} from "@angular/router";
import {NonNullableFormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-paypal',
  templateUrl: './paypal.component.html',
  styleUrls: ['./paypal.component.css']
})
export class PaypalComponent {
  paypalForm = this.fb.group({
    reservationId: ['', Validators.required],
    email: ['', Validators.required]
  })

  constructor(private fb: NonNullableFormBuilder, private reservationService: ReservationService, private router:Router) {
  }

  onSubmit() {
    const { reservationId, email } = this.paypalForm.value;

    this.reservationService.payReservationWithPaypal(reservationId!, email!).subscribe({
      next: () => this.router.navigateByUrl("/list")
    })
  }
}
