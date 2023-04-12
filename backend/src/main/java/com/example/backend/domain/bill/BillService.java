package com.example.backend.domain.bill;

import java.util.List;
import java.util.UUID;

public interface BillService {

    List<Bill> getPaidBillByClientId(UUID clientId);
}
