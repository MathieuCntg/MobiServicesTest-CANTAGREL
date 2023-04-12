import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReservationModel} from "../../models/Reservation.model";
import {environment} from "../../../environments/environment";
import {PaymentMethods} from "../../enums/PaymentMethods";

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

  list():Observable<ReservationModel[]> {
    return this.http.get<ReservationModel[]>(`${environment.backendUrl}/reservations`)
  }

  deleteReservation(reservationId: string): Observable<void>{
    return this.http.delete<void>(`${environment.backendUrl}/reservations/${reservationId}`)
  }

  payReservationWithPaypal(reservationId: string, email: string): Observable<ReservationModel> {
    return this.http.post<ReservationModel>(`${environment.backendUrl}/reservations/${reservationId}/pay`, {
      paymentMethod: PaymentMethods.PAYPAL,
      paymentInformation: {
        email: email
      }
    })
  }

  payReservationWithCreditCard(reservationId: string, creditCardNumber: string, creditCardExpirationDate: string): Observable<ReservationModel> {
    return this.http.post<ReservationModel>(`${environment.backendUrl}/reservations/${reservationId}/pay`, {
      paymentMethod:  PaymentMethods.CREDIT_CARD,
      paymentInformation: {
        creditCardNumber: creditCardNumber,
        creditCardExpirationDate: creditCardExpirationDate
      }
    })
  }
}
