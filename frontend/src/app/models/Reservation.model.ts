import {BusModel} from "./Bus.model";
import {PaymentMethods} from "../enums/PaymentMethods";

export interface ReservationModel {
  id: string,
  clientId: string,
  departureDate: string,
  busses: BusModel[],
  paymentMethod?: PaymentMethods;
}
