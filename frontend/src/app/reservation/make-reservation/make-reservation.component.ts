import { Component } from '@angular/core';
import {NonNullableFormBuilder, Validators} from "@angular/forms";
import {ReservationService} from "../service/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent {

  reservationForm = this.fb.group({
    departureDate: ['', Validators.required],
    clientID: ['', Validators.required],
    bussesIds: ['', Validators.required]
  })

  constructor(private fb: NonNullableFormBuilder, private reservationService: ReservationService, private router: Router) {
  }

  onSubmit() {
    const { departureDate, clientID, bussesIds} = this.reservationForm.value;
    this.reservationService.makeReservation(new Date(departureDate!), clientID!, [bussesIds!]).subscribe({
      next: () => this.router.navigateByUrl("/list")
    })
  }
}
