import {BusModel} from "./Bus.model";

export interface ReservationModel {
  id: string,
  clientId: string,
  departureDate: string,
  busses: BusModel[],
}
