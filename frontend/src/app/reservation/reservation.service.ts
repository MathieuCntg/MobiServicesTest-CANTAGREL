import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReservationModel} from "../models/Reservation.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  makeReservation(departureDate: Date, clientID: string, bussesIds: string[]): Observable<ReservationModel> {
    return this.http.post<ReservationModel>(`${environment.backendUrl}/reservations`, {
      departureDate: departureDate,
      clientID: clientID,
      bussesIds: bussesIds
    })
  }
}
