import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ReservationModel} from "../models/Reservation.model";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {

  @Input() reservation!: ReservationModel;

  @Output() onDelete = new EventEmitter<string>()
}
