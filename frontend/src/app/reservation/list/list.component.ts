import { Component } from '@angular/core';
import {ReservationService} from "../service/reservation.service";
import {ReservationModel} from "../../models/Reservation.model";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {
  reservations: ReservationModel[] = [];

  constructor(private reservationService: ReservationService) {
    this.reservationService.list().subscribe({
      next: value => this.reservations = value,
    });
  }

  deleteReservation(reservationId: string) {
    this.reservationService.deleteReservation(reservationId).pipe(
      switchMap(() => this.reservationService.list())
    ).subscribe({
      next: value => this.reservations = value,
    });

  }
}
